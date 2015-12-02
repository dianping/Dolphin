package com.dianping.paas.core.service.impl;

import com.dianping.paas.core.dto.request.DockerfileRequest;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.response.DockerfileResponse;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import com.dianping.paas.core.message.nats.MessageCallBack;
import com.dianping.paas.core.message.nats.bus.MessageBus;
import com.dianping.paas.core.message.nats.subscribe.Subject;
import com.dianping.paas.core.service.AgentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/02 14:21.
 */
@Component
public class AgentServiceImpl implements AgentService {
    private static final Logger logger = LogManager.getLogger(AgentServiceImpl.class);

    @Resource
    private MessageBus messageBus;

    public void pullImageAndRun(DockerfileRequest dockerfileRequest, DockerfileResponse dockerfileResponse) {

        final InstanceStartRequest startInstanceContext = buildStartInstanceRequest(dockerfileRequest, dockerfileResponse);

        logger.info(String.format("begin pullImageAndRun request ==>\n%s", startInstanceContext));

        messageBus.requestAsync(Subject.Instance.PULL_IMAGE_AND_RUN, startInstanceContext, new MessageCallBack<InstanceStartResponse>(InstanceStartResponse.class) {
            @Override
            public void success(InstanceStartResponse startInstanceResponse) {
                logger.info(String.format("success pullImageAndRun response ==>\n%s", startInstanceResponse));
            }

            @Override
            public void error(Throwable throwable) {
                logger.error(String.format("error pullImageAndRun request ==>\n%s", startInstanceContext), throwable);
            }

            @Override
            public void timeout() {
                logger.error(String.format("timeout pullImageAndRun request ==>\n%s", startInstanceContext));
            }
        });
    }

    private InstanceStartRequest buildStartInstanceRequest(DockerfileRequest dockerfileRequest, DockerfileResponse dockerfileResponse) {
        InstanceStartRequest startInstanceContext = new InstanceStartRequest();

        startInstanceContext.setRepository(dockerfileResponse.getRepository());
        startInstanceContext.setAppName(dockerfileRequest.getAppName());
        startInstanceContext.setImageId(dockerfileResponse.getImageId());

        return startInstanceContext;
    }
}
