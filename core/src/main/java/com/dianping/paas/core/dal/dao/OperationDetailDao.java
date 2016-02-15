package com.dianping.paas.core.dal.dao;


import com.dianping.paas.core.dal.entity.OperationDetailEntity;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/18.
 */
public interface OperationDetailDao {
    long insert(OperationDetailEntity operationDetail);

    int updateRawLogAndMsg(OperationDetailEntity operationDetail);
}
