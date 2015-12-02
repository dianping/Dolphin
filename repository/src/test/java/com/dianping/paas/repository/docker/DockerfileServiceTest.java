package com.dianping.paas.repository.docker;

import com.dianping.paas.core.dto.DockerfileRequest;
import com.dianping.paas.core.dto.DockerfileResponse;
import com.google.common.io.Files;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
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
        dockerfileRequest.setDockerfileTemplateContent(getDockerfileTemplateContent());
        dockerfileRequest.setDockerfileLocation("/data/temp/Dockerfile");
        dockerfileRequest.setAppName("dockerfile");
    }

    @Test
    public void buildAndPushImage() throws Exception {
        dockerfileRequest.setAppTag(System.currentTimeMillis() + "");
        DockerfileResponse dockerfileResponse = dockerfileService.buildAndPushImage(dockerfileRequest);
        logger.info("\ndockerfileResponse ==>\n" + dockerfileResponse);
        Assert.notNull(dockerfileResponse.getImageId());
    }

    private String getDockerfileTemplateContent() throws Exception {
        String path = getClass().getClassLoader().getResource("dockerfiles/Dockerfile-template").getPath();
        File file = new File(path);
        List<String> lines = Files.readLines(file, Charset.defaultCharset());

        return StringUtils.join(lines, "\n");
    }
}