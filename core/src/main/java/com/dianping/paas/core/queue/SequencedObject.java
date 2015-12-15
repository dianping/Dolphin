package com.dianping.paas.core.queue;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/14.
 */
public interface SequencedObject<T> {

    T getPayload();

    void done();

    String getId();
}
