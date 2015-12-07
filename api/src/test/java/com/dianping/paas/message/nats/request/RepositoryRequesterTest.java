package com.dianping.paas.message.nats.request;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.request.DockerfileRequest;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.core.message.nats.request.RepositoryRequester;
import com.dianping.paas.test.SpringTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuchao on 12/4/15.
 */
public class RepositoryRequesterTest extends SpringTest {

    @Resource
    private RepositoryRequester repositoryRequester;

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);


    @Test
    public void uploadWebPackage() throws Exception {
        AllocateWebPackageRequest request = new AllocateWebPackageRequest();
        request.setApp_id("test");
        request.setApp_version("v1");

        Assert.assertNotNull(repositoryRequester.allocateWebPackage(request));
    }

    @Test
    public void newAndDeploy() throws Exception {
        DockerfileRequest dockerfileRequest = new DockerfileRequest();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("path", "/tmp/1.txt");

        dockerfileRequest.setAppName("dp1");
        dockerfileRequest.setDockerfileTemplateContent("FROM");
        dockerfileRequest.setDockerfileLocation(buildDockerfileLocation("dp"));
        dockerfileRequest.setDockerfileTemplateContent("FROM busybox:latest\necho 'hello' > ${path}");
        dockerfileRequest.setDockerfileParams(params);

        repositoryRequester.newAndDeploy(dockerfileRequest);
        Thread.sleep(10000);
    }

    private String buildDockerfileLocation(String app_id) {
        return String.format("%s/%s/dockerfiles/Dockerfile", configManager.getBaseWebappDir(), app_id);
    }
}