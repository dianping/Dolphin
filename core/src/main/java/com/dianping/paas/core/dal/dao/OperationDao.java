package com.dianping.paas.core.dal.dao;

import com.dianping.paas.core.dal.entity.OperationEntity;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/18.
 */
public interface OperationDao {
    long insert(OperationEntity operation);

    int updateOperation(OperationEntity operation);
}
