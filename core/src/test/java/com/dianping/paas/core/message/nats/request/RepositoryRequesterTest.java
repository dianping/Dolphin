package com.dianping.paas.core.message.nats.request;

import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.request.DockerfileRequest;
import com.dianping.paas.test.SpringTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by yuchao on 12/4/15.
 */
public class RepositoryRequesterTest extends SpringTest {

    @Resource
    private RepositoryRequester repositoryRequester;


    @Test
    public void uploadWebPackage() throws Exception {
        AllocateWebPackageRequest request = new AllocateWebPackageRequest();
        request.setApp_id("test");
        request.setApp_version("v1");

        Assert.assertNotNull(repositoryRequester.allocateWebPackage(request));
    }

    @Test
    public void testNewAndDeploy() throws Exception {
        DockerfileRequest dockerfileRequest = new DockerfileRequest();
        repositoryRequester.newAndDeploy(dockerfileRequest);
    }
}