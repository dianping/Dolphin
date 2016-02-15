package com.dianping.paas.controller.strategy;

import java.util.List;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/3.
 */
public interface InstanceGroupStrategy {

	List<Long> allocateInstanceGroup(String appId, int instanceCount);

}
