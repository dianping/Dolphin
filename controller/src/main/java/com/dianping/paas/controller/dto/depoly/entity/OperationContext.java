package com.dianping.paas.controller.dto.depoly.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/14.
 */
@Data
public class OperationContext {

    private String appId;

    private List<Long> instanceIndexs = new ArrayList<Long>();

    private List<Long> instanceGroups = new ArrayList<Long>();

}
