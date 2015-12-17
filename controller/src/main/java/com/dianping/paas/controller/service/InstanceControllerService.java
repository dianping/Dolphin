package com.dianping.paas.controller.service;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/17 15:47.
 */
public interface InstanceControllerService {
    void startAllInstances(String appId);

    void stopAllInstances(String appId);

    void scaleInstance(String appId, int count);

    void removeAllInstances(String appId);

    void restartAllInstances(String appId);
}
