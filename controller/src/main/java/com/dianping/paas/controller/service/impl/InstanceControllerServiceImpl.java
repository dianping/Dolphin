package com.dianping.paas.controller.service.impl;

import com.dianping.paas.controller.service.InstanceControllerService;
import com.dianping.paas.core.dto.request.InstanceScaleRequest;
import com.dianping.paas.core.message.nats.request.AgentRequester;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/17 15:48.
 */
@Service
public class InstanceControllerServiceImpl implements InstanceControllerService {
    @Resource
    private AgentRequester agentRequester;

    @Override
    public void scaleInstance(String appId, int count) {
        List<InstanceScaleRequest> instanceScaleRequestList = buildInstanceScaleRequest(appId, count);

        for (InstanceScaleRequest instanceScaleRequest : instanceScaleRequestList) {
            agentRequester.scaleInstance(instanceScaleRequest);
        }
    }

    /**
     * TODO 验证, 插数据库, 构造发送给nats的消息
     */
    private List<InstanceScaleRequest> buildInstanceScaleRequest(String appId, int count) {
        List<InstanceScaleRequest> instanceScaleRequestList = Lists.newArrayList();

        for (int i = 0; i < count; i++) {
            InstanceScaleRequest instanceScaleRequest = new InstanceScaleRequest();
            instanceScaleRequest.setAppId(appId);
            //instanceScaleRequest.setImageId();
            // ...

            instanceScaleRequestList.add(instanceScaleRequest);
        }

        return instanceScaleRequestList;
    }
}
