package com.dianping.paas.controller.service;


import com.dianping.paas.core.dal.entity.AppEntity;
import com.dianping.paas.core.dto.request.AppInitRequest;
import com.dianping.paas.core.dto.response.AsyncOperationResponse;

import java.util.List;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/11.
 */
public interface AppService {

    AsyncOperationResponse initApp(AppInitRequest appInitRequest);

    List<AppEntity> findAllApp();
}
