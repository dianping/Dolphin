package com.dianping.paas.controller.service.impl;

import com.dianping.paas.controller.service.GroupControllerService;
import com.dianping.paas.core.dal.InstanceDal;
import com.dianping.paas.core.dal.entity.InstanceEntity;
import com.dianping.paas.core.dto.request.InstanceUpgradeRequest;
import com.dianping.paas.core.message.nats.request.AgentRequester;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuchao on 12/7/15.
 */
@Service
public class GroupControllerServiceImpl implements GroupControllerService {
    @Resource
    private AgentRequester agentRequester;

    @Resource
    private InstanceDal instanceDal;

    public void upgradeInstances(String app_id, String app_version) {
        List<InstanceUpgradeRequest> requests = buildUpgradeInstanceRequests(app_id, app_version);

        for (InstanceUpgradeRequest request : requests) {
            agentRequester.upgradeInstance(request);
        }
    }

    private List<InstanceUpgradeRequest> buildUpgradeInstanceRequests(String app_id, String app_version) {
        List<InstanceUpgradeRequest> requests = Lists.newArrayList();

        List<InstanceEntity> instanceEntityList = instanceDal.getRunningInstances(app_id, app_version);
        String webPackageUrl = getWebPackageUrl(app_id, app_version);

        for (InstanceEntity instanceEntity : instanceEntityList) {
            InstanceUpgradeRequest request = new InstanceUpgradeRequest();
            request.setApp_id(app_id);
            request.setWebPackageUrl(webPackageUrl);
            request.setApp_version(app_version);
            request.setInstance_index(instanceEntity.getInstance_index());
            request.setAgent_ip(instanceEntity.getAgent_ip());
            requests.add(request);
        }

        return requests;
    }

    //TODO
    private String getWebPackageUrl(String app_id, String app_version) {

        return "http://localhost:8080/v1/repository/webpackages?token=token";
    }
}
