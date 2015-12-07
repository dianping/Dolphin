package com.dianping.paas.agent.message.nats.subscribe;

import com.dianping.paas.agent.service.InstanceService;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.request.UpgradeInstanceRequest;
import com.dianping.paas.core.message.nats.subscribe.Subject;
import com.dianping.paas.core.message.nats.subscribe.Subscriber;
import nats.client.Message;
import nats.client.spring.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/02 14:50.
 */
@Component
public class AgentSubscriber extends Subscriber {
    private static final Logger logger = LogManager.getLogger(AgentSubscriber.class);

    @Resource
    private InstanceService instanceService;

    @Subscribe(Subject.Instance.PULL_IMAGE_AND_RUN)
    public void pullImageAndRun(final Message message) {
        run(new Runnable() {
            public void run() {
                InstanceStartRequest instanceStartRequest = null;

                try {
                    instanceStartRequest = getPayload(message, InstanceStartRequest.class);
                    reply(message, instanceService.pullImageAndRun(instanceStartRequest));
                } catch (Exception e) {
                    logger.error(String.format("error when pullImageAndRun: %s", instanceStartRequest), e);
                }
            }
        });
    }

    @Subscribe(Subject.Instance.UPGRADE)
    public void upgradeInstance(final Message message) {
        run(new Runnable() {
            public void run() {
                UpgradeInstanceRequest upgradeInstanceRequest = null;

                try {
                    upgradeInstanceRequest = getPayload(message, UpgradeInstanceRequest.class);
                    // upgrade 为 异步,不需要响应
                    instanceService.upgrade(upgradeInstanceRequest);
                } catch (Exception e) {
                    logger.error(String.format("error when upgradeInstance: %s", upgradeInstanceRequest), e);
                }

            }
        });
    }

}
