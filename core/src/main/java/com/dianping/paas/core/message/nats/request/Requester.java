package com.dianping.paas.core.message.nats.request;

import com.dianping.paas.core.message.nats.MessageCallBack;
import com.dianping.paas.core.message.nats.bus.DefaultMessageBus;
import com.dianping.paas.core.message.nats.bus.MessageBus;

import javax.annotation.Resource;

/**
 * remote message
 * <p>
 * Created by yuchao on 12/4/15.
 */
public abstract class Requester {
    @Resource
    private MessageBus messageBus;

    /**
     * 同步请求, 超时时间为默认值 {@link DefaultMessageBus#DEFAULT_TIMEOUT}
     *
     * @param subject         主题
     * @param payload         请求的内容
     * @param messageCallBack 消息回调
     * @param <REQUEST_T>     请求内容的类型, 主要用于序列化
     */
    protected <REQUEST_T> void requestSync(String subject, REQUEST_T payload, MessageCallBack messageCallBack) {
        messageBus.requestSync(subject, payload, messageCallBack);
    }

    /**
     * 同步请求
     *
     * @param subject         主题
     * @param payload         请求的内容
     * @param messageCallBack 消息回调
     * @param timeout         超时时间,默认为 {@link DefaultMessageBus#DEFAULT_TIMEOUT}
     * @param <REQUEST_T>     请求内容的类型, 主要用于序列化
     */
    protected <REQUEST_T> void requestSync(String subject, REQUEST_T payload, MessageCallBack messageCallBack, int timeout) {
        messageBus.requestSync(subject, payload, messageCallBack, timeout);
    }

    /**
     * 异步请求
     *
     * @param <REQUEST_T> 请求类型
     * @param subject     主题
     */
    protected <REQUEST_T> void requestAsync(String subject, REQUEST_T payload) {
        requestAsync(subject, payload, null);
    }

    /**
     * 异步请求
     *
     * @param <REQUEST_T> 请求类型
     * @param subject     主题
     * @param payload     请求内容
     */
    protected <REQUEST_T> void requestAsync(String subject, REQUEST_T payload, MessageCallBack messageCallBack) {
        messageBus.requestAsync(subject, payload, messageCallBack);
    }
}
