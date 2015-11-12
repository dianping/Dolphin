package com.dianping.paas.message.nats;

import com.dianping.paas.message.nats.bus.MessageBus;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/05 18:20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/config/spring/appcontext-nats.xml")
public class MessageBusTest {
    private final static Logger logger = LogManager.getLogger(MessageBusTest.class);

    @Resource
    private MessageBus messageBus;

    @Test
    public void testRequestSync() throws Exception {
        RequestPayload requestPayload = new RequestPayload("testRequestSync", new Date());
        final boolean[] flag = {true};
        messageBus.requestSync(TempSubscribeBean.NATS_SUBJECT, requestPayload, new MessageCallBack<ResponsePayload>(ResponsePayload.class) {
            @Override
            public void callback(ResponsePayload responsePayload) {
                flag[0] = false;
                logger.trace("testRequestSync callback: " + responsePayload);
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error(throwable);
            }
        });

        Assert.assertFalse(flag[0]);
    }

    @Test
    public void testRequestAsync() throws Exception {
        RequestPayload requestPayload = new RequestPayload("testRequestAsync", new Date());
        final boolean[] flag = {true};
        messageBus.requestAsync(TempSubscribeBean.NATS_SUBJECT, requestPayload, new MessageCallBack<ResponsePayload>(ResponsePayload.class) {
            @Override
            public void callback(ResponsePayload responsePayload) {
                flag[0] = false;
                logger.trace("testRequestAsync callback: " + responsePayload);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error(throwable);
            }
        });


        Assert.assertTrue(flag[0]);
    }


    @Data
    public static class RequestPayload {
        private String name;
        private Date date;

        public RequestPayload(String name, Date date) {
            this.date = date;
            this.name = name;
        }

        @Override
        public String toString() {
            return "RequestPayload{" +
                    "name='" + name + '\'' +
                    ", date=" + date +
                    '}';
        }

        public RequestPayload() {
        }
    }

    @Data
    public static class ResponsePayload {
        private String name;
        private Date date;

        public ResponsePayload(String name, Date date) {
            this.name = name;
            this.date = date;
        }

        @Override
        public String toString() {
            return "ResponsePayload{" +
                    "name='" + name + '\'' +
                    ", date=" + date +
                    '}';
        }

        public ResponsePayload() {
        }
    }


}