package com.dianping.paas.core.service.impl;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dao.AppDao;
import com.dianping.paas.core.dto.request.AppInitRequest;
import com.dianping.paas.core.dto.request.DockerfileRequest;
import com.dianping.paas.core.entity.AppEntity;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.core.message.nats.request.RepositoryRequester;
import com.dianping.paas.core.service.AppService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 15/11/3.
 */
@Service
public class AppServiceImpl implements AppService {
    private static final Logger logger = LogManager.getLogger(AppServiceImpl.class);
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AppDao appDao;

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);

    @Resource
    private RepositoryRequester repositoryRequester;

    public List<AppEntity> getAll() {
        return appDao.findAll();
    }

    public void init(final AppInitRequest appInfo) {
        logger.info(String.format("begin init app: %s", appInfo));

        repositoryRequester.newAndDeploy(buildDockerfileRequest(appInfo));
    }

    private DockerfileRequest buildDockerfileRequest(AppInitRequest appInfo) {
        DockerfileRequest dockerfileRequest = new DockerfileRequest();

        Map<String, Object> params = buildParams(appInfo);

        dockerfileRequest.setAppName(appInfo.getAppId());
        dockerfileRequest.setDockerfileLocation(configManager.getDockerfileLocation(appInfo.getAppId()));
        dockerfileRequest.setDockerfileTemplateLocation(configManager.getDockerfileTemplateDir(appInfo.getImageType()));
        dockerfileRequest.setDockerfileParams(params);

        return dockerfileRequest;
    }

    // TODO
    private Map<String, Object> buildParams(AppInitRequest appInfo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("path", String.format("/tmp/1.txt", appInfo.getAppId()));

        return params;
    }
}
