package com.dianping.paas.agent.service.impl;

import com.dianping.paas.agent.service.InstanceService;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.PullResponseItem;
import com.github.dockerjava.core.command.PullImageResultCallback;
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
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private DockerClient dockerClient;


    public InstanceStartResponse pullImageAndRun(final InstanceStartRequest instanceStartRequest) throws Exception {
        InstanceStartResponse instanceStartResponse = new InstanceStartResponse();

        pullImage(instanceStartRequest, instanceStartResponse);
        runImage(instanceStartRequest, instanceStartResponse);

        return instanceStartResponse;
    }

    private void pullImage(InstanceStartRequest instanceStartRequest, InstanceStartResponse instanceStartResponse) {
        logger.info(String.format("\nbegin pullImage, request ==>\n%s", instanceStartRequest));

        dockerClient.pullImageCmd(instanceStartRequest.getRepository()).exec(new PullImageResultCallback() {
            @Override
            public void onNext(PullResponseItem item) {
                super.onNext(item);
                logger.info(item);
            }
        }).awaitSuccess();

        logger.info(String.format("\nend pullImage, response ==>\n%s", instanceStartResponse));
    }

    private void runImage(InstanceStartRequest instanceStartRequest, InstanceStartResponse instanceStartResponse) throws Exception {
        logger.info(String.format("\nbegin runImage, request ==>\n%s", instanceStartRequest));

        String imageId = instanceStartRequest.getImageId();
        CreateContainerResponse createContainerResponse = dockerClient.createContainerCmd(imageId).withCmd("touch", "/test").exec();
        String containerId = createContainerResponse.getId();

        if (containerId == null) {
            // TODO 归纳所有异常, 细分处理
            throw new Exception("create container failed!");
        }
        dockerClient.startContainerCmd(containerId).exec();

        logger.info(String.format("\nend runImage, response ==>\n%s", instanceStartResponse));
    }
}
