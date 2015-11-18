package com.dianping.paas.router;

import lombok.Data;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/16 14:14.
 */
@Data
public class RouteEntry {
    public static final String STATE_DISABLE = "DISABLE";

    public static final String STATE_ENABLE = "ENABLE";

    private String appId;

    private String serverIp;

    private String serverPort;

    private String state;

    public void disable() {
        setState(STATE_DISABLE);
    }

    public void enable() {
        setState(STATE_ENABLE);
    }
}



