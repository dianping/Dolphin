package com.dianping.paas.agent.docker;

import com.dianping.paas.agent.docker.dto.DockerfileRequest;
import com.dianping.paas.agent.docker.dto.DockerfileResponse;

/**
 * Created by yuchao on 11/23/15.
 */
public interface DockerfileService {

    /**
     * 基于 {@link DockerfileRequest#dockerfileTemplateLocation} build image
     */
    DockerfileResponse buildImageFromDockerfileTemplateContent(DockerfileRequest request) throws Exception;

    /**
     * 基于 {@link DockerfileRequest#dockerfileTemplateContent} build image
     */
    DockerfileResponse buildImageFromDockerfileTemplateLocation(DockerfileRequest request) throws Exception;
}
