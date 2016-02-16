package com.dianping.paas.controller.service.impl;

import com.dianping.paas.controller.processor.step.AppInitStep;
import com.dianping.paas.controller.service.AppControllerService;
import com.dianping.paas.core.dal.AppDal;
import com.dianping.paas.core.dal.entity.AppEntity;
import com.dianping.paas.core.dto.request.AppInitRequest;
import com.dianping.paas.core.dto.response.AsyncOperationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/14.
 */
@Service
public class AppControllerServiceImpl implements AppControllerService {

    public static final Logger logger = LogManager.getLogger(AppControllerServiceImpl.class);

    @Resource
    private AppInitStep appInitStep;

    @Resource
    private AppDal appDal;

    @Override
    public List<AppEntity> findAllApp() {
        return appDal.findAll();
    }

    @Override
    public AsyncOperationResponse initApp(AppInitRequest appInitRequest) {
        AsyncOperationResponse response = new AsyncOperationResponse();

        try {
            appInitStep.validAppInitRequest(appInitRequest);
            appInitStep.saveAppInfo(appInitRequest, response);
            appInitStep.saveInstanceGroupInfo(appInitRequest);
            appInitStep.allocatePkgToRespository(appInitRequest);

            long opId = appInitStep.receiveOperationId(appInitRequest);
            response.setOperationId(opId);
            response.success();
        } catch (Exception e) {
            logger.error("init app fail : " + response.getReturnCode().getReason());
        }
        return response;
    }


}
