package com.dianping.paas.core.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/15 14:05.
 */
@Data
public class InstanceEntity {
    private int id;
    private String instance_id;
    private int instance_group_id;
    private String app_id;
    private String app_version;
    private String instance_ip;
    private String agent_ip;
    private int instance_port;
    private int type;
    private int status;
    private Date creation_date;
    private Date last_modified_date;
    private String image;
    private int instance_index;
}
