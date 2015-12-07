package com.dianping.paas.core.service.impl;

import com.dianping.paas.core.dto.request.UpgradeInstanceRequest;
import com.dianping.paas.core.message.nats.request.AgentRequester;
import com.dianping.paas.core.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yuchao on 12/7/15.
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private AgentRequester agentRequester;

    public void upgradeInstances(String app_id, String app_version) {
        UpgradeInstanceRequest request = buildUpgradeInstanceRequest(app_id, app_version);

        agentRequester.upgradeInstance(request);
    }

    private UpgradeInstanceRequest buildUpgradeInstanceRequest(String app_id, String app_version) {
        UpgradeInstanceRequest request = new UpgradeInstanceRequest();

        String webPackageUrl = getWebPackageUrl(app_id, app_version);

        request.setApp_id(app_id);
        request.setWebPackageUrl(webPackageUrl);
        request.setApp_version(app_version);

        return request;
    }

    //TODO
    private String getWebPackageUrl(String app_id, String app_version) {

        return "http://localhost:8080/v1/repositories/webpackages?token=token";
    }
}
