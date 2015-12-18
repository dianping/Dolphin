package com.dianping.paas.controller.service.impl;

import com.dianping.paas.controller.service.InstanceControllerService;
import com.dianping.paas.core.dal.InstanceDal;
import com.dianping.paas.core.dal.entity.InstanceEntity;
import com.dianping.paas.core.dto.request.*;
import com.dianping.paas.core.message.nats.request.AgentRequester;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/17 15:48.
 */
@Service
public class InstanceControllerServiceImpl implements InstanceControllerService {
    @Resource
    private AgentRequester agentRequester;

    @Resource
    private InstanceDal instanceDal;


    //  ________________________________________________________________________
    // |                                                                        |
    // | start some instances                                                   |
    // |________________________________________________________________________|
    //

    @Override
    public void startAllInstances(String appId) {
        startInstances(getInstanceByAppId(appId));
    }

    @Override
    public void startFilteredInstances(InstanceFilterRequest instanceFilterRequest) {
        startInstances(getFilteredInstanceList(instanceFilterRequest));
    }

    private void startInstances(List<InstanceEntity> instanceEntityList) {
        List<InstanceStartRequest> instanceStartRequestList = buildInstanceStartRequest(instanceEntityList);

        for (InstanceStartRequest instanceStartRequest : instanceStartRequestList) {
            agentRequester.startInstance(instanceStartRequest);
        }
    }

    /**
     * TODO 验证, 查数据库, 构造nats消息
     *
     * @param instanceEntityList
     */
    private List<InstanceStartRequest> buildInstanceStartRequest(List<InstanceEntity> instanceEntityList) {
        List<InstanceStartRequest> instanceStartRequestList = Lists.newArrayList();
        // ...

        return instanceStartRequestList;
    }


    //  ________________________________________________________________________
    // |                                                                        |
    // | stop some instances                                                    |
    // |________________________________________________________________________|
    //

    @Override
    public void stopAllInstances(String appId) {
        stopInstances(getInstanceByAppId(appId));
    }

    @Override
    public void stopFilteredInstances(InstanceFilterRequest instanceFilterRequest) {
        stopInstances(getFilteredInstanceList(instanceFilterRequest));
    }

    private void stopInstances(List<InstanceEntity> instanceEntityList) {
        List<InstanceStopRequest> instanceStopRequestList = buildInstanceStopRequest(instanceEntityList);

        for (InstanceStopRequest instanceScaleRequest : instanceStopRequestList) {
            agentRequester.stopInstance(instanceScaleRequest);
        }
    }

    /**
     * TODO 验证, 查数据库, 构造nats消息
     */
    private List<InstanceStopRequest> buildInstanceStopRequest(List<InstanceEntity> instanceEntityList) {
        List<InstanceStopRequest> instanceStopRequestList = Lists.newArrayList();
        // ...

        return instanceStopRequestList;
    }


    //  ________________________________________________________________________
    // |                                                                        |
    // | restart some instances                                                 |
    // |________________________________________________________________________|
    //

    @Override
    public void restartAllInstances(String appId) {
        restartInstances(getInstanceByAppId(appId));
    }

    @Override
    public void restartFilteredInstances(InstanceFilterRequest instanceFilterRequest) {

        restartInstances(getFilteredInstanceList(instanceFilterRequest));
    }

    private void restartInstances(List<InstanceEntity> instanceEntityList) {
        List<InstanceRestartRequest> instanceRestartRequestList = buildInstanceRestartRequest(instanceEntityList);

        for (InstanceRestartRequest instanceRestartRequest : instanceRestartRequestList) {
            agentRequester.restartInstance(instanceRestartRequest);
        }
    }

    /**
     * TODO 验证, 查数据库, 构造nats消息
     */
    private List<InstanceRestartRequest> buildInstanceRestartRequest(List<InstanceEntity> instanceEntityList) {
        List<InstanceRestartRequest> instanceRestartRequestList = Lists.newArrayList();
        // ...

        return instanceRestartRequestList;
    }

    @Override
    public void removeAllInstances(String appId) {
        List<InstanceRemoveRequest> instanceRemoveRequestList = buildInstanceRemoveRequest(appId);

        for (InstanceRemoveRequest instanceRemoveRequest : instanceRemoveRequestList) {
            agentRequester.removeInstance(instanceRemoveRequest);
        }
    }

    /**
     * TODO 验证, 查数据库, 构造nats消息
     */
    private List<InstanceRemoveRequest> buildInstanceRemoveRequest(String appId) {
        List<InstanceRemoveRequest> instanceRemoveRequestList = Lists.newArrayList();
        // ...

        return instanceRemoveRequestList;
    }

    @Override
    public void scaleInstance(String appId, int count) {
        List<InstanceScaleRequest> instanceScaleRequestList = buildInstanceScaleRequest(appId, count);

        for (InstanceScaleRequest instanceScaleRequest : instanceScaleRequestList) {
            agentRequester.scaleInstance(instanceScaleRequest);
        }
    }

    /**
     * TODO 验证, 插数据库, 构造nats消息
     */
    private List<InstanceScaleRequest> buildInstanceScaleRequest(String appId, int count) {
        List<InstanceScaleRequest> instanceScaleRequestList = Lists.newArrayList();

        for (int i = 0; i < count; i++) {
            InstanceScaleRequest instanceScaleRequest = new InstanceScaleRequest();
            instanceScaleRequest.setAppId(appId);
            //instanceScaleRequest.setImageId();
            // ...

            instanceScaleRequestList.add(instanceScaleRequest);
        }

        return instanceScaleRequestList;
    }


    private List<InstanceEntity> getInstanceByAppId(String appId) {

        return instanceDal.getInstanceByAppId(appId);
    }

    private List<InstanceEntity> getFilteredInstanceList(InstanceFilterRequest instanceFilterRequest) {

        return instanceDal.getFilteredInstanceList(instanceFilterRequest);
    }
}
