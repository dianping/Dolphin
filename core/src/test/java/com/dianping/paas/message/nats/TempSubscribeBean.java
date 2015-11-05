package com.dianping.paas.message.nats;

import com.dianping.paas.util.JsonUtil;
import nats.client.Message;
import nats.client.spring.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/05 18:50.
 */
@Component
public class TempSubscribeBean {

    public static final String NATS_SUBJECT = "nats_subject";
    private final static Logger logger = LogManager.getLogger(TempSubscribeBean.class);

    @Subscribe(NATS_SUBJECT)
    public void subscribe(Message message) {
        try {
            MessageBusTest.RequestPayload requestPayload = JsonUtil.toBean(message.getBody(), MessageBusTest.RequestPayload.class);
            logger.trace("TempSubscribeBean#subscribe receive message: " + requestPayload);

            Thread.sleep(500);

            String response = JsonUtil.toJson(new MessageBusTest.ResponsePayload("chao.yu", new Date()));
            logger.trace("TempSubscribeBean#subscribe receive message: " + response);
            message.reply(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}