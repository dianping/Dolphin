package com.dianping.paas.core.queue;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/14.
 */
public interface Sequencer<T> {
    void offer(String id, T obj);

    SequencedObject<T> take() throws InterruptedException;
}
