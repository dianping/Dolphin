package com.dianping.paas.core.service;

import com.dianping.paas.core.dto.request.AppInitRequest;
import com.dianping.paas.core.entity.AppEntity;

import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 15/11/3.
 */
public interface AppService {
    List<AppEntity> getAll();

    void init(AppInitRequest appInfo);

}
