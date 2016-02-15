package com.dianping.paas.core.dal.entity;

import lombok.Data;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
@Data
public class AppPlanEntity {
    private long id;
    private String name;
    private int minInstanceCount;
    private int maxInstanceCount;
    private double cpu;
    private int cpuSharable;
    private long memory;
    private long keyId;

}
