package com.deng.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by deng on 2017/2/23.
 */
@Component
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {
    @Autowired
    protected SessionFactory sessionFactory;

    public Serializable save(T object) {
        return sessionFactory.getCurrentSession().save(object);
    }

    public void update(T object) {
        sessionFactory.getCurrentSession().update(object);
    }

    public T load(Class<T> objectClass, Serializable id) {
        return sessionFactory.getCurrentSession().load(objectClass, id);
    }

    public T get(Class<T> objectClass, Serializable id) {
        return sessionFactory.getCurrentSession().get(objectClass, id);
    }

    public void delete(T object) {
        sessionFactory.getCurrentSession().delete(object);
    }
}
