package com.dianping.paas.agent.message.nats.subscribe;

import com.dianping.paas.agent.service.InstanceService;
import com.dianping.paas.core.dto.request.*;
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

    @Subscribe(Subject.Instance.START)
    public void startInstance(final Message message) {
        run(new Runnable() {
            public void run() {
                InstanceStartRequest instanceStartRequest = null;

                try {
                    instanceStartRequest = getPayload(message, InstanceStartRequest.class);
                    // startInstance 为 异步,不需要响应
                    instanceService.startInstance(instanceStartRequest);
                } catch (Exception e) {
                    logger.error(String.format("error when startInstance: %s", instanceStartRequest), e);
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

    @Subscribe(Subject.Instance.RESTART)
    public void restartInstance(final Message message) {
        run(new Runnable() {
            public void run() {
                InstanceRestartRequest instanceRestartRequest = null;

                try {
                    instanceRestartRequest = getPayload(message, InstanceRestartRequest.class);
                    // restartInstance 为 异步,不需要响应
                    instanceService.restartInstance(instanceRestartRequest);
                } catch (Exception e) {
                    logger.error(String.format("error when restartInstance: %s", instanceRestartRequest), e);
                }

            }
        });
    }

    @Subscribe(Subject.Instance.REMOVE)
    public void removeInstance(final Message message) {
        run(new Runnable() {
            public void run() {
                InstanceRemoveRequest instanceRemoveRequest = null;

                try {
                    instanceRemoveRequest = getPayload(message, InstanceRemoveRequest.class);
                    // removeInstance 为 异步,不需要响应
                    instanceService.removeInstance(instanceRemoveRequest);
                } catch (Exception e) {
                    logger.error(String.format("error when removeInstance: %s", instanceRemoveRequest), e);
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
}
