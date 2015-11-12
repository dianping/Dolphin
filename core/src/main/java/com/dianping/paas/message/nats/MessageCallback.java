package com.dianping.paas.message.nats;

import com.dianping.paas.util.JsonUtil;
import lombok.Data;
import nats.client.Message;
import nats.client.MessageHandler;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/05 16:13.
 */
@Data
public abstract class MessageCallBack<Res> implements MessageHandler {
    private CountDownLatch countDownLatch;

    private Class<Res> responseType;

    public MessageCallBack(Class<Res> responseType) {
        this.responseType = responseType;
    }

    public void onMessage(Message message) {

        Res response;
        try {
            response = JsonUtil.toBean(message.getBody(), responseType);
            callback(response);
        } catch (IOException e) {
            onError(e);
        } finally {
            tryCountDown();
        }

    }

    public abstract void callback(Res res);

    public abstract void onError(Throwable throwable);

    private void tryCountDown() {
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }


}
