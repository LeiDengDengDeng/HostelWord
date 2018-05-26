package com.deng.service.hostel;

import com.deng.dao.hostel.CheckInRecordDaoImpl;
import com.deng.dao.hostel.HostelRoomDaoImpl;
import com.deng.pojo.hostel.*;
import com.deng.pojo.member.ConsumeType;
import com.deng.pojo.member.Member;
import com.deng.pojo.order.RoomOrder;
import com.deng.service.exception.ServiceException;
import com.deng.service.manager.ManagerService;
import com.deng.service.member.MemberService;
import com.deng.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by deng on 2017/3/3.
 */
@Service("HostelRoomService")
@Transactional
public class HostelRoomServiceImpl implements HostelRoomService {
    @Autowired
    private HostelRoomDaoImpl hostelRoomDao;
    @Autowired
    private CheckInRecordDaoImpl checkInRecordDao;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private HostelService hostelService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberService memberService;

    public HostelRoom getHostelRoomByHostelAndRoomNum(long hostelId, int roomNum) {
        return hostelRoomDao.getHostelRoomByHostelAndRoomNum(hostelId, roomNum);
    }

    public void orderHostelRoom(HostelRoom room, Date inDate, Date outDate) throws ServiceException {
        checkTargetTime(inDate, outDate);
        synchronized (HostelRoomService.class) {
            for (HostelRoomState state : getStateInTargetTime(room, inDate, outDate)) {
                if (state.isIsOrdered()) throw new ServiceException(100003, "房间刚被预定!");
                state.setIsOrdered(true);
            }
        }
        hostelRoomDao.update(room);
    }

    public void cancelOrderHostelRoom(HostelRoom room, Date inDate, Date outDate) throws ServiceException {
        checkTargetTime(inDate, outDate);
        synchronized (HostelRoomService.class) {
            for (HostelRoomState state : getStateInTargetTime(room, inDate, outDate)) {
                if (!state.isIsOrdered()) throw new ServiceException(100004, "房间未被预定!");
                state.setIsOrdered(false);
            }
        }
        hostelRoomDao.update(room);
    }

    public void checkInHostelRoom(long memberId, long orderId, int price, Set<Visitor> visitors, long hostelId, int roomNum, Date inDate, Date outDate) throws ServiceException {
        RoomOrder order = orderService.getOrder(orderId);
        if (order == null) {
            managerService.consumeManagerMoney(price, ConsumeType.ORDER);
            hostelService.gainMoney(hostelId, price);
        } else {
            orderService.effectOrder(orderId);
        }

        Member member = memberService.getMemberProfile(memberId);
        if (order == null & member != null) {
            memberService.consumeMemberMoney(memberId, price, ConsumeType.ORDER);
        }

        HostelRoom room = getHostelRoomByHostelAndRoomNum(hostelId, roomNum);
        checkTargetTime(inDate, outDate);
        for (HostelRoomState state : getStateInTargetTime(room, inDate, outDate)) {
            if (state.isIsCheckedIn()) throw new ServiceException(100006, "已被入住");
            state.setIsCheckedIn(true);
        }
        CheckInRecord checkInRecord = new CheckInRecord(room, order, member, inDate, outDate, visitors);
        for (Visitor v : visitors)
            v.setCheckInRecord(checkInRecord);
        room.getCheckInRecords().add(checkInRecord);
        checkInRecordDao.save(checkInRecord);
        hostelRoomDao.update(room);
    }

    public List<CheckInRecord> getCheckInRecordsByHostelId(long hostelId) {
        return checkInRecordDao.getCheckInRecordsByHostelId(hostelId);
    }

    public List<HostelRoomBO> getHostelRooms(long hostelId, Date start, Date end) {
        List<HostelRoom> rooms = hostelRoomDao.getHostelRooms(hostelId);

        List<HostelRoomBO> roomBOS = new ArrayList<HostelRoomBO>();
        for (HostelRoom room : rooms) {
            List<HostelRoomState> states = getStateInTargetTime(room, start, end);
            boolean ordered = false;
            boolean checkedIn = false;
            for (HostelRoomState state : states) {
                ordered = ordered || state.isIsOrdered();
                checkedIn = checkedIn || state.isIsCheckedIn();
            }

            HostelRoomBO roomBO = new HostelRoomBO(room.getRoomNum(), room.getRoomType(), ordered, checkedIn);
            roomBOS.add(roomBO);
        }

        return roomBOS;
    }

