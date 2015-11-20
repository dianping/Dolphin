package docker.client.spring;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.beans.factory.FactoryBean;

public class DockerClientFactoryBean implements FactoryBean<DockerClient> {

    private String serverUrl;

    public DockerClient getObject() throws Exception {
        return DockerClientBuilder.getInstance(serverUrl).build();
    }

    public Class<?> getObjectType() {
        return DockerClient.class;
    }

    public boolean isSingleton() {
        return true;
    }


    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}