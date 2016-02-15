package com.dianping.paas.controller.executor;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
public class StatusCumulator {

    private boolean m_allSuccess = true;

    public synchronized void cumulate(boolean thisSuccess) {
        m_allSuccess = m_allSuccess && thisSuccess;
    }

    public synchronized boolean allSuccess() {
        return m_allSuccess;
    }

}
