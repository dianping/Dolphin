package com.dianping.paas.agent.container.config;

import com.dianping.paas.agent.context.CreateContainerContext;
import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.AccessMode;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Volume;
import org.springframework.stereotype.Component;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/14 11:51.
 */
@Component("BindVolumeCreateContainerPostProcessor")
public class BindVolumeCreateContainerPostProcessor implements CreateContainerPostProcessor {

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);

    @Override
    public void postProcessContainer(CreateContainerContext createContainerContext) {
        CreateContainerCmd createContainerCmd = createContainerContext.getCreateContainerCmd();

        Bind bind = createBind(createContainerContext.getInstanceStartRequest());
        createContainerCmd.withBinds(bind);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }


    private Bind createBind(InstanceStartRequest request) {
        String outer = configManager.getOuterWebPackageRootDir(request.getAppName(), request.getInstanceIndex());
        Volume inner = new Volume(configManager.getInnerWebPackageRootDir());

        return new Bind(outer, inner, AccessMode.rw);
    }
}
