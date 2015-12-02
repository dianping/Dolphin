package com.dianping.paas.core.message.nats.bus;


import com.dianping.paas.core.message.nats.MessageCallBack;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/05 15:57.
 */
public interface MessageBus {

    /**
     * 同步请求, 超时时间为默认值 {@link DefaultMessageBus#DEFAULT_TIMEOUT}
     *
     * @param subject         主题
     * @param payload         请求的内容
     * @param messageCallBack 消息回调
     * @param <REQUEST_T>     请求内容的类型, 主要用于序列化
     */
    <REQUEST_T> void requestSync(String subject, REQUEST_T payload, MessageCallBack messageCallBack);

    /**
     * 同步请求
     *
     * @param subject         主题
     * @param payload         请求的内容
     * @param messageCallBack 消息回调
     * @param timeout         超时时间,默认为 {@link DefaultMessageBus#DEFAULT_TIMEOUT}
     * @param <REQUEST_T>     请求内容的类型, 主要用于序列化
     */
    <REQUEST_T> void requestSync(String subject, REQUEST_T payload, MessageCallBack messageCallBack, int timeout);


    /**
     * 异步请求
     *
     * @param subject         主题
     * @param payload         请求内容
     * @param messageCallBack 消息回调
     * @param <REQUEST_T>     请求类型
     */
    <REQUEST_T> void requestAsync(String subject, REQUEST_T payload, MessageCallBack messageCallBack);
}
