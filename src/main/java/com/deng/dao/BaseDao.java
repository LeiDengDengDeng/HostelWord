package com.deng.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by deng on 2017/2/22.
 */
public interface BaseDao<T> {
    Serializable save(T object);

    void update(T object);

    T load(Class<T> objectClass,Serializable id);

    T get(Class<T> objectClass,Serializable id);

    void delete(T object);
}
