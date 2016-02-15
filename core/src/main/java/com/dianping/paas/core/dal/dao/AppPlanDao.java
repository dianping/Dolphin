package com.dianping.paas.core.dal.dao;

import com.dianping.paas.core.dal.entity.AppPlanEntity;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
public interface AppPlanDao {

    AppPlanEntity findByPK(long id);
}
