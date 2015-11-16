package com.dianping.paas.message.nats;

import com.dianping.paas.message.nats.bus.MessageBus;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/05 18:20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/config/spring/appcontext-*.xml")
public class MessageBusTest {
    public static final int FLAG_INITIAL = -1;
    public static final int FLAG_TIMEOUT = 1;
    public static final int FLAG_NOT_TIMEOUT = 2;
    public static final int FLAG_ERROR = 4;

    @Resource
    private MessageBus messageBus;

    /**
     * 测试未超时同步调用
     * 将超时时间设置得比处理时间 {@link TestSubscribeBean#PROCESS_TIME} 长,那么就不会超时 {@link #FLAG_NOT_TIMEOUT}
     */
    @Test
    public void testRequestSyncNotTimeout() throws Exception {
        doTestRequestSync(FLAG_NOT_TIMEOUT, TestSubscribeBean.PROCESS_TIME + 100);
    }

    /**
     * 测试超时同步调用
     * 将超时时间设置得比处理时间 {@link TestSubscribeBean#PROCESS_TIME} 短,那么就会超时 {@link #FLAG_TIMEOUT}
     */
    @Test
    public void testRequestSyncTimeout() throws Exception {
        doTestRequestSync(FLAG_TIMEOUT, TestSubscribeBean.PROCESS_TIME - 100);
    }

    /**
     * 测试同步调用, 设置不同的超时时间能得到不同的flag
     *
     * @param expectedFlag 期望得到的超时结果标志
     * @param timeout      同步的超时时间
     */
    private void doTestRequestSync(int expectedFlag, final int timeout) {
        RequestPayload requestPayload = new RequestPayload("testRequestSync", new Date());
        final AtomicInteger flag = new AtomicInteger(FLAG_INITIAL);

        messageBus.requestSync(TestSubscribeBean.NATS_SUBJECT, requestPayload, newMessageCallBack(flag), timeout);

        Assert.assertEquals(expectedFlag, flag.get());
    }

    /**
     * 测试异步调用
     *
     * @throws Exception
     */
    @Test
    public void testRequestAsync() throws Exception {
        RequestPayload requestPayload = new RequestPayload("testRequestAsync", new Date());
        final AtomicInteger flag = new AtomicInteger(FLAG_INITIAL);

        messageBus.requestAsync(TestSubscribeBean.NATS_SUBJECT, requestPayload, newMessageCallBack(flag));

        Assert.assertEquals(FLAG_INITIAL, flag.get());
    }

    private MessageCallBack<ResponsePayload> newMessageCallBack(final AtomicInteger flag) {
        return new MessageCallBack<ResponsePayload>(ResponsePayload.class) {

            @Override
            public void success(ResponsePayload responsePayload) {
                flag.set(FLAG_NOT_TIMEOUT);
            }

            @Override
            public void error(Throwable throwable) {
                flag.set(FLAG_ERROR);
            }

            @Override
            public void timeout() {
                flag.set(FLAG_TIMEOUT);
            }

        };
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