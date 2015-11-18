package com.dianping.paas.router.nginx.url;

/**
 * Created by yuchao on 11/18/15.
 */
public interface NginxUrlBuilder {
    String buildAddMemberUrl(String upstream);

    String buildDelMemberUrl(String upstream);

    String buildDeployUrl(String upstream);

    String buildUpdateMemberUrl(String upstream);
}
