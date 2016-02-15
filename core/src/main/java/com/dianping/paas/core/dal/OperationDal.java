package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.OperationDao;
import com.dianping.paas.core.dal.entity.OperationEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/18.
 */
@Repository
public class OperationDal {
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OperationDao operationDao;

    public long insert(OperationEntity operation) {
       return operationDao.insert(operation);
    }

    public int updateOperation(OperationEntity operation) {
        return operationDao.updateOperation(operation);
    }
}
