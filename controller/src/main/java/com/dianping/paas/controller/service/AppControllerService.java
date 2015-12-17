package com.dianping.paas.controller.service;


import com.dianping.paas.core.dto.request.AppInitRequest;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/11.
 */
public interface AppControllerService {

    long initApp(AppInitRequest appInitRequest);

}
