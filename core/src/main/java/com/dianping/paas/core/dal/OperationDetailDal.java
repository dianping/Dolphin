package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.OperationDetailDao;
import com.dianping.paas.core.dal.entity.OperationDetailEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
@Repository
public class OperationDetailDal {

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OperationDetailDao operationDetailDao;

    public long insert(OperationDetailEntity operationDetail) {
        return operationDetailDao.insert(operationDetail);
    }

    public int updateRawLogAndMsg(OperationDetailEntity operationDetail) {
        return operationDetailDao.updateRawLogAndMsg(operationDetail);
    }
}
