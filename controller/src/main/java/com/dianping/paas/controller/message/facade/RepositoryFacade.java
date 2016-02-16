package com.dianping.paas.controller.message.facade;

import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;

import java.util.concurrent.TimeoutException;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/16.
 */
public interface RepositoryFacade {

    AllocateWebPackageResponse allocate(String appId, String appVersion, String md5) throws TimeoutException;

    void backup();

    void deleteWar();

}
