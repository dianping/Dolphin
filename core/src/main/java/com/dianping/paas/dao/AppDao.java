package com.dianping.paas.dao;

import com.dianping.paas.dao.support.CrudDao;
import com.dianping.paas.entity.AppEntity;

/**
 * Created by yuchao on 15/11/2.
 */
public interface AppDao extends CrudDao<AppEntity> {

    AppEntity getByAppId(String appId);

}
