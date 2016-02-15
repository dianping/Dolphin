package com.dianping.paas.controller.executor;

import com.dianping.paas.controller.dto.depoly.entity.OperationContext;
import com.dianping.paas.controller.executor.context.InstanceDeployContext;
import com.dianping.paas.controller.record.OperationListener;
import com.dianping.paas.core.dal.entity.AppConfigEntity;
import com.dianping.paas.core.dal.entity.AppEntity;

import java.util.List;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
public interface DeployExecutor {

    List<InstanceDeployContext> prepareInstanceDeployContext(OperationContext opCtx, AppEntity app, AppConfigEntity appConfig, int instanceCount, boolean tryOnline);

    void create(OperationContext opCtx, List<InstanceDeployContext> instanceDeployContexts, OperationListener deployListener);
}
