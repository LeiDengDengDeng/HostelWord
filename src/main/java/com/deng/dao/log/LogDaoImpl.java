package com.deng.dao.log;

import com.deng.dao.BaseDaoImpl;
import com.deng.pojo.log.ConsumeLog;
import com.deng.pojo.log.IncomeRecordLog;
import com.deng.pojo.log.Log;
import com.deng.pojo.log.PointLog;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by deng on 2017/3/17.
 */
@Repository("LogDaoImpl")
public class LogDaoImpl extends BaseDaoImpl<Log> {

    public List<ConsumeLog> getConsumeLogsByMemberId(long memberId) {
        String hql = "from com.deng.pojo.log.ConsumeLog l where l.member.memberId =:id order by operatetime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", memberId);
        return query.list();
    }

    public List<PointLog> getPointLogsByMemberId(long memberId) {
        String hql = "from com.deng.pojo.log.PointLog l where l.member.memberId =:id order by operatetime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", memberId);
        return query.list();
    }

    public List<Log> getOrderLogsByMemberId(long memberId) {
        String hql = "from com.deng.pojo.log.Log l where l.operatorId =:id and " +
                "(l.operationName ='预定房间' or l.operationName='取消预定') order by operatetime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("id", memberId);
        return query.list();
    }

    public List<Log> getManagerExamLogs() {
        String hql = "from com.deng.pojo.log.Log l where l.operatorId = 999999 and " +
                "l.operationName ='审批酒店信息' order by operatetime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public List<IncomeRecordLog> getManagerConsumeLogs() {
        String hql = "from com.deng.pojo.log.IncomeRecordLog l where l.operatorId = 999999 and " +
                "l.operationName ='经理资金变化' order by operatetime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
