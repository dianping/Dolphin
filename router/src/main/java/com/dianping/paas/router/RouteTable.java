package com.dianping.paas.router;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/16 14:13.
 */
public interface RouteTable {
    boolean add(RouteEntry entry);

    boolean remove(RouteEntry entry);

    boolean enable(RouteEntry entry);

    boolean disable(RouteEntry entry);
}
