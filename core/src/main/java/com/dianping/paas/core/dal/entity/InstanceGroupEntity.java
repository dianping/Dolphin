package com.dianping.paas.core.dal.entity;

import lombok.Data;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/17.
 */
@Data
public class InstanceGroupEntity {
    private long id;

    private String appId;

    private String name;

    private int maxInstance;

    private long keyId;
}
