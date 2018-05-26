package com.deng.dao.hostel;

import com.deng.dao.BaseDaoImpl;
import com.deng.pojo.hostel.Hostel;
import com.deng.pojo.hostel.HostelState;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by deng on 2017/2/23.
 */
@Component("HostelDaoImpl")
public class HostelDaoImpl extends BaseDaoImpl<Hostel> {
    public List<Hostel> getExaminingHostels() {
        String hql = "from com.deng.pojo.hostel.Hostel hostel where hostel.state=:state";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("state", "EXAMINING");
        return query.list();
    }

    public List<Hostel> getRunningHostels() {
        String hql = "from com.deng.pojo.hostel.Hostel hostel where hostel.state=:state";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("state","NORMAL");
        return query.list();
    }
}
