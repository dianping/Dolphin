package com.dianping.paas.controller.record.impl;

import com.dianping.paas.controller.dto.depoly.entity.DeploymentDetail;
import com.dianping.paas.core.dal.entity.OperationDetailEntity;
import com.dianping.paas.controller.record.OperationRecorder;
import com.dianping.paas.core.dal.OperationDal;
import com.dianping.paas.core.dal.OperationDetailDal;
import com.dianping.paas.core.dal.entity.OperationEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/17.
 */
@Component
public class OperationRecorderImpl implements OperationRecorder {

    @Resource
    private OperationDal operationDal;

    private DeploymentDetail deployDetail = new DeploymentDetail();

    private OperationDetailEntity operationDetail;

    private OperationEntity operation;

    @Resource
    private OperationDetailDal operationDetailDal;

    @Override
    public long prepare(String appId, int operationType) {
        return recordOperationStart(appId, operationType);
    }

    private long recordOperationStart(String appId, int operationType) {
        operation = new OperationEntity();
        operation.setAppId(appId);
        operation.setCreationDate(new Date());
        operation.setState(OperationEntity.STATUS_IN_PROGRESS);
        operation.setType(operationType);
        return operationDal.insert(operation);
    }

    @Override
    public void start(int totalHosts) {
        deployDetail.setTotalHosts(totalHosts);

        operationDetail = new OperationDetailEntity();
        operationDetail.setOperationId(operation.getId());
        operationDetail.setTotalStep(totalHosts);
        operationDetailDal.insert(operationDetail);
    }

    @Override
    public void done(boolean success, String msg) {
        int endStatus = success ? OperationEntity.STATUS_SUCCESS : OperationEntity.STATUS_FAIL;

        operation.setState(endStatus);
        operationDal.updateOperation(operation);

        deployDetail.setStatus(endStatus);
        operationDetail.setRawLog(deployDetail.toString());
        if (msg != null) {
            operationDetail.setMsg(msg);
        }
        operationDetailDal.updateRawLogAndMsg(operationDetail);
    }
}
