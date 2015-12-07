package com.dianping.paas.core.dao;

import com.dianping.paas.core.entity.AppEntity;

import java.util.List;

/**
 * Created by yuchao on 15/11/2.
 */
public interface AppDao {

    AppEntity getByAppId(String appId);

    List<AppEntity> findAll();
}