    public int getRandomFreeRoomByTypeAndDate(long hostelId, RoomType type, Date inDate, Date outDate) throws ServiceException {
        checkTargetTime(inDate, outDate);

        int roomNum = -1;
        List<HostelRoom> rooms = hostelRoomDao.getHostelRoomByType(hostelId, type);
        for (HostelRoom room : rooms) {
            boolean canBeOrdered = true;
            for (HostelRoomState state : getStateInTargetTime(room, inDate, outDate)) {
                if (state.isIsCheckedIn() || state.isIsOrdered()) {
                    canBeOrdered = false;
                    break;
                }
            }
            if (canBeOrdered) {
                roomNum = room.getRoomNum();
                break;
            }
        }

        if (roomNum == -1) throw new ServiceException(100002, "无空闲房间");

        return roomNum;
    }

    public int getPrice(long hostelId, RoomType type, Date inDate, Date outDate, int vipLevel) {
        BasicSchedule basicSchedule = scheduleService.getBasicScheduleByHostelIdAndRoomType(hostelId, type);
        int nullCount = 0;
        int result = 0;

        // 开始循环的时间点
        Calendar c = Calendar.getInstance();
        c.setTime(inDate);
        // 结束循环的时间点
        Calendar end = Calendar.getInstance();
        end.setTime(outDate);
        try {
            do {
                SpecialSchedule specialSchedule = scheduleService.getSpecialSchedule(hostelId, type, c.getTime());
                if (specialSchedule == null) {
                    nullCount++;
                } else {
                    Method method = specialSchedule.getClass().getDeclaredMethod("getVip" + vipLevel + "Price");
                    result += (Integer) method.invoke(specialSchedule);
                }
                c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
            } while (c.getTimeInMillis() <= end.getTimeInMillis());

            String methodName = "getVip" + vipLevel + "Price";
            Method method = basicSchedule.getClass().getDeclaredMethod(methodName);
            result += (Integer) method.invoke(basicSchedule) * nullCount;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断inDate和outDate是否符合规则（从今天起一个星期）
     *
     * @param inDate  入住日期
     * @param outDate 离店日期
     * @throws ServiceException
     */
    private void checkTargetTime(Date inDate, Date outDate) throws ServiceException {
        Calendar sevenDaysLater = Calendar.getInstance();
        sevenDaysLater.set(Calendar.DATE, sevenDaysLater.get(Calendar.DATE) + 7);
        Date sevenDaysLaterDate = sevenDaysLater.getTime();

        Calendar now = Calendar.getInstance();
        now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
        Date nowDate = now.getTime();

        if (!(inDate.after(nowDate) && inDate.before(sevenDaysLaterDate) && outDate.after(nowDate) && outDate.before(sevenDaysLaterDate)))
            throw new ServiceException(100001, "只能预定从今天起一个星期以内的房间!");
    }

    private List<HostelRoomState> getStateInTargetTime(HostelRoom room, Date inDate, Date outDate) {
        Calendar sevenDaysLater = Calendar.getInstance();
        sevenDaysLater.set(Calendar.DATE, sevenDaysLater.get(Calendar.DATE) + 7);
        Date sevenDaysLaterDate = sevenDaysLater.getTime();

        Calendar in = Calendar.getInstance();
        in.setTime(inDate);
        Calendar out = Calendar.getInstance();
        out.setTime(outDate);

        int outLoc = sevenDaysLater.get((Calendar.DATE)) - out.get(Calendar.DATE) - 1;
        int inLoc = sevenDaysLater.get((Calendar.DATE)) - in.get(Calendar.DATE) - 1;

        return room.getHostelRoomStates().subList(outLoc, inLoc + 1);
    }
}
