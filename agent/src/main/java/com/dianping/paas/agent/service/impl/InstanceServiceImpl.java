package com.dianping.paas.agent.service.impl;

import com.dianping.paas.agent.service.DockerContainerService;
import com.dianping.paas.agent.service.DockerImageService;
import com.dianping.paas.agent.service.InstanceService;
import com.dianping.paas.core.dto.request.InstanceRestartRequest;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.response.InstanceRestartResponse;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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


    public InstanceStartResponse pullImageAndRun(final InstanceStartRequest request) {
        InstanceStartResponse response = new InstanceStartResponse();

        if (pullImage(request, response)) {
            runImage(request, response);
        }

        return response;
    }

    public InstanceRestartResponse restartInstance(InstanceRestartRequest request) {
        logger.info(String.format("begin restartInstance: %s", request));

        InstanceRestartResponse response = new InstanceRestartResponse();

        dockerContainerService.restartContainer(request, response);

        logger.info(String.format("end restartInstance: %s", response));

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
}


