package com.dianping.paas.core.service;

import com.dianping.paas.core.dto.AppInfo;
import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.entity.AppEntity;

import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 15/11/3.
 */
public interface AppService {
    List<AppEntity> getAll();

    void init(AppInfo appInfo);
}
