package com.dianping.paas.router.nginx;

import com.dianping.paas.router.RouteEntry;
import lombok.Data;

/**
 * Created by yuchao on 11/17/15.
 */
@Data
public class NginxSlbRequest {
    private static final int DEFAULT_WEIGHT = 1;

    private String name;

    private String ip;

    private String port;

    private int weight;

    private String state;

    public static NginxSlbRequest createInstanceFrom(RouteEntry routeEntry) {
        NginxSlbRequest nginxSlbRequest = new NginxSlbRequest();

        nginxSlbRequest.setIp(routeEntry.getServerIp());
        nginxSlbRequest.setName(routeEntry.getServerIp() + "-" + routeEntry.getServerPort());
        nginxSlbRequest.setWeight(DEFAULT_WEIGHT);
        nginxSlbRequest.setPort(routeEntry.getServerPort());
        nginxSlbRequest.setState(routeEntry.getState());

        return nginxSlbRequest;
    }
}
