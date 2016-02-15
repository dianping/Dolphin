package com.dianping.paas.core.dal.dao;

import com.dianping.paas.core.dal.entity.AppEntity;

import java.util.List;

/**
 * Created by yuchao on 15/11/2.
 */
public interface AppDao {

    List<AppEntity> findAll();

    AppEntity findByAppId(String appId);

    long insert(AppEntity app);
}
