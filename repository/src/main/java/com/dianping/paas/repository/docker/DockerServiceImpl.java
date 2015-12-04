package com.dianping.paas.repository.docker;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dto.request.DockerfileRequest;
import com.dianping.paas.core.dto.response.DockerfileResponse;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.core.util.FileUtil;
import com.dianping.paas.repository.template.TemplateService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Identifier;
import com.github.dockerjava.api.model.PushResponseItem;
import com.github.dockerjava.api.model.Repository;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import com.github.dockerjava.core.command.PushImageResultCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by yuchao on 11/23/15.
 */
@Component
public class DockerServiceImpl implements DockerService {
    private static final Logger logger = LogManager.getLogger(DockerServiceImpl.class);

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private DockerClient dockerClient;

    @Resource
    private TemplateService templateService;

    public DockerfileResponse buildImageAndPush(DockerfileRequest request) throws Exception {
        DockerfileResponse response = new DockerfileResponse();

        buildImage(request, response);
        pushImage(request, response);

        return response;
    }

    private void buildImage(DockerfileRequest request, DockerfileResponse response) throws Exception {
        logger.info(String.format("begin buildImage: %s", request));

        String dockerfileContent = templateService.getContentFromTemplateContent(request.getDockerfileTemplateContent(), request.getDockerfileParams());
        response.setDockerfileContent(dockerfileContent);

        File dockerfile = FileUtil.write(request.getDockerfileLocation(), dockerfileContent);

        String imageId = dockerClient.buildImageCmd(dockerfile).withTag(buildImageTag(request)).exec(new BuildImageResultCallback()).awaitImageId();
        response.setImageId(imageId);

        logger.info(String.format("end buildImage: %s", response));
    }

    private void pushImage(DockerfileRequest request, DockerfileResponse response) {
        logger.info(String.format("begin pushImage: %s", request));

        String repositoryName = buildRepositoryName(request);
        Repository repository = new Repository(repositoryName);
        Identifier identifier = new Identifier(repository, request.getAppTag());
        dockerClient.pushImageCmd(identifier).exec(new PushImageResultCallback() {
            @Override
            public void onNext(PushResponseItem item) {
                super.onNext(item);
                logger.info(item);
            }
        }).awaitSuccess();

        response.setRepository(String.format("%s:%s", repositoryName, request.getAppTag()));

        logger.info(String.format("end pushImage: %s", response));
    }

    private String buildImageTag(DockerfileRequest request) {
        return String.format("%s:%s", buildRepositoryName(request), request.getAppTag());
    }

    private String buildRepositoryName(DockerfileRequest request) {
        return String.format("%s/%s", configManager.getRepositoryUrl(), request.getAppName());
    }
}
