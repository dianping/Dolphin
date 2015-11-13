package com.dianping.paas.message.nats;

import com.dianping.paas.message.nats.subscribe.SubscribeBean;
import nats.client.Message;
import nats.client.spring.Subscribe;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/05 18:50.
 */
@Component
public class TestSubscribeBean extends SubscribeBean {
    public static final String NATS_SUBJECT = "nats_subject";
    public static final int PROCESS_TIME = 200;

    @Subscribe(NATS_SUBJECT)
    public void subscribe(final Message message) {
        run(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(PROCESS_TIME);
                    reply(message, new MessageBusTest.ResponsePayload("chao.yu", new Date()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}