package com.dianping.paas.controller.service;

import com.dianping.paas.core.dto.request.InstanceFilterRequest;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/17 15:47.
 */
public interface InstanceControllerService {

    void startAllInstances(String appId);

    void startFilteredInstances(InstanceFilterRequest instanceFilterRequest);


    void stopAllInstances(String appId);

    void stopFilteredInstances(InstanceFilterRequest instanceFilterRequest);


    void restartAllInstances(String appId);

    void restartFilteredInstances(InstanceFilterRequest instanceFilterRequest);


    void scaleInstance(String appId, int count);

    void removeAllInstances(String appId);
}
