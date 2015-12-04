package com.dianping.paas.agent.service.impl;

import com.dianping.paas.agent.service.DockerContainerService;
import com.dianping.paas.agent.service.DockerImageService;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.PullResponseItem;
import com.github.dockerjava.core.command.PullImageResultCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by yuchao on 12/4/15.
 */
@Component
public class DockerImageServiceImpl implements DockerImageService {
    private static final Logger logger = LogManager.getLogger(DockerImageServiceImpl.class);

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private DockerClient dockerClient;

    @Resource
    private DockerContainerService dockerContainerService;

    public void pull(InstanceStartRequest request, InstanceStartResponse response) {
        try {
            dockerClient.pullImageCmd(request.getRepository()).exec(new PullImageResultCallback() {
                @Override
                public void onNext(PullResponseItem item) {
                    super.onNext(item);
                    logger.info(item);
                }
            }).awaitSuccess();
            response.success();
        } catch (Exception e) {
            response.fail(e.toString());
            logger.error(String.format("error pullImage: %s", request), e);
        }
    }

    public void run(InstanceStartRequest request, InstanceStartResponse response) {
        if (dockerContainerService.createContainer(request, response)) {
            dockerContainerService.startContainer(request, response);
        }
    }
}
