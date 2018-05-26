package com.deng.dao.hostel;

import com.deng.dao.BaseDaoImpl;
import com.deng.pojo.hostel.BasicSchedule;
import com.deng.pojo.hostel.RoomType;
import com.deng.pojo.hostel.SpecialSchedule;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2/19 0019.
 */
@Component("SpecialScheduleDaoImpl")
public class SpecialScheduleDaoImpl extends BaseDaoImpl<SpecialSchedule> {
    public List<SpecialSchedule> getSpecialScheduleByHostelId(long hostelId) {
        String hql = "from com.deng.pojo.hostel.SpecialSchedule ss where ss.hostel.id =:id and ss.date>=:todayDate)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", hostelId);
        query.setDate("todayDate", new Date());
        return query.list();
    }

    public SpecialSchedule getSpecialSchedulesByHostelIdAndRoomTypeAndDate(long hostelId, RoomType roomType, Date date) {
        String hql = "from com.deng.pojo.hostel.SpecialSchedule ss where ss.hostel.id =:id and ss.roomType =:roomType and ss.date=:date";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", hostelId);
        query.setString("roomType", roomType.toString());
        query.setDate("date", date);
        return (SpecialSchedule) query.uniqueResult();
    }
}
