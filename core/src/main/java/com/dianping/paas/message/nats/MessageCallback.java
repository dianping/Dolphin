package com.dianping.paas.message.nats;

import com.dianping.paas.extension.ExtensionLoader;
import com.dianping.paas.message.codec.Codec;
import lombok.Data;
import nats.client.Message;
import nats.client.MessageHandler;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/05 16:13.
 */
@Data
public abstract class MessageCallBack<Res> implements MessageHandler {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    private CountDownLatch countDownLatch;

    private Class<Res> responseType;

    private boolean called = false;

    private int timeout;

    private Codec codec = ExtensionLoader.getExtension(Codec.class);

    public MessageCallBack(Class<Res> responseType) {
        this.responseType = responseType;
    }

    public void onMessage(final Message message) {
        Res response;
        try {
            called = true;
            response = codec.decode(message.getBody(), responseType);
            success(response);
        } catch (Exception e) {
            error(e);
        } finally {
            tryCountDown();
        }
    }

    public abstract void success(Res res);

    public abstract void timeout();

    public abstract void error(Throwable throwable);

    private void tryCountDown() {
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void beginSync(int timeout) {
        countDownLatch = new CountDownLatch(1);
        this.timeout = timeout;
    }

    public void endSync() throws InterruptedException {
        countDownLatch.await(timeout, TimeUnit.MILLISECONDS);
    }

}
