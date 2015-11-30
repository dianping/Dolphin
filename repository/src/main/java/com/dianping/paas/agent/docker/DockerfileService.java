package com.dianping.paas.agent.docker;

import com.dianping.paas.agent.docker.dto.DockerfileRequest;
import com.dianping.paas.agent.docker.dto.DockerfileResponse;

/**
 * Created by yuchao on 11/23/15.
 */
public interface DockerfileService {

    DockerfileResponse buildAndPushImage(DockerfileRequest request) throws Exception;
}
