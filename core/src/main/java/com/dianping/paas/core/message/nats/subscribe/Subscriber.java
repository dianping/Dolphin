package com.dianping.paas.core.message.nats.subscribe;

import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.core.message.codec.Codec;
import nats.client.Message;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public abstract class Subscriber {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    private Codec codec = ExtensionLoader.getExtension(Codec.class);

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
