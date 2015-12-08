package com.dianping.paas.core.dto.request;

import lombok.Data;

import java.util.Map;

/**
 * Created by yuchao on 11/23/15.
 */
@Data
public class DockerfileRequest {

    /**
     * Dockerfile 模板的路径, example: path/to/dockerfiles/Dockerfile-template
     */
    private String dockerfileTemplateLocation;

    /**
     * Dockerfile文件中的占位符对应的值
     * for example:
     * path -> /tmp/hello.text
     */
    private Map<String, Object> dockerfileParams;

    /**
     * 生成后的完整的dockerfile文件路径, example: path/to/dockerfiles/Dockerfile
     */
    private String dockerfileLocation;

    /**
     * 哪个app需要使用该Dockerfile, 必须小写
     */
    private String appName;

    /**
     * app 的标签, 用于区分同一个app的不同image, 默认为latest
     */
    private String appTag = "latest";

}
