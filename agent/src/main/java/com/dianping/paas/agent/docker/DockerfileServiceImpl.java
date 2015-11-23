package com.dianping.paas.agent.docker;

import com.dianping.paas.agent.docker.dto.DockerfileRequest;
import com.dianping.paas.agent.docker.dto.DockerfileResponse;
import com.dianping.paas.agent.template.TemplateService;
import com.dianping.paas.spring.conditional.annotation.AgentModule;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by yuchao on 11/23/15.
 */
@AgentModule
public class DockerfileServiceImpl implements DockerfileService {
    private static final Logger logger = LogManager.getLogger(DockerfileServiceImpl.class);

    @Resource
    private DockerClient dockerClient;

    @Resource
    private TemplateService templateService;

    public DockerfileResponse buildImageFromDockerfileTemplateLocation(DockerfileRequest request) throws Exception {
        File templateFile = new File(request.getDockerfileTemplateLocation());
        String dockerfileContent = templateService.getContentFromTemplateFile(templateFile, request.getDockerfileParams());

        return buildImage(request, dockerfileContent);
    }


    public DockerfileResponse buildImageFromDockerfileTemplateContent(DockerfileRequest request) throws Exception {
        String dockerfileContent = templateService.getContentFromTemplateContent(request.getDockerfileTemplateContent(), request.getDockerfileParams());

        return buildImage(request, dockerfileContent);
    }

    private DockerfileResponse buildImage(DockerfileRequest request, String dockerfileContent) throws IOException {
        logger.info(String.format("begin buildImage, request ==> %s", request));

        DockerfileResponse response = new DockerfileResponse();

        response.setDockerfileContent(dockerfileContent);
        File dockerfileLocation = new File(request.getDockerfileLocation());
        Files.write(dockerfileContent, dockerfileLocation, Charset.defaultCharset());
        String imageId = dockerClient.buildImageCmd(dockerfileLocation).withTag(buildImageTag(request)).exec(new BuildImageResultCallback()).awaitImageId();
        response.setImageId(imageId);

        logger.info(String.format("end buildImage, response ==> %s", response));

        return response;
    }

    private String buildImageTag(DockerfileRequest request) {
        return request.getAppName() + ":" + request.getAppTag();
    }
}
