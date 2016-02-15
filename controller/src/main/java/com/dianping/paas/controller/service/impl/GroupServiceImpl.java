package com.dianping.paas.controller.service.impl;

import com.dianping.paas.controller.service.GroupService;
import com.dianping.paas.core.dal.InstanceDal;
import com.dianping.paas.core.dto.request.UpgradeInstanceRequest;
import com.dianping.paas.core.dal.entity.InstanceEntity;
import com.dianping.paas.core.message.nats.request.AgentRequester;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuchao on 12/7/15.
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private AgentRequester agentRequester;

    @Resource
    private InstanceDal instanceDal;

    public void upgradeInstances(String app_id, String app_version) {
        List<UpgradeInstanceRequest> requests = buildUpgradeInstanceRequests(app_id, app_version);

        for (UpgradeInstanceRequest request : requests) {
            agentRequester.upgradeInstance(request);
        }
    }

    private List<UpgradeInstanceRequest> buildUpgradeInstanceRequests(String app_id, String app_version) {
        List<UpgradeInstanceRequest> requests = new ArrayList<UpgradeInstanceRequest>();

        List<InstanceEntity> instanceEntityList = instanceDal.getRunningInstances(app_id, app_version);
        String webPackageUrl = getWebPackageUrl(app_id, app_version);

        for (InstanceEntity instanceEntity : instanceEntityList) {
            UpgradeInstanceRequest request = new UpgradeInstanceRequest();
            request.setApp_id(app_id);
            request.setWebPackageUrl(webPackageUrl);
            request.setApp_version(app_version);
            request.setInstance_index(instanceEntity.getInstanceIndex());
            request.setAgent_ip(instanceEntity.getAgentIp());
            requests.add(request);
        }

        return requests;
    }

    //TODO
    private String getWebPackageUrl(String app_id, String app_version) {

        return "http://localhost:8080/v1/repository/webpackages?token=token";
    }
}
