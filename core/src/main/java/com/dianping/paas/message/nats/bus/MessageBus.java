package com.dianping.paas.message.nats.bus;


import com.dianping.paas.message.nats.MessageCallBack;

import java.io.IOException;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/05 15:57.
 */
public interface MessageBus {

    <T> void requestSync(String subject, T payload, MessageCallBack messageCallBack) throws IOException, InterruptedException;

    <T> void requestAsync(String subject, T payload, MessageCallBack messageCallBack) throws IOException, InterruptedException;

}
