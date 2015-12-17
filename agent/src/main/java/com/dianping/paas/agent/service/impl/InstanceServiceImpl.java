package com.dianping.paas.agent.service.impl;

import com.dianping.paas.agent.service.DockerContainerService;
import com.dianping.paas.agent.service.DockerImageService;
import com.dianping.paas.agent.service.InstanceService;
import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dto.request.*;
import com.dianping.paas.core.dto.response.InstanceRemoveResponse;
import com.dianping.paas.core.dto.response.InstanceRestartResponse;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import com.dianping.paas.core.dto.response.InstanceStopResponse;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.core.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipInputStream;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/02 14:59.
 */
@Component
public class InstanceServiceImpl implements InstanceService {
    private static final Logger logger = LogManager.getLogger(InstanceServiceImpl.class);

    @Resource
    private DockerImageService dockerImageService;

    @Resource
    private DockerContainerService dockerContainerService;

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);


    @Override
    public InstanceStartResponse pullImageAndRun(final InstanceStartRequest request) {
        InstanceStartResponse response = new InstanceStartResponse();

        if (pullImage(request, response)) {
            runImage(request, response);
        }

        return response;
    }

    private boolean pullImage(InstanceStartRequest request, InstanceStartResponse response) {
        logger.info(String.format("begin pullImage: %s", request));

        dockerImageService.pull(request, response);

        logger.info(String.format("end pullImage: %s", response));

        return response.isSuccess();
    }

    private void runImage(InstanceStartRequest request, InstanceStartResponse response) {
        logger.info(String.format("begin runImage: %s", request));

        dockerImageService.run(request, response);

        logger.info(String.format("end runImage: %s", response));
    }

    @Override
    public InstanceStartResponse startInstance(InstanceStartRequest request) {
        logger.info(String.format("begin startInstance: %s", request));

        InstanceStartResponse response = new InstanceStartResponse();

        dockerContainerService.startContainer(request, response);

        logger.info(String.format("end startInstance: %s", response));

        return response;
    }

    @Override
    public InstanceRestartResponse restartInstance(InstanceRestartRequest request) {
        logger.info(String.format("begin restartInstance: %s", request));

        InstanceRestartResponse response = new InstanceRestartResponse();

        dockerContainerService.restartContainer(request, response);

        logger.info(String.format("end restartInstance: %s", response));

        return response;
    }

    @Override
    public InstanceStopResponse stopInstance(InstanceStopRequest request) {
        logger.info(String.format("begin stopInstance: %s", request));

        InstanceStopResponse response = new InstanceStopResponse();

        dockerContainerService.stopContainer(request, response);

        logger.info(String.format("end stopInstance: %s", response));

        return response;
    }

    @Override
    public InstanceRemoveResponse removeInstance(InstanceRemoveRequest request) {
        logger.info(String.format("begin removeInstance: %s", request));

        InstanceRemoveResponse response = new InstanceRemoveResponse();

        dockerContainerService.removeContainer(request, response);

        logger.info(String.format("end removeInstance: %s", response));

        return response;
    }


    public void upgradeInstance(InstanceUpgradeRequest request) throws IOException {
        // 0. find dir of web package
        String webPackageRootDir = locateWebPackageRootDir(request);

        // 1. downLoad web package
        downLoadWebPackage(webPackageRootDir, request.getWebPackageUrl());

        // 2. restart container
        restartInstance(buildInstanceRestartRequest(request));

    }

    @Override
    public void scaleInstance(InstanceScaleRequest instanceScaleRequest) {
        // only call pullImage and run
        pullImageAndRun(instanceScaleRequest);
    }

    private void downLoadWebPackage(String webPackageRootDir, String webPackageUrl) throws IOException {
        logger.info(String.format("begin downLoadWebPackage: %s->%s", webPackageUrl, webPackageRootDir));

        URL url = new URL(webPackageUrl);
        ZipInputStream zipInputStream = new ZipInputStream(url.openStream());
        FileUtil.unZip(zipInputStream, webPackageRootDir);

        logger.info(String.format("end downLoadWebPackage: %s->%s", webPackageUrl, webPackageRootDir));
    }

    private InstanceRestartRequest buildInstanceRestartRequest(InstanceUpgradeRequest request) {
        InstanceRestartRequest instanceRestartRequest = new InstanceRestartRequest();

        instanceRestartRequest.setContainerId(request.getInstance_id());

        return instanceRestartRequest;
    }

    private String locateWebPackageRootDir(InstanceUpgradeRequest request) {

        return configManager.getOuterWebPackageRootDir(request.getApp_id(), request.getInstance_index());
    }
}


