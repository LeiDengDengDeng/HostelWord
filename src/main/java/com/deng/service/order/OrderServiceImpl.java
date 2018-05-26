package com.deng.service.order;

import com.deng.aspect.LogType;
import com.deng.dao.order.OrderDaoImpl;
import com.deng.pojo.hostel.HostelRoom;
import com.deng.pojo.member.ConsumeType;
import com.deng.pojo.order.RoomOrder;
import com.deng.service.exception.ServiceException;
import com.deng.service.hostel.HostelRoomService;
import com.deng.service.hostel.HostelService;
import com.deng.service.manager.ManagerService;
import com.deng.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/3/17.
 */
@Service("OrderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDaoImpl orderDao;
    @Autowired
    private MemberService memberService;
    @Autowired
    private HostelRoomService hostelRoomService;
    @Autowired
    private HostelService hostelService;
    @Autowired
    private ManagerService managerService;

    @LogType(operationName = "预定房间")
    public void orderRoom(long memberId, long hostelId, int roomNum, int price, Date inDate, Date outDate) throws ServiceException {
        // 修改会员卡余额和会员卡积分
        memberService.consumeMemberMoney(memberId, -price, ConsumeType.ORDER);
        memberService.changeMemberPoint(memberId, price / 100, ConsumeType.ORDER);
        // 修改酒店和经理资金
        hostelService.gainMoney(hostelId, price);
        managerService.consumeManagerMoney(price, ConsumeType.ORDER);

        // 修改酒店房间信息，状态变成已预定
        HostelRoom room = hostelRoomService.getHostelRoomByHostelAndRoomNum(hostelId, roomNum);
        hostelRoomService.orderHostelRoom(room, inDate, outDate);

        room = hostelRoomService.getHostelRoomByHostelAndRoomNum(hostelId, roomNum);
        RoomOrder order = new RoomOrder(memberService.getMemberProfile(memberId), room, price, price / 100, new Date(), inDate, outDate);
        orderDao.save(order);
    }

    @LogType(operationName = "取消预定")
    public void cancelOrder(long orderId) throws ServiceException {
        RoomOrder order = orderDao.get(RoomOrder.class, orderId);

        synchronized (OrderService.class) {
            if (!order.canBeCanceled()) throw new ServiceException(600001, "订单已经无法被撤销");
            // 修改用户积分，放在这是因为用户积分可能不够扣除，此时不允许取消预定
            memberService.changeMemberPoint(order.getMember().getMemberId(), -order.getPoint(), ConsumeType.CANCEL_ORDER);

            order.setCanceled(true);
            orderDao.update(order);
        }
        // 将房间修改成未被预定
        hostelRoomService.cancelOrderHostelRoom(order.getRoom(), order.getInDate(), order.getOutDate());
        // 修改用户金额
        memberService.consumeMemberMoney(order.getMember().getMemberId(), order.getPrice(), ConsumeType.CANCEL_ORDER);
        // 修改酒店和经理资金
        hostelService.gainMoney(order.getRoom().getHostel().getId(), -order.getPrice());
        managerService.consumeManagerMoney(-order.getPrice(), ConsumeType.CANCEL_ORDER);
    }

    public RoomOrder getOrder(long orderId) {
        return orderDao.get(RoomOrder.class, orderId);
    }

    public List<RoomOrder> getMemberOrders(long memberId) {
        return orderDao.getOrdersByMemberId(memberId);
    }

    public List<RoomOrder> getHostelOrders(long hostelId) {
        return orderDao.getOrdersByHostelId(hostelId);
    }

    public void effectOrder(long orderId) throws ServiceException {
        RoomOrder order = orderDao.get(RoomOrder.class, orderId);
        if (order.isCheckedIn()) throw new ServiceException(700001, "已被入住");
        order.setCheckedIn(true);
        orderDao.update(order);
    }
}
