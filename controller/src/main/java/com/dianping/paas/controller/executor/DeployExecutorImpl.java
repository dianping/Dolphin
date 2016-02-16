package com.dianping.paas.controller.executor;

import com.dianping.paas.controller.dto.depoly.entity.HostOperation;
import com.dianping.paas.controller.dto.depoly.entity.OperationContext;
import com.dianping.paas.controller.executor.context.InstanceDeployContext;
import com.dianping.paas.controller.message.facade.AgentFacade;
import com.dianping.paas.controller.message.facade.RepositoryFacade;
import com.dianping.paas.controller.record.OperationListener;
import com.dianping.paas.controller.strategy.InstanceGroupStrategy;
import com.dianping.paas.controller.strategy.InstanceIndexAllocater;
import com.dianping.paas.core.dal.entity.AppConfigEntity;
import com.dianping.paas.core.dal.entity.AppEntity;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.dto.response.NewInstanceAndDeployWarResponse;
import com.dianping.paas.core.util.ClosureHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeoutException;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
@Component
public class DeployExecutorImpl implements DeployExecutor {

    public static final Logger logger = LogManager.getLogger(DeployExecutorImpl.class);

    @Resource
    private InstanceIndexAllocater instanceIndexAllocater;

    @Resource
    private InstanceGroupStrategy instanceGroupStrategy;

    @Resource
    private AgentFacade agentFacade;

    @Resource
    private RepositoryFacade repositoryFacade;

    @Override
    public AllocateWebPackageResponse allocateRespository(String appId, String appVersion, String md5) throws TimeoutException {
        return repositoryFacade.allocate(appId, appVersion, md5);
    }

    @Override
    public List<InstanceDeployContext> prepareInstanceDeployContext(OperationContext opCtx, AppEntity app, AppConfigEntity appConfig, int instanceCount, boolean tryOnline) {
        List<Long> groupIds = opCtx.getInstanceGroups();
        if (groupIds.size() != instanceCount) {
            groupIds = instanceGroupStrategy.allocateInstanceGroup(app.getAppId(), instanceCount);
        }

        List<Long> instanceIndexs = opCtx.getInstanceIndexs();
        if (instanceIndexs.size() != instanceCount) {
            instanceIndexs = instanceIndexAllocater.allocateInstanceIndex(app.getAppId(), instanceCount);
        }

        List<InstanceDeployContext> ctxes = new ArrayList<InstanceDeployContext>();
        for (int i = 0; i < instanceCount; i++) {
            InstanceDeployContext ctx = new InstanceDeployContext();

            ctx.setQuota(app.getQuota());
            ctx.setApp(app);
            ctx.setInstanceGroupId(groupIds.get(i));
            ctx.setAppConfig(appConfig);
            ctx.setTryOnline(tryOnline);
            ctx.setInstanceIndex(instanceIndexs.get(i));

            ctxes.add(ctx);
        }

        return ctxes;
    }

    @Override
    public void create(OperationContext opCtx, List<InstanceDeployContext> instanceDeployContexts, OperationListener operationListener) {
        StatusCumulator sc = new StatusCumulator();
        Semaphore smf = new Semaphore(0);
        int startedTask = 0;

        try {
            operationListener.onStart(instanceDeployContexts.size());

            for (InstanceDeployContext deployCtx : instanceDeployContexts) {
                startedTask++;
                doCreateAsync(deployCtx, operationListener, sc, smf);
            }
        } catch (Exception e) {
            logger.error(String.format("Error deploy operation %s on context %s", opCtx, instanceDeployContexts), e);
            sc.cumulate(false);
        } finally {
            try {
                smf.acquire(startedTask);
            } catch (InterruptedException e) {
            }
            notifyDeployListener(sc.allSuccess(), operationListener);
        }
    }

    private void doCreateAsync(final InstanceDeployContext deployCtx, final OperationListener deployListener, final StatusCumulator sc, final Semaphore smf) {
        doAsync(new ClosureHandler<HostOperation>() {

            @Override
            public HostOperation handle() {
                try {
                    return doCreate(deployCtx, deployListener);
                } catch (Exception e) {
                    logger.error("Error create instance");
                    throw new RuntimeException(e);
                }
            }
        }, sc, smf);
    }

    private void doAsync(final ClosureHandler<HostOperation> handler, final StatusCumulator sc, final Semaphore smf) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                boolean success = false;
                try {
                    success = handler.handle().isSuccess();
                } finally {
                    sc.cumulate(success);
                    smf.release();
                }
            }
        }).start();
    }

    private HostOperation doCreate(InstanceDeployContext instanceDeployContext, OperationListener deployListener) throws Exception {
        HostOperation hostOperation = new HostOperation();
        try {
            hostOperation.setType(HostOperation.TYPE_CREATE);
            hostOperation.setAppVersion(instanceDeployContext.getAppVersion().getVersion());
            hostOperation.setInstanceGroupId(instanceDeployContext.getInstanceGroupId());
            hostOperation.setImage(instanceDeployContext.getApp().getImage());
            hostOperation.setInstanceIndex(instanceDeployContext.getInstanceIndex());
            deployListener.preCreate(hostOperation);

            NewInstanceAndDeployWarResponse res = agentFacade.create(instanceDeployContext);
            hostOperation.setInstanceIp(res.getInstanceIp());
            hostOperation.setAgentIp(res.getAgentIp());
            hostOperation.setPort(res.getHttpPort());
            hostOperation.setStatus(res.getReturnCode().getStatus());
            hostOperation.setInstanceId(res.getInstanceId());
        } catch (Exception e) {
//            logger.error("Error deploy instance", e);
//            deployOp.addSegment(new Segment().setText(ExceptionUtil.convertException(e)));
//            deployOp.setStatus(HostOperation.STATUS_FAIL);
//            if (e instanceof InstanceOperationFailException) {
//                InstanceOperationFailException iofe = (InstanceOperationFailException) e;
//                deployOp.setInstanceId(iofe.getInstanceId());
//                deployOp.setAgentIp(iofe.getAgentIp());
//                deployOp.setInstanceIp(iofe.getInstanceIp());
//                deployOp.setPort(ImageUtils.defaultPort(deployCtx.getApp().getImage()));
//            }
        } finally {
//            deployListener.postCreate(deployOp, deployCtx.getTryOnline());
        }

        return hostOperation;
    }

    private void notifyDeployListener(boolean allSuccess, OperationListener operationListener) {
        if (allSuccess) {
            operationListener.onSuccess();
        } else {
            operationListener.onFail();
        }
    }

}
