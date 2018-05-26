package com.deng.service.hostel;

import com.deng.pojo.hostel.*;
import com.deng.service.exception.ServiceException;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by deng on 2017/3/3.
 */
public interface HostelRoomService {
    /**
     * 根据酒店id和房间号获得房间信息
     *
     * @param hostelId 酒店id
     * @param roomNum  房间号
     * @return 对应房间信息
     */
    HostelRoom getHostelRoomByHostelAndRoomNum(long hostelId, int roomNum);

    void orderHostelRoom(HostelRoom room, Date inDate, Date outDate) throws ServiceException;

    void cancelOrderHostelRoom(HostelRoom room, Date inDate, Date outDate) throws ServiceException;

    void checkInHostelRoom(long memberId, long orderId, int price, Set<Visitor> visitors, long hostelId, int roomNum, Date inDate, Date outDate) throws ServiceException;

    List<CheckInRecord> getCheckInRecordsByHostelId(long hostelId);

    /**
     * 获得对应酒店下的某一个时间段的所有酒店房间信息
     *
     * @param hostelId 酒店id
     * @return 对应酒店下的某一时间段的所有酒店房间信息
     */
    List<HostelRoomBO> getHostelRooms(long hostelId, Date start, Date end);

    /**
     * 获得所选择的房间类型及日期下的任一一间空房的房间号码
     *
     * @param hostelId 酒店ID
     * @param type     房间类型
     * @param inDate   入住日期
     * @param outDate  离店日期
     * @return 所选择的房间类型的任一一间空房的房间号码
     * @throws ServiceException 找不到空房间时throw
     */
    int getRandomFreeRoomByTypeAndDate(long hostelId, RoomType type, Date inDate, Date outDate) throws ServiceException;

    /**
     * 获得所选择的房间类型及日期下的任一一间空房的房间号码
     *
     * @param hostelId 酒店ID
     * @param type     房间类型
     * @param inDate   入住日期
     * @param outDate  离店日期
     * @param vipLevel vip等级，0代表非会员
     * @return 所选择的房间类型的价格
     */
    int getPrice(long hostelId, RoomType type, Date inDate, Date outDate, int vipLevel);
}
