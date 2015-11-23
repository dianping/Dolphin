package com.dianping.paas.agent.docker;

import com.dianping.paas.agent.docker.dto.DockerfileRequest;
import com.dianping.paas.agent.docker.dto.DockerfileResponse;
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
public class DockerfileServiceTest {
    private static final Logger logger = LogManager.getLogger(DockerfileServiceTest.class);

    @Resource
    private DockerfileService dockerfileService;

    private DockerfileRequest dockerfileRequest;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("path", "/tmp/hello.text");

        dockerfileRequest = new DockerfileRequest();

        dockerfileRequest.setDockerfileParams(params);
        dockerfileRequest.setDockerfileTemplateLocation(getDockerfileTemplateLocation());
        dockerfileRequest.setDockerfileTemplateContent(getDockerfileTemplateContent());
        dockerfileRequest.setDockerfileLocation(getDockerfileLocation());
        dockerfileRequest.setAppName("dockerfile");
    }

    @Test
    public void buildImageFromDockerfileTemplateContent() throws Exception {
        dockerfileRequest.setAppTag("content");
        DockerfileResponse dockerfileResponse = dockerfileService.buildImageFromDockerfileTemplateContent(dockerfileRequest);
        logger.info("\n dockerfileResponse ==> " + dockerfileResponse);
        Assert.notNull(dockerfileResponse.getImageId());
    }

    @Test
    public void buildImageFromDockerfileTemplateLocation() throws Exception {
        dockerfileRequest.setAppTag("location");
        DockerfileResponse dockerfileResponse = dockerfileService.buildImageFromDockerfileTemplateLocation(dockerfileRequest);
        logger.info("\n dockerfileResponse ==> " + dockerfileResponse);
        Assert.notNull(dockerfileResponse.getImageId());
    }

    private String getDockerfileLocation() {
        return "/data/temp/Dockerfile";
    }

    private String getDockerfileTemplateLocation() {
        return getResourceLocation("dockerfiles/Dockerfile-template");
    }

    private String getDockerfileTemplateContent() {
        StringBuilder sb = new StringBuilder();

        sb
                .append("FROM busybox:latest")
                .append("\n")
                .append("RUN echo \"buildImageFromDockerfileTemplateContent() created this image!\" >> ${path}");

        return sb.toString();
    }

    private String getResourceLocation(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}