package com.dianping.paas.repository.docker;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.repository.docker.dto.DockerfileRequest;
import com.dianping.paas.repository.docker.dto.DockerfileResponse;
import com.dianping.paas.repository.template.TemplateService;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.core.spring.conditional.annotation.AgentModule;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Identifier;
import com.github.dockerjava.api.model.PushResponseItem;
import com.github.dockerjava.api.model.Repository;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import com.github.dockerjava.core.command.PushImageResultCallback;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by yuchao on 11/23/15.
 */
@AgentModule
public class DockerfileServiceImpl implements DockerfileService {
    private static final Logger logger = LogManager.getLogger(DockerfileServiceImpl.class);

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);
    @Resource
    private DockerClient dockerClient;

    @Resource
    private TemplateService templateService;

    public DockerfileResponse buildAndPushImage(DockerfileRequest request) throws Exception {
        DockerfileResponse response = new DockerfileResponse();

        buildImage(request, response);
        pushImage(request);

        return response;
    }

    private void buildImage(DockerfileRequest request, DockerfileResponse response) throws Exception {
        logger.info(String.format("\nbegin buildImage, request ==>\n%s", request));

        String dockerfileContent = templateService.getContentFromTemplateContent(request.getDockerfileTemplateContent(), request.getDockerfileParams());
        response.setDockerfileContent(dockerfileContent);

        File dockerfileLocation = new File(request.getDockerfileLocation());
        Files.write(dockerfileContent, dockerfileLocation, Charset.defaultCharset());

        String imageId = dockerClient.buildImageCmd(dockerfileLocation).withTag(buildImageTag(request)).exec(new BuildImageResultCallback()).awaitImageId();
        response.setImageId(imageId);

        logger.info(String.format("\nend buildImage, response ==>\n%s", response));
    }


    private void pushImage(DockerfileRequest request) {
        logger.info(String.format("\nbegin pushImage, request ==>\n%s", request));

        Repository repository = new Repository(buildRepositoryName(request));
        Identifier identifier = new Identifier(repository, request.getAppTag());
        dockerClient.pushImageCmd(identifier).exec(new PushImageResultCallback() {
            @Override
            public void onNext(PushResponseItem item) {
                super.onNext(item);
                logger.info(item);
            }
        }).awaitSuccess();

        logger.info(String.format("\nend pushImage, request ==>\n%s", request));
    }

    private String buildImageTag(DockerfileRequest request) {
        return String.format("%s:%s", buildRepositoryName(request), request.getAppTag());
    }

    private String buildRepositoryName(DockerfileRequest request) {
        return String.format("%s/%s", configManager.getRepositoryUrl(), request.getAppName());
    }
}
