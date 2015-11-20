package docker.client.spring;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yuchao on 11/20/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:dockerClientFactoryContext.xml"})
public class DockerClientFactoryBeanTest {

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private DockerClient dockerClient;

    @Test
    public void dockerClientFactory() throws Exception {
        Assert.assertNotNull(dockerClient);
    }

    @Test
    public void dockerInfo() throws Exception {
        Info info = dockerClient.infoCmd().exec();
        Assert.assertNotNull(info);
    }

}