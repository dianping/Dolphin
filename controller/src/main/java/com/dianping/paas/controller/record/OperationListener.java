package com.dianping.paas.controller.record;

import com.dianping.paas.controller.dto.depoly.entity.HostOperation;
import com.dianping.paas.controller.dto.depoly.entity.OperationContext;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
public interface OperationListener {

    void init(OperationContext opCtx, OperationRecorder opRecorder);

    void onStart(int totalHosts);

    void preCreate(HostOperation hostOperation);

    void onSuccess();

    void onFail();
}
