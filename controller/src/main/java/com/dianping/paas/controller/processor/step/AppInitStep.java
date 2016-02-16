package com.dianping.paas.controller.processor.step;

import com.dianping.paas.controller.dto.depoly.entity.OperationContext;
import com.dianping.paas.controller.exception.AppExistException;
import com.dianping.paas.controller.exception.AppPlanNotExistException;
import com.dianping.paas.controller.executor.DeployExecutor;
import com.dianping.paas.controller.executor.context.InstanceDeployContext;
import com.dianping.paas.controller.record.OperationListener;
import com.dianping.paas.controller.record.OperationRecorder;
import com.dianping.paas.controller.sequencer.Task;
import com.dianping.paas.controller.sequencer.TaskSequencer;
import com.dianping.paas.core.dal.*;
import com.dianping.paas.core.dal.entity.*;
import com.dianping.paas.core.dto.request.AppInitRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.dto.response.AsyncOperationResponse;
import com.dianping.paas.core.dto.response.ResultCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
@Component
public class AppInitStep {

    private static final Logger logger = LogManager.getLogger(AppInitStep.class);

    @Resource
    private AppDal appDal;

    @Resource
    private AppPlanDal appPlanDal;

    @Resource
    private AppConfigDal appConfigDal;

    @Resource
    private QuotaDal quotaDal;

    @Resource
    private InstanceGroupDal instanceGroupDal;

    @Resource
    private TaskSequencer taskSequencer;

    @Resource
    private DeployExecutor deployExecutor;

    @Resource
    private OperationListener operationListener;

    @Resource
    private OperationRecorder operationRecorder;

    public static final String APP_INIT_VERSION = "0.0.1-paas";

    public boolean validAppInitRequest(AppInitRequest appInitRequest) {
        return true;
    }

    public void saveAppInfo(AppInitRequest appInitRequest, AsyncOperationResponse response) throws Exception {
        AppPlanEntity appPlan = appPlanDal.findByPK(appInitRequest.getAppPlanId());
        if (appPlan == null) {
            response.fail(ResultCode.DB_APP_PLAN_NOT_EXIST);
            throw new AppPlanNotExistException("the appPlanId is not valid, can not find it database");
        }
        AppEntity app = appDal.findByAppId(appInitRequest.getAppId());
        if (app != null) {
            response.fail(ResultCode.DB_APP_EXIST);
            throw new AppExistException("the app already exists");
        }
        app = new AppEntity();
        app.setAppId(appInitRequest.getAppId());
        app.setCreationDate(new Date());
        app.setLastModifiedDate(new Date());
        app.setLevel(appInitRequest.getLevel());
        app.setOwner(appInitRequest.getOwner());
        app.setType(appInitRequest.getType());
        app.setAppPlanName(appPlan.getName());
        app.setImage(appInitRequest.getImage());
        app.setMachineLabel(appInitRequest.getMachineLabel());

        AppConfigEntity appConfig = new AppConfigEntity();
        appConfig.setAppId(appInitRequest.getAppId());
        appConfig.setCreationDate(new Date());
        appConfig.setLastModifiedDate(new Date());
        appConfig.setLdapBase(appInitRequest.getLdapBase() + ",ou=auth,dc=dianping,dc=com");
        // need phenix kernal?

        appConfig.setWarmUpUrl(appInitRequest.getWarmUpURL());

        QuotaEntity quota = new QuotaEntity();
        quota.setCpu(appPlan.getCpu());
        quota.setCpuSharable(appPlan.getCpuSharable());
        quota.setMaxInstanceCount(appPlan.getMaxInstanceCount());
        quota.setMinInstanceCount(appPlan.getMinInstanceCount());
        quota.setMemory(appPlan.getMemory());

        appConfigDal.insert(appConfig);
        app.setQuota(quota);
        quotaDal.insert(quota);
        app.setQuotaId(quota.getId());
        appDal.insert(app);

    }

    public void saveInstanceGroupInfo(AppInitRequest appInitRequest) {
        InstanceGroupEntity instanceGroup1 = new InstanceGroupEntity();
        String appId = appInitRequest.getAppId();
        instanceGroup1.setAppId(appId);
        instanceGroup1.setMaxInstance(1);
        instanceGroup1.setName("paas group1");
        instanceGroupDal.insert(instanceGroup1);

        InstanceGroupEntity instanceGroup2 = new InstanceGroupEntity();
        instanceGroup2.setAppId(appId);
        instanceGroup2.setMaxInstance(10);
        instanceGroup2.setName("paas group2");
        instanceGroupDal.insert(instanceGroup2);
    }

    public AllocateWebPackageResponse allocatePkgToRespository(AppInitRequest appInitRequest) {

        AllocateWebPackageResponse allocateWebPackageResponse = new AllocateWebPackageResponse();

        try {
            allocateWebPackageResponse = deployExecutor.allocateRespository(appInitRequest.getAppId(), APP_INIT_VERSION, "");
        } catch (Exception e) {
            logger.error("error when allocate package", e);
        }

        return allocateWebPackageResponse;
    }

    public long receiveOperationId(final AppInitRequest appInitRequest) {
        final OperationContext opCtx = new OperationContext();
        opCtx.setAppId(appInitRequest.getAppId());
        long opId = taskSequencer.queueAndRun(opCtx, OperationEntity.TYPE_CREATE, new Task() {
            @Override
            public void execute() {
                initInstance(opCtx, APP_INIT_VERSION, appInitRequest.getInstanceCount());
            }
        });
        return opId;
    }

    private void initInstance(OperationContext opCtx, String appInitVersion, int instanceCount) {

        AppConfigEntity appConfig = appConfigDal.findByAppId(opCtx.getAppId());
        AppEntity app = appDal.findByAppId(opCtx.getAppId());
        List<InstanceDeployContext> instanceDeployContexts = deployExecutor.prepareInstanceDeployContext(opCtx, app, appConfig, instanceCount, false);
        try {
            operationListener.init(opCtx, operationRecorder);
            deployExecutor.create(opCtx, instanceDeployContexts, operationListener);
        } catch (RuntimeException e) {
            logger.error("Unexpected exception in deploy", e);
        }


    }


}
