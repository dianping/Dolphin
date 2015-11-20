package docker.client.spring.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by yuchao on 11/20/15
 */
public class DockerNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("docker", new DockerClientBeanDefinitionParser());
    }
}
