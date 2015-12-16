package com.dianping.paas.core.dto.response;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/16 11:23.
 */
@Data
@ToString(callSuper = true)
public class InstanceRemoveResponse extends Response {
    private Date date = new Date();
}
