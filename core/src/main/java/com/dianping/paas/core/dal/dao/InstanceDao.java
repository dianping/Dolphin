package com.dianping.paas.core.dal.dao;

import com.dianping.paas.core.dal.entity.InstanceEntity;

import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/15 14:11.
 */
public interface InstanceDao {
    List<InstanceEntity> countNonRemovedInstanceByGroupId(long groupId);

    List<InstanceEntity> findUpgradeableInstances(String appId);

    long insert(InstanceEntity instance);
}
