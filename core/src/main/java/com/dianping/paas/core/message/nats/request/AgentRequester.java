package com.dianping.paas.core.message.nats.request;

import com.dianping.paas.core.dto.request.*;
import com.dianping.paas.core.dto.response.*;
import com.dianping.paas.core.message.nats.MessageCallBack;
import com.dianping.paas.core.message.nats.subscribe.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by yuchao on 12/4/15.
 */
@Component
public class AgentRequester extends Requester {
    private static final Logger logger = LogManager.getLogger(AgentRequester.class);

    public void pullImageAndRun(final InstanceStartRequest request) {
        logger.info(String.format("begin pullImageAndRun: %s", request));

        requestAsync(Subject.Instance.PULL_IMAGE_AND_RUN, request, new MessageCallBack<InstanceStartResponse>(InstanceStartResponse.class) {
            @Override
            public void success(InstanceStartResponse response) {
                logger.info(String.format("success pullImageAndRun: %s", response));
            }

            @Override
            public void error(Throwable throwable) {
                logger.error(String.format("error pullImageAndRun: %s", request), throwable);
            }

            @Override
            public void timeout() {
                logger.error(String.format("timeout pullImageAndRun: %s", request));
            }
        });

    }

    public void startInstance(final InstanceStartRequest request) {
        requestAsync(Subject.Instance.START, request, new MessageCallBack<InstanceStartResponse>(InstanceStartResponse.class) {
            @Override
            public void success(InstanceStartResponse response) {
                logger.info(String.format("success startInstance: %s", response));
            }

            @Override
            public void error(Throwable throwable) {
                logger.error(String.format("error startInstance: %s", request), throwable);
            }

            @Override
            public void timeout() {
                logger.error(String.format("timeout startInstance: %s", request));
            }
        });
    }

    public void stopInstance(final InstanceStopRequest request) {
        requestAsync(Subject.Instance.STOP, request, new MessageCallBack<InstanceStopResponse>(InstanceStopResponse.class) {
            @Override
            public void success(InstanceStopResponse response) {
                logger.info(String.format("success stopInstance: %s", response));
            }

            @Override
            public void error(Throwable throwable) {
                logger.error(String.format("error stopInstance: %s", request), throwable);
            }

            @Override
            public void timeout() {
                logger.error(String.format("timeout stopInstance: %s", request));
            }
        });
    }

    public void scaleInstance(final InstanceScaleRequest request) {
        logger.info(String.format("begin scaleInstance: %s", request));

        requestAsync(Subject.Instance.SCALE, request, new MessageCallBack<InstanceScaleResponse>(InstanceScaleResponse.class) {
            @Override
            public void success(InstanceScaleResponse response) {
                logger.info(String.format("success scaleInstance: %s", response));
            }

            @Override
            public void error(Throwable throwable) {
                logger.error(String.format("error scaleInstance: %s", request), throwable);
            }

            @Override
            public void timeout() {
                logger.error(String.format("timeout scaleInstance: %s", request));
            }
        });

    }

    public void removeInstance(final InstanceRemoveRequest request) {
        logger.info(String.format("begin removeInstance: %s", request));

        requestAsync(Subject.Instance.REMOVE, request, new MessageCallBack<InstanceRemoveResponse>(InstanceRemoveResponse.class) {
            @Override
            public void success(InstanceRemoveResponse response) {
                logger.info(String.format("success removeInstance: %s", response));
            }

            @Override
            public void error(Throwable throwable) {
                logger.error(String.format("error removeInstance: %s", request), throwable);
            }

            @Override
            public void timeout() {
                logger.error(String.format("timeout removeInstance: %s", request));
            }
        });
    }

    public void restartInstance(final InstanceRestartRequest request) {
        logger.info(String.format("begin restartInstance: %s", request));

        requestAsync(Subject.Instance.RESTART, request, new MessageCallBack<InstanceRestartResponse>(InstanceRestartResponse.class) {
            @Override
            public void success(InstanceRestartResponse response) {
                logger.info(String.format("success restartInstance: %s", response));
            }

            @Override
            public void error(Throwable throwable) {
                logger.error(String.format("error restartInstance: %s", request), throwable);
            }

            @Override
            public void timeout() {
                logger.error(String.format("timeout restartInstance: %s", request));
            }
        });
    }

    public void upgradeInstance(InstanceUpgradeRequest request) {
        logger.info(String.format("begin upgradeInstance: %s", request));

        request.setInstance_id("b6175f138d8e");
//      requestAsync(Subject.Instance.UPGRADE + request.getAgent_ip(), request);
        requestAsync(Subject.Instance.UPGRADE, request);

    }
}
