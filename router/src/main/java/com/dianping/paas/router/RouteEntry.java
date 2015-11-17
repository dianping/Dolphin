package com.dianping.paas.router;

import lombok.Data;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/16 14:14.
 */
@Data
public class RouteEntry {
    private String appId;

    private String serverIp;

    private String serverPort;

    private String state;
}



