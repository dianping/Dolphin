package com.dianping.paas.core.spring.conditional;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.extension.ExtensionLoader;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * Created by yuchao on 11/19/15.
 */
public abstract class ModuleCondition implements Condition {
    protected ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);


    /**
     * 判断是否启动某个模块,只需要判断本机是否有该模块的文件或目录
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return fileExist(getModuleFile());
    }

    private boolean fileExist(String fileOrDir) {
        return StringUtils.hasText(fileOrDir) && new File(fileOrDir).exists();
    }

    protected abstract String getModuleFile();

}
