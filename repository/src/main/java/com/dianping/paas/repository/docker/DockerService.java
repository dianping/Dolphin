package com.dianping.paas.repository.docker;

import com.dianping.paas.core.dto.request.DockerfileRequest;
import com.dianping.paas.core.dto.response.DockerfileResponse;

/**
 * Created by yuchao on 11/23/15.
 */
public interface DockerService {

    DockerfileResponse buildImageAndPush(DockerfileRequest request) throws Exception;
}
