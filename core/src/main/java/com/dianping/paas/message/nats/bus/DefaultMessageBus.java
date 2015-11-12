package com.dianping.paas.message.nats.bus;

import com.dianping.paas.message.nats.MessageCallBack;
import com.dianping.paas.util.JsonUtil;
import nats.client.Nats;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/05 16:05.
 */
@Component
public class DefaultMessageBus implements MessageBus {

    @Resource
    private Nats nats;

    /**
     * 同步获取响应
     */
    public <REQUEST_T> void requestSync(String subject, REQUEST_T payload, MessageCallBack messageCallBack) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        messageCallBack.setCountDownLatch(countDownLatch);
        doRequest(subject, payload, messageCallBack);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            messageCallBack.onError(e);
        }
    }

    /**
     * 异步获取响应
     */
    public <REQUEST_T> void requestAsync(String subject, REQUEST_T payload, MessageCallBack messageCallBack) {
        doRequest(subject, payload, messageCallBack);
    }


    private <REQUEST_T> void doRequest(String subject, REQUEST_T payload, MessageCallBack messageCallBack) {
        String body;
        try {
            body = JsonUtil.toJson(payload);
            nats.request(subject, body, 5, TimeUnit.SECONDS, messageCallBack);
        } catch (IOException e) {
            messageCallBack.onError(e);
        }

    }
}
