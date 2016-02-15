package com.dianping.paas.controller.record.impl;

import com.dianping.paas.controller.dto.depoly.entity.HostOperation;
import com.dianping.paas.controller.dto.depoly.entity.OperationContext;
import com.dianping.paas.controller.record.OperationListener;
import com.dianping.paas.controller.record.OperationRecorder;
import com.dianping.paas.core.dal.InstanceDal;
import com.dianping.paas.core.dal.entity.InstanceEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */

@Component
public class OperationListenerImpl implements OperationListener {

    protected OperationContext operationContext;

    protected OperationRecorder operationRecorder;

    @Resource
    private InstanceDal instanceDal;

    @Override
    public void init(OperationContext opCtx, OperationRecorder opRecorder) {
        this.operationContext = opCtx;
        this.operationRecorder = opRecorder;
    }

    @Override
    public void onStart(int totalHosts) {
        operationRecorder.start(totalHosts);
    }

    @Override
    public void preCreate(HostOperation hostOperation) {
        InstanceEntity instance = saveInstanceBasicInfo(InstanceEntity.STATUS_CREATING, hostOperation);
        hostOperation.setDbInstanceId(instance.getId());
    }

    protected InstanceEntity saveInstanceBasicInfo(int status, HostOperation hostOperation) {
        InstanceEntity instance = new InstanceEntity();

        instance.setAppId(operationContext.getAppId());
        instance.setType(InstanceEntity.INSTANCE_TYPE_NORMAL);
        instance.setStatus(status);
        instance.setAppVersion(hostOperation.getAppVersion());
        instance.setInstanceGroupId(hostOperation.getInstanceGroupId());
        instance.setImage(hostOperation.getImage());
        instance.setInstanceIndex(hostOperation.getInstanceIndex());

        instanceDal.insert(instance);

        return instance;
    }

    @Override
    public void onSuccess() {
        operationRecorder.done(true, null);
    }

    @Override
    public void onFail() {
        operationRecorder.done(false, null);
    }
}
