package docker.client.spring;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.springframework.beans.factory.FactoryBean;

public class DockerClientFactoryBean implements FactoryBean<DockerClient> {

    private String uri;
    private String version;
    private String dockerCertPath;
    private String username;
    private String password;
    private String email;
    private String serverAddress;

    public DockerClient getObject() throws Exception {
        DockerClientConfig config = DockerClientConfig.createDefaultConfigBuilder()
                .withVersion(version)
                .withUri(uri)
                .withUsername(username)
                .withPassword(password)
                .withEmail(email)
                .withServerAddress(serverAddress)
                .withDockerCertPath(dockerCertPath)
                .build();
        return DockerClientBuilder.getInstance(config).build();
    }

    public Class<?> getObjectType() {
        return DockerClient.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDockerCertPath(String dockerCertPath) {
        this.dockerCertPath = dockerCertPath;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}