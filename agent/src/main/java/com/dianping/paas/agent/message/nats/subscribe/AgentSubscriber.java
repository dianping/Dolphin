package com.dianping.paas.agent.message.nats.subscribe;

import com.dianping.paas.agent.service.InstanceService;
import com.dianping.paas.core.dto.request.InstanceScaleRequest;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.request.InstanceStopRequest;
import com.dianping.paas.core.dto.request.InstanceUpgradeRequest;
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

    @Subscribe(Subject.Instance.STOP)
    public void stopInstance(final Message message) {
        run(new Runnable() {
            public void run() {
                InstanceStopRequest instanceStopRequest = null;

                try {
                    instanceStopRequest = getPayload(message, InstanceStopRequest.class);
                    // stopInstance 为 异步,不需要响应
                    instanceService.stopInstance(instanceStopRequest);
                } catch (Exception e) {
                    logger.error(String.format("error when stopInstance: %s", instanceStopRequest), e);
                }

            }
        });
    }


    @Subscribe(Subject.Instance.UPGRADE)
    public void upgradeInstance(final Message message) {
        run(new Runnable() {
            public void run() {
                InstanceUpgradeRequest instanceUpgradeRequest = null;

                try {
                    instanceUpgradeRequest = getPayload(message, InstanceUpgradeRequest.class);
                    // upgradeInstance 为 异步,不需要响应
                    instanceService.upgradeInstance(instanceUpgradeRequest);
                } catch (Exception e) {
                    logger.error(String.format("error when upgradeInstance: %s", instanceUpgradeRequest), e);
                }

            }
        });
    }

    @Subscribe(Subject.Instance.SCALE)
    public void scaleInstance(final Message message) {
        run(new Runnable() {
            public void run() {
                InstanceScaleRequest instanceScaleRequest = null;

                try {
                    instanceScaleRequest = getPayload(message, InstanceScaleRequest.class);
                    // scaleInstance 为 异步,不需要响应
                    instanceService.scaleInstance(instanceScaleRequest);
                } catch (Exception e) {
                    logger.error(String.format("error when scaleInstance: %s", instanceScaleRequest), e);
                }

            }
        });
    }

}
