package com.dianping.paas.repository.message.nats.subscribe;

import com.dianping.paas.core.dto.request.DockerfileRequest;
import com.dianping.paas.core.dto.response.DockerfileResponse;
import com.dianping.paas.core.message.nats.subscribe.Subject;
import com.dianping.paas.core.message.nats.subscribe.Subscriber;
import com.dianping.paas.repository.docker.DockerService;
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
public class RepositorySubscriber extends Subscriber {
    private static final Logger logger = LogManager.getLogger(RepositorySubscriber.class);

    @Resource
    private DockerService dockerService;

    @Subscribe(Subject.Instance.NEW_AND_DEPLOY)
    public void subscribe(final Message message) {
        run(new Runnable() {
            public void run() {
                DockerfileRequest dockerfileRequest = null;

                try {
                    dockerfileRequest = getPayload(message, DockerfileRequest.class);
                    reply(message, dockerService.buildImageAndPush(dockerfileRequest));
                } catch (Exception e) {
                    logger.error("buildImageAndPush error, request is " + dockerfileRequest, e);
                }
            }
        });
    }
}
