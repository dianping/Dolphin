package com.dianping.paas.core.service;

import com.dianping.paas.core.dto.request.DockerfileRequest;
import com.dianping.paas.core.dto.response.DockerfileResponse;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/02 14:20.
 */
public interface AgentService {
    void pullImageAndRun(DockerfileRequest dockerfileRequest, DockerfileResponse dockerfileResponse);
}
