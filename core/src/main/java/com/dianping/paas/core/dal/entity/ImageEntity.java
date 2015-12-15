package com.dianping.paas.core.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by yuchao on 12/1/15.
 */
@Data
public class ImageEntity extends Entity {
    private String image_type;
    private String dockerfile_template;
    private Date creation_date;
    private Date last_modified_date;
}
