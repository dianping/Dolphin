package com.dianping.paas.controller.record;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/14.
 */
public interface OperationRecorder {

    long prepare(String appId, int operationType);

    void start(int totalHosts);

    void done(boolean success, String msg);
}
