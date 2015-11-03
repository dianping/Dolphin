package com.dianping.paas.dao.support;

import com.dianping.paas.entity.Entity;

import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 11/3/15.
 */
public interface CrudDao<T extends Entity> {
    Long save(T entity);

    int delete(Long id);

    List<T> findAll();

    T findOne(Long id);

    int update(T entity);

    String getNamespace();

    GenericDaoSupport getGenericDaoSupport();
}
