package com.dianping.paas.core.dto.response;

import lombok.Data;
import lombok.ToString;

/**
 * Created by yuchao on 11/23/15.
 */

@Data
@ToString(callSuper = true)
public class DockerfileResponse extends Response {
    /**
     * 基于dockerfile模板和参数build出来的镜像id
     */
    private String imageId;

    /**
     * 基于dockerfile模板和参数生成的dockerfile完整内容
     */
    private String dockerfileContent;

    /**
     * docker pull 的URI
     */
    private String repository;
}
