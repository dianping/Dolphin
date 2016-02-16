package com.dianping.paas.controller.message.facade.impl;

import com.dianping.paas.controller.message.facade.RepositoryFacade;
import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.message.nats.request.RepositoryRequester;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeoutException;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/16.
 */
@Component
public class RepositoryFacadeImpl implements RepositoryFacade {

    @Resource
    private RepositoryRequester repositoryRequester;

    @Override
    public AllocateWebPackageResponse allocate(String appId, String appVersion, String md5) throws TimeoutException {

        AllocateWebPackageRequest allocateWebPackageRequest = new AllocateWebPackageRequest();
        allocateWebPackageRequest.setAppId(appId);
        allocateWebPackageRequest.setAppVersion(appVersion);
        return repositoryRequester.allocateWebPackage(allocateWebPackageRequest);
    }

    @Override
    public void backup() {

    }

    @Override
    public void deleteWar() {

    }
}
