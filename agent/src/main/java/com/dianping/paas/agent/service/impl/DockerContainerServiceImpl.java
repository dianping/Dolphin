package com.dianping.paas.agent.service.impl;

import com.dianping.paas.agent.service.DockerContainerService;
import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dto.request.InstanceRestartRequest;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.response.InstanceRestartResponse;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.AccessMode;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Volume;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by yuchao on 12/4/15.
 */
@Component
public class DockerContainerServiceImpl implements DockerContainerService {

    private static final Logger logger = LogManager.getLogger(DockerContainerServiceImpl.class);

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private DockerClient dockerClient;

    public boolean createContainer(InstanceStartRequest request, InstanceStartResponse response) {
        logger.info(String.format("begin createContainer: %s", request));

        String imageId = request.getImageId();
        String containerId;
        try {

            CreateContainerCmd containerCmd = dockerClient.createContainerCmd(imageId);

            // TODO ======define startContainerContest && then use chain mode=======
            Bind bind = createBind(request);
            containerCmd = containerCmd.withBinds(bind);
            // TODO ==========end=================

            CreateContainerResponse createContainerResponse = containerCmd.exec();
            containerId = createContainerResponse.getId();
            response.setContainerId(containerId);

            if (containerId == null) {
                response.fail("create container failed, created containerId is null!");
            } else {
                response.success();
            }
        } catch (Exception e) {
            response.fail(e.toString());
            logger.error(String.format("error createContainer: %s", request), e);
        }


        logger.info(String.format("end createContainer: %s", response));

        return response.isSuccess();
    }

    private Bind createBind(InstanceStartRequest request) {
        String outer = configManager.getOuterWebPackageRootDir(request.getAppName(), request.getInstanceIndex());
        Volume inner = new Volume(configManager.getInnerWebPackageRootDir());

        return new Bind(outer, inner, AccessMode.rw);
    }

    public void startContainer(InstanceStartRequest request, InstanceStartResponse response) {
        logger.info(String.format("begin startContainer: %s", request));

        try {
            dockerClient.startContainerCmd(response.getContainerId()).exec();
            response.success();
        } catch (Exception e) {
            response.fail(e.toString());
            logger.error(String.format("error startContainer: %s", request), e);
        }

        logger.info(String.format("end startContainer: %s", response));
    }

    public void restartContainer(InstanceRestartRequest request, InstanceRestartResponse response) {
        logger.info(String.format("begin startContainer: %s", request));

        try {
            dockerClient.restartContainerCmd(request.getContainerId()).exec();
            response.success();
        } catch (Exception e) {
            response.fail(e.toString());
            logger.error(String.format("restartInstance error: %s", request), e);
        }

        logger.info(String.format("end startContainer: %s", response));
    }
}
