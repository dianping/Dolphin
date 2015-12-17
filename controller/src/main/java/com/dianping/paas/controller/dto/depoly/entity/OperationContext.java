package com.dianping.paas.controller.dto.depoly.entity;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyapu on 15/12/14.
 */
@Data
public class OperationContext {

    private String appId;

    private List<Long> instanceIndexs = Lists.newArrayList();

    private List<Long> instanceGroups = Lists.newArrayList();

}
