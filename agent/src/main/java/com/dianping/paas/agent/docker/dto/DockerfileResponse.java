package com.dianping.paas.agent.docker.dto;

import lombok.Data;

/**
 * Created by yuchao on 11/23/15.
 */

@Data
public class DockerfileResponse {
    /**
     * 基于dockerfile模板和参数build出来的镜像id
     */
    private String imageId;

    /**
     * 基于dockerfile模板和参数生成的dockerfile完整内容
     */
    private String dockerfileContent;
}
