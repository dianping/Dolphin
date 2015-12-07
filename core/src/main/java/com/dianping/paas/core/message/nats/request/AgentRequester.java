package com.dianping.paas.core.message.nats.request;

import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.request.UpgradeInstanceRequest;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
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

    public void pullImageAndRun(final InstanceStartRequest startInstanceRequest) {
        logger.info(String.format("begin pullImageAndRun: %s", startInstanceRequest));

        requestAsync(Subject.Instance.PULL_IMAGE_AND_RUN, startInstanceRequest, new MessageCallBack<InstanceStartResponse>(InstanceStartResponse.class) {
            @Override
            public void success(InstanceStartResponse startInstanceResponse) {
                logger.info(String.format("success pullImageAndRun: %s", startInstanceResponse));
            }

            @Override
            public void error(Throwable throwable) {
                logger.error(String.format("error pullImageAndRun: %s", startInstanceRequest), throwable);
            }

            @Override
            public void timeout() {
                logger.error(String.format("timeout pullImageAndRun: %s", startInstanceRequest));
            }
        });

    }

    public void upgradeInstance(UpgradeInstanceRequest request) {
        logger.info(String.format("begin upgradeInstance: %s", request));

        //TODO begin: find all agent then request with <Subject.Instance.UPGRADE + agent_ip>
        request.setInstance_id("b6175f138d8e");
        requestAsync(Subject.Instance.UPGRADE, request);
        // TODO end
    }
}
