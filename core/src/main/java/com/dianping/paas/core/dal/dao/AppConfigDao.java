package com.dianping.paas.core.dal.dao;

import com.dianping.paas.core.dal.entity.AppConfigEntity;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
public interface AppConfigDao {

    int insert(AppConfigEntity appConfig);

    AppConfigEntity findByAppId(String appId);
}
