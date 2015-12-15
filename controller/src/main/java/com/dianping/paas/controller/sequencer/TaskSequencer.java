package com.dianping.paas.controller.sequencer;

import com.dianping.paas.controller.dto.depoly.entity.OperationContext;
import org.springframework.stereotype.Component;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/14.
 */
@Component
public class TaskSequencer {

    public long queueAndRun(OperationContext opCtx, int opType, final Task task) {
        long operationId = 0L;

        return operationId;
    }

}
