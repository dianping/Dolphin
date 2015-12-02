package com.dianping.paas.repository.message.nats.subscribe;

import com.dianping.paas.core.dto.DockerfileRequest;
import com.dianping.paas.core.dto.DockerfileResponse;
import com.dianping.paas.core.message.nats.subscribe.Subject;
import com.dianping.paas.core.message.nats.subscribe.SubscribeBean;
import com.dianping.paas.repository.docker.DockerfileService;
import nats.client.Message;
import nats.client.spring.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by yuchao on 12/1/15.
 */
@Component
public class InstanceSubscriber extends SubscribeBean {

    public InstanceSubscriber() {

    }

    private static final Logger logger = LogManager.getLogger(InstanceSubscriber.class);

    @Resource
    private DockerfileService dockerfileService;

    @Subscribe(Subject.Instance.NEW_AND_DEPLOY)
    public void subscribe(final Message message) {
        run(new Runnable() {
            public void run() {
                DockerfileRequest dockerfileRequest = null;
                DockerfileResponse dockerfileResponse;
                try {
                    dockerfileRequest = getPayload(message, DockerfileRequest.class);
                    dockerfileResponse = dockerfileService.buildAndPushImage(dockerfileRequest);
                    reply(message, dockerfileResponse);
                } catch (Exception e) {
                    logger.error("buildAndPushImage error, request is " + dockerfileRequest, e);
                }
            }
        });

    }
}
