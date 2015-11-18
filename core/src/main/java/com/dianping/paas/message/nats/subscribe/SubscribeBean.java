package com.dianping.paas.message.nats.subscribe;

import com.dianping.paas.message.codec.Codec;
import nats.client.Message;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SubscribeBean {
    @Resource
    protected Codec codec;

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    protected <T> T getPayload(Message message, Class<T> clazz) throws IOException {
        return codec.decode(message.getBody(), clazz);
    }

    protected <T> void reply(Message message, T response) throws IOException {
        message.reply(codec.encode(response));
    }

    protected void run(Runnable runnable) {
        threadPool.submit(runnable);
    }

}
