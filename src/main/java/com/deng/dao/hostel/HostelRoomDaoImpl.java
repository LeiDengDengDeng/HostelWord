package com.deng.dao.hostel;

import com.deng.dao.BaseDaoImpl;
import com.deng.pojo.hostel.HostelRoom;
import com.deng.pojo.hostel.RoomType;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/3/3.
 */
@Component("HostelRoomDaoImpl")
public class HostelRoomDaoImpl extends BaseDaoImpl<HostelRoom> {
    /**
     * 根据酒店id和房间号获得房间信息
     *
     * @param hostelId 酒店id
     * @param roomNum  房间号
     * @return 对应房间信息
     */
    public HostelRoom getHostelRoomByHostelAndRoomNum(long hostelId, int roomNum) {
        String hql = "from com.deng.pojo.hostel.HostelRoom r where r.hostel.id =:id and r.roomNum =:roomNum";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", hostelId);
        query.setInteger("roomNum", roomNum);
        return (HostelRoom) query.uniqueResult();
    }

    /**
     * 根据对应酒店下所有房间情况
     *
     * @param hostelId 酒店ID
     * @return 对应酒店下所有房间情况
     */
    public List<HostelRoom> getHostelRooms(long hostelId) {
        String hql = "from com.deng.pojo.hostel.HostelRoom r where r.hostel.id =:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", hostelId);
        return query.list();
    }

    /**
     * 根据房间类型获得对应酒店下所有该类型的房间
     *
     * @param hostelId 酒店ID
     * @param type     房间类型
     * @return 对应酒店下所有该类型的房间
     */
    public List<HostelRoom> getHostelRoomByType(long hostelId, RoomType type) {
        String hql = "from com.deng.pojo.hostel.HostelRoom r where r.hostel.id =:id " +
                "and r.roomType =:type";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", hostelId);
        query.setString("type", type.toString());
        return query.list();
    }
}
