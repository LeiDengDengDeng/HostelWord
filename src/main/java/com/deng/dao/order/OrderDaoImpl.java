package com.deng.dao.order;

import com.deng.dao.BaseDaoImpl;
import com.deng.pojo.order.RoomOrder;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by deng on 2017/3/17.
 */
@Repository("OrderDaoImpl")
public class OrderDaoImpl extends BaseDaoImpl<RoomOrder> {

    public List<RoomOrder> getOrdersByMemberId(long memberId) {
        String hql = "from com.deng.pojo.order.RoomOrder o where o.member.memberId =:id order by orderDate desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", memberId);
        return query.list();
    }

    public List<RoomOrder> getOrdersByHostelId(long hostelId) {
        String hql = "from com.deng.pojo.order.RoomOrder o where o.room.hostel.id =:id order by orderDate desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", hostelId);
        return query.list();
    }
}
