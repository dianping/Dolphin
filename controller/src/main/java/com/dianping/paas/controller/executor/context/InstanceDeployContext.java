package com.dianping.paas.controller.executor.context;

import com.dianping.paas.core.dal.entity.AppConfigEntity;
import com.dianping.paas.core.dal.entity.AppEntity;
import com.dianping.paas.core.dal.entity.AppVersionEntity;
import com.dianping.paas.core.dal.entity.QuotaEntity;
import lombok.Data;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/2.
 */
@Data
public class InstanceDeployContext {
    private AppVersionEntity appVersion;

    private URL appFile;

    private QuotaEntity quota;

    private AppEntity app;

    private long instanceGroupId;

    private Map<String, String> env = new ConcurrentHashMap<String, String>();

    private AppConfigEntity appConfig;

    private String ldapBase;

    private String phoenixKernelVersion;

    private String phoenixKernelGitUrl;

    private String instanceIp;

    private Boolean tryOnline;

    private Long instanceIndex;
}
