package com.dianping.paas.spring.conditional;

/**
 * agent模块启动的条件
 * Created by yuchao on 11/19/15.
 */
public class HealthManagerModuleCondition extends ModuleCondition {
    @Override
    protected String getModuleFile() {
        return configManager.getHealthManagerModule();
    }
}
