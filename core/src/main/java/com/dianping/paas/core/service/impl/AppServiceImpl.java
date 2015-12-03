package com.dianping.paas.core.service.impl;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dao.AppDao;
import com.dianping.paas.core.dto.AppInfo;
import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.request.DockerfileRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.dto.response.DockerfileResponse;
import com.dianping.paas.core.entity.AppEntity;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.core.message.nats.MessageCallBack;
import com.dianping.paas.core.message.nats.bus.MessageBus;
import com.dianping.paas.core.message.nats.subscribe.Subject;
import com.dianping.paas.core.service.AgentService;
import com.dianping.paas.core.service.AppService;
import com.dianping.paas.core.service.ImageService;
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
    private AppDao appDao;

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);

    @Resource
    private MessageBus messageBus;

    @Resource
    private ImageService imageService;

    @Resource
    private AgentService agentService;

    public List<AppEntity> getAll() {
        return appDao.findAll();
    }

    public void init(final AppInfo appInfo) {
        final DockerfileRequest dockerfileRequest = buildDockerfileRequest(appInfo);

        messageBus.requestAsync(Subject.Instance.NEW_AND_DEPLOY, dockerfileRequest, new MessageCallBack<DockerfileResponse>(DockerfileResponse.class) {
            @Override
            public void success(DockerfileResponse dockerfileResponse) {
                logger.info("init app success, AppInfo ==> " + appInfo + ", response is " + dockerfileResponse);
                agentService.pullImageAndRun(dockerfileRequest, dockerfileResponse);
            }

            @Override
            public void error(Throwable throwable) {
                logger.error("init app error, AppInfo ==> " + appInfo, throwable);
            }

            @Override
            public void timeout() {
                logger.error("init app timeout, AppInfo ==> " + appInfo);
            }
        });

    }

    public AllocateWebPackageResponse allocateWebPackage(final AllocateWebPackageRequest request) {
        final AllocateWebPackageResponse[] allocateWebPackageResponse = new AllocateWebPackageResponse[1];

        messageBus.requestSync(Subject.Repository.ALLOCATE_WEB_PACKAGE_REQUEST, request,
                new MessageCallBack<AllocateWebPackageResponse>(AllocateWebPackageResponse.class) {
                    @Override
                    public void success(AllocateWebPackageResponse response) {
                        allocateWebPackageResponse[0] = response;
                    }

                    @Override
                    public void error(Throwable throwable) {
                        logger.error(String.format("error when allocateWebPackage, request ==>\n%s", request));
                    }

                    @Override
                    public void timeout() {
                        logger.error(String.format("timeout when allocateWebPackage, request ==>\n%s", request));
                    }
                });

        return allocateWebPackageResponse[0];
    }

    private DockerfileRequest buildDockerfileRequest(AppInfo appInfo) {
        DockerfileRequest dockerfileRequest = new DockerfileRequest();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("path", "/tmp/1.txt");

        dockerfileRequest.setAppName(appInfo.getApp_Id());
        dockerfileRequest.setDockerfileLocation(buildDockerfileLocation(appInfo));
        dockerfileRequest.setDockerfileTemplateContent(imageService.getDockerfileTemplateContent(appInfo.getImage_type()));
        dockerfileRequest.setDockerfileParams(params);

        return dockerfileRequest;
    }

    private String buildDockerfileLocation(AppInfo appInfo) {
        return String.format("%s/%s/dockerfiles/Dockerfile", configManager.getBaseWebappDir(), appInfo.getApp_Id());
    }
}
