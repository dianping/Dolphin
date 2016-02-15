package com.dianping.paas.core.dal.dao;

import com.dianping.paas.core.dal.entity.InstanceGroupEntity;

import java.util.List;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/17.
 */
public interface InstanceGroupDao {
    long insert(InstanceGroupEntity instanceGroup);

    List<InstanceGroupEntity> findInstanceGroupByAppId(String appId);
}
