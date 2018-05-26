package com.deng.dao.member;

import com.deng.dao.BaseDaoImpl;
import com.deng.pojo.member.Member;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by deng on 2017/3/9.
 */
@Component("MemberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member> {
    public List<Member> getAllNormalMember() {
        String hql = "from com.deng.pojo.member.Member m where m.state = 'NORMAL'";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
