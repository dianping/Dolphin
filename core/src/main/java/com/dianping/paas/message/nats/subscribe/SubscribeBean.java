package com.dianping.paas.message.nats.subscribe;

import com.dianping.paas.util.JsonUtil;
import nats.client.Message;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SubscribeBean {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    protected <T> T getPayload(Message message, Class<T> clazz) throws IOException {
        return JsonUtil.toBean(message.getBody(), clazz);
    }

    protected <T> void reply(Message message, T response) throws IOException {
        message.reply(JsonUtil.toJson(response));
    }

    protected void run(Runnable runnable) {
        threadPool.submit(runnable);
    }

}
