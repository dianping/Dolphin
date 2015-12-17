package com.dianping.paas.controller.service;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/17 15:47.
 */
public interface InstanceControllerService {
    void scaleInstance(String appId, int count);
}
