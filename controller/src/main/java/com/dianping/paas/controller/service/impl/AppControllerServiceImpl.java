package com.dianping.paas.controller.service.impl;

import com.dianping.paas.controller.dto.depoly.entity.Operation;
import com.dianping.paas.controller.dto.depoly.entity.OperationContext;
import com.dianping.paas.controller.processor.step.AppInitStep;
import com.dianping.paas.controller.record.OperationRecorder;
import com.dianping.paas.controller.sequencer.Task;
import com.dianping.paas.controller.sequencer.TaskSequencer;
import com.dianping.paas.controller.service.AppControllerService;
import com.dianping.paas.core.dto.request.AppInitRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/14.
 */
@Service
public class AppControllerServiceImpl implements AppControllerService {

    @Resource
    private TaskSequencer taskSequencer;

    @Resource
    private OperationRecorder operationRecorder;

    @Resource
    private AppInitStep appInitStep;

    public long initApp(AppInitRequest appInitRequest) {

        appInitStep.saveAppInfo(appInitRequest);

        final OperationContext opCtx = new OperationContext();
        opCtx.setAppId(appInitRequest.getAppId());
        return taskSequencer.queueAndRun(opCtx, Operation.TYPE_CREATE, new Task() {
            @Override
            public void execute() {

            }
        });

    }
}
