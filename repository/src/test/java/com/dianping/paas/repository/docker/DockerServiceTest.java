package com.dianping.paas.repository.docker;

import com.dianping.paas.core.dto.request.DockerfileRequest;
import com.dianping.paas.core.dto.response.DockerfileResponse;
import com.dianping.paas.core.test.Globals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuchao on 11/23/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config/spring/appcontext-*.xml")
public class DockerServiceTest {
    private static final Logger logger = LogManager.getLogger(DockerServiceTest.class);

    @Resource
    private DockerService dockerService;

    private DockerfileRequest dockerfileRequest;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("repository", Globals.REPOSITORY);

        dockerfileRequest = new DockerfileRequest();

        dockerfileRequest.setDockerfileParams(params);
        dockerfileRequest.setDockerfileTemplateLocation(getDockerfileTemplateLocation());
        dockerfileRequest.setDockerfileLocation("/data/temp/Dockerfile");
        dockerfileRequest.setAppName(Globals.APP_ID);
    }

    @Test
    public void buildImageAndPush() throws Exception {
        DockerfileResponse dockerfileResponse = dockerService.buildImageAndPush(dockerfileRequest);
        logger.info(dockerfileResponse);
        Assert.notNull(dockerfileResponse.getImageId());
    }

    private String getDockerfileTemplateLocation() throws Exception {
        return getClass().getClassLoader().getResource("dockerfiles/Dockerfile-template").getPath();
    }
}