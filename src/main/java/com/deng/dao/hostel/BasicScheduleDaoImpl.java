package com.deng.dao.hostel;

import com.deng.dao.BaseDaoImpl;
import com.deng.pojo.hostel.BasicSchedule;
import com.deng.pojo.hostel.RoomType;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by deng on 2017/2/23.
 */
@Component("BasicScheduleDaoImpl")
public class BasicScheduleDaoImpl extends BaseDaoImpl<BasicSchedule> {
    /**
     * 获得最新一年的对应酒店和对应房间类型的酒店基本计划
     *
     * @param hostelId 酒店ID
     * @param roomType 房间类型
     * @return 最新一年的对应酒店和对应房间类型的酒店基本计划
     */
    public BasicSchedule getBasisSchedulesByHostelIdAndRoomType(long hostelId, RoomType roomType) {
        String hql = "from com.deng.pojo.hostel.BasicSchedule bs where bs.hostel.id =:id and bs.year = (select max(s.year) from basicschedule s) and bs.roomType =:roomType";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", hostelId);
        query.setString("roomType", roomType.toString());
        return (BasicSchedule) query.uniqueResult();
    }

    /**
     * 获得最新一年的对应酒店的酒店基本计划
     *
     * @param hostelId 酒店ID
     * @return 最新一年的对应酒店的酒店基本计划
     */
    public List<BasicSchedule> getBasisSchedulesByHostelId(long hostelId) {
        String hql = "from com.deng.pojo.hostel.BasicSchedule bs where bs.hostel.id =:id and bs.year = (select max(s.year) from basicschedule s)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", hostelId);
        return query.list();
    }
}
