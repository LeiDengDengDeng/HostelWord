package com.deng.dao.hostel;

import com.deng.dao.BaseDaoImpl;
import com.deng.pojo.hostel.CheckInRecord;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by deng on 2017/3/20.
 */
@Repository("CheckInDaoImpl")
public class CheckInRecordDaoImpl extends BaseDaoImpl<CheckInRecord> {

    public List<CheckInRecord> getCheckInRecordsByHostelId(long hostelId){
        String hql = "from com.deng.pojo.hostel.CheckInRecord r where r.room.hostel.id =:id order by indate desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", hostelId);
        return query.list();
    }
}
