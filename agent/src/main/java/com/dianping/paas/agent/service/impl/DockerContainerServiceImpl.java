package com.dianping.paas.agent.service.impl;

import com.dianping.paas.agent.context.CreateContainerContext;
import com.dianping.paas.agent.context.RestartContainerContext;
import com.dianping.paas.agent.context.StartContainerContext;
import com.dianping.paas.agent.context.support.PostProcessorContainerDelegate;
import com.dianping.paas.agent.service.DockerContainerService;
import com.dianping.paas.core.dto.request.InstanceRemoveRequest;
import com.dianping.paas.core.dto.request.InstanceRestartRequest;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.request.InstanceStopRequest;
import com.dianping.paas.core.dto.response.InstanceRemoveResponse;
import com.dianping.paas.core.dto.response.InstanceRestartResponse;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import com.dianping.paas.core.dto.response.InstanceStopResponse;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.*;
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

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private DockerClient dockerClient;

    @Resource
    private PostProcessorContainerDelegate postProcessorContainerDelegate;

    public boolean createContainer(InstanceStartRequest request, InstanceStartResponse response) {
        logger.info(String.format("begin createContainer: %s", request));

        String imageId = request.getImageId();
        String containerId;
        try {
            // 1. create cmd
            CreateContainerCmd containerCmd = dockerClient.createContainerCmd(imageId);

            // 2. invoke post processor before create container
            CreateContainerContext createContainerContext = buildCreateContainerContext(request, containerCmd);
            applyCreateContainerProcessorsBeforeCreate(createContainerContext);

            // 3. exec cmd
            CreateContainerResponse createContainerResponse = containerCmd.exec();

            // 4. invoke post processor after create container
            applyCreateContainerProcessorsAfterCreate(createContainerContext);

            containerId = createContainerResponse.getId();
            request.setContainerId(containerId);

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

    public void startContainer(InstanceStartRequest request, InstanceStartResponse response) {
        logger.info(String.format("begin startContainer: %s", request));

        try {
            // 1. create cmd
            StartContainerCmd startContainerCmd = dockerClient.startContainerCmd(request.getContainerId());

            // 2. invoke post processor before start container
            StartContainerContext startContainerContext = buildStartContainerContext(request, startContainerCmd);
            applyStartContainerProcessorsBeforeStart(startContainerContext);

            // 3. exec cmd
            startContainerCmd.exec();

            // 4. invoke post processor after start container
            applyStartContainerProcessorsAfterStart(startContainerContext);

            response.success();
        } catch (Exception e) {
            response.fail(e.toString());
            logger.error(String.format("error startContainer: %s", request), e);
        }

        logger.info(String.format("end startContainer: %s", response));
    }

    public void restartContainer(InstanceRestartRequest request, InstanceRestartResponse response) {
        logger.info(String.format("begin restartContainer: %s", request));

        try {
            // 1. create cmd
            RestartContainerCmd restartContainerCmd = dockerClient.restartContainerCmd(request.getContainerId());

            // 2. invoke post processors before restart container
            RestartContainerContext context = buildRestartContainerContext(request, restartContainerCmd);
            applyRestartContainerProcessorsBeforeRestart(context);

            // 3. exec cmd
            restartContainerCmd.exec();

            // 4. invoke post processors after restart container
            applyRestartContainerProcessorsAfterRestart(context);

            response.success();
        } catch (Exception e) {
            response.fail(e.toString());
            logger.error(String.format("restartInstance error: %s", request), e);
        }

        logger.info(String.format("end restartContainer: %s", response));
    }

    @Override
    public void stopContainer(InstanceStopRequest request, InstanceStopResponse response) {
        logger.info(String.format("begin stopContainer: %s", request));

        try {
            // 1. create cmd
            StopContainerCmd stopContainerCmd = dockerClient.stopContainerCmd(request.getContainerId());

            // 2. invoke post processors before stopContainer container

            // 3. exec cmd
            stopContainerCmd.exec();

            // 4. invoke post processors after stopContainer container


            response.success();
        } catch (Exception e) {
            response.fail(e.toString());
            logger.error(String.format("stopContainer error: %s", request), e);
        }

        logger.info(String.format("end stopContainer: %s", response));
    }

    @Override
    public void removeContainer(InstanceRemoveRequest request, InstanceRemoveResponse response) {
        logger.info(String.format("begin removeContainer: %s", request));

        try {
            // 1. create cmd
            RemoveContainerCmd removeContainerCmd = dockerClient.removeContainerCmd(request.getContainerId()).withForce();

            // 2. invoke post processors before restart container

            // 3. exec cmd
            removeContainerCmd.exec();

            // 4. invoke post processors after restart container

            response.success();
        } catch (Exception e) {
            response.fail(e.toString());
            logger.error(String.format("removeContainer error: %s", request), e);
        }

        logger.info(String.format("end removeContainer: %s", response));
    }

    private void applyCreateContainerProcessorsBeforeCreate(CreateContainerContext context) {
        postProcessorContainerDelegate.postProcessBeforeCreateContainer(context);
    }

    private void applyCreateContainerProcessorsAfterCreate(CreateContainerContext context) {
        postProcessorContainerDelegate.postProcessAfterCreateContainer(context);
    }

    private void applyStartContainerProcessorsBeforeStart(StartContainerContext context) {
        postProcessorContainerDelegate.postProcessBeforeStartContainer(context);
    }

    private void applyStartContainerProcessorsAfterStart(StartContainerContext context) {
        postProcessorContainerDelegate.postProcessAfterStartContainer(context);
    }

    private void applyRestartContainerProcessorsBeforeRestart(RestartContainerContext context) {
        postProcessorContainerDelegate.postProcessBeforeRestartContainer(context);
    }


    private void applyRestartContainerProcessorsAfterRestart(RestartContainerContext context) {
        postProcessorContainerDelegate.postProcessAfterRestartContainer(context);
    }

    private CreateContainerContext buildCreateContainerContext(InstanceStartRequest instanceStartRequest, CreateContainerCmd containerCmd) {
        CreateContainerContext createContainerContext = new CreateContainerContext();

        createContainerContext.setCreateContainerCmd(containerCmd);
        createContainerContext.setInstanceStartRequest(instanceStartRequest);

        return createContainerContext;
    }


    private StartContainerContext buildStartContainerContext(InstanceStartRequest request, StartContainerCmd startContainerCmd) {
        StartContainerContext containerContext = new StartContainerContext();

        containerContext.setInstanceStartRequest(request);
        containerContext.setStartContainerCmd(startContainerCmd);

        return containerContext;
    }

    private RestartContainerContext buildRestartContainerContext(InstanceRestartRequest request, RestartContainerCmd restartContainerCmd) {
        RestartContainerContext restartContainerContext = new RestartContainerContext();

        restartContainerContext.setInstanceRestartRequest(request);
        restartContainerContext.setRestartContainerCmd(restartContainerCmd);

        return restartContainerContext;
    }

}
