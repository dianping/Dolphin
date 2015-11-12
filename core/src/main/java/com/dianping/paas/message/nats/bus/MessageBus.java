package com.dianping.paas.message.nats.bus;


import com.dianping.paas.message.nats.MessageCallBack;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/05 15:57.
 */
public interface MessageBus {

    <REQUEST_T> void requestSync(String subject, REQUEST_T payload, MessageCallBack messageCallBack);

    <REQUEST_T> void requestAsync(String subject, REQUEST_T payload, MessageCallBack messageCallBack);
}
