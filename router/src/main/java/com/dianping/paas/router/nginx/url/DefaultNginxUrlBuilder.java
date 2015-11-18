package com.dianping.paas.router.nginx.url;

import com.dianping.paas.config.ConfigManager;
import com.dianping.paas.extension.ExtensionLoader;
import org.springframework.stereotype.Component;

/**
 * Created by yuchao on 11/18/15.
 */
@Component
public class DefaultNginxUrlBuilder implements NginxUrlBuilder {

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);

    public String buildAddMemberUrl(String upstream) {
        return String.format("http://%s/api/v2/pool/%s/addMember", configManager.getSlbDomain(), upstream);
    }

    public String buildDelMemberUrl(String upstream) {
        return String.format("http://%s/api/v2/pool/%s/delMember", configManager.getSlbDomain(), upstream);
    }

    public String buildDeployUrl(String upstream) {
        return String.format("http://%s/api/pool/%s/deploy", configManager.getSlbDomain(), upstream);
    }


    public String buildUpdateMemberUrl(String upstream) {
        return String.format("http://%s/api/v2/pool/%s/updateMember", configManager.getSlbDomain(), upstream);
    }

}
