package com.dianping.paas.message.nats.subscribe;

import com.dianping.paas.util.JsonUtil;
import nats.client.Message;

import java.io.IOException;


public class SubscribeBean {

    protected <T> T getPayload(Message message, Class<T> clazz) throws IOException {
        return JsonUtil.toBean(message.getBody(), clazz);
    }

    protected <T> void reply(Message message, T response) throws IOException {
        message.reply(JsonUtil.toJson(response));
    }

}
