package com.dianping.paas.controller.sequencer;

import com.dianping.paas.controller.dto.depoly.entity.OperationContext;
import com.dianping.paas.controller.record.OperationRecorder;
import com.dianping.paas.core.queue.SequencedObject;
import com.dianping.paas.core.queue.Sequencer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/14.
 */
@Component
public class TaskSequencer implements InitializingBean {

    public static final Logger logger = LogManager.getLogger(TaskSequencer.class);

    @Resource
    private OperationRecorder operationRecorder;

    @Resource
    private Sequencer sequencer;

    public long queueAndRun(OperationContext operationContext, int operationType, final Task task) {

        long operationId = operationRecorder.prepare(operationContext.getAppId(), operationType);

        sequencer.offer(operationContext.getAppId(), new Runnable() {

            @Override
            public void run() {
                try {
                    task.execute();
                } catch (Exception e) {
                    logger.error("Unexpected exception", e);
                }
            }
        });

        return operationId;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        final ExecutorService threadPool = Executors.newCachedThreadPool();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        submitOperationAndRecordDone();
                    } catch (InterruptedException e) {
                        break;
                    } catch (RuntimeException e) {
                        logger.error("Unexpected exception", e);
                    }

                }
            }

            private void submitOperationAndRecordDone() throws InterruptedException {

                final SequencedObject<Runnable> sequencedObj = sequencer.take();

                threadPool.submit(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            sequencedObj.getPayload().run();
                        } catch (RuntimeException e) {
                            logger.error("Unexpected exception", e);
                        } finally {
                            sequencedObj.done();
                        }
                    }

                });

            }

        }).start();
    }
}
