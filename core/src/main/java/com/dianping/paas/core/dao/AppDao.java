package com.dianping.paas.core.dao;

import com.dianping.paas.core.entity.AppEntity;
import com.dianping.paas.core.dao.support.CrudDao;

/**
 * Created by yuchao on 15/11/2.
 */
public interface AppDao extends CrudDao<AppEntity> {

    AppEntity getByAppId(String appId);

}
