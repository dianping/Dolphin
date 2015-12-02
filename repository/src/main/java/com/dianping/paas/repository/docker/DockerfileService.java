package com.dianping.paas.repository.docker;

import com.dianping.paas.core.dto.DockerfileRequest;
import com.dianping.paas.core.dto.DockerfileResponse;

/**
 * Created by yuchao on 11/23/15.
 */
public interface DockerfileService {

    DockerfileResponse buildAndPushImage(DockerfileRequest request) throws Exception;
}
