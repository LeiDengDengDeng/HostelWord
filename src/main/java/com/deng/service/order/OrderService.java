package com.deng.service.order;


import com.deng.pojo.order.RoomOrder;
import com.deng.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/3/17.
 */
public interface OrderService {
    void orderRoom(long memberId, long hostelId, int roomNum, int price, Date inDate, Date outDate) throws ServiceException;

    void cancelOrder(long orderId) throws ServiceException;

    RoomOrder getOrder(long orderId);

    List<RoomOrder> getMemberOrders(long memberId);

    List<RoomOrder> getHostelOrders(long hostelId);

    void effectOrder(long orderId) throws ServiceException;
}
