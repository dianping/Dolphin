package docker.client.spring.config;

import docker.client.spring.DockerClientFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

/**
 * Created by yuchao on 11/20/15.
 */
public class DockerClientBeanDefinitionParser implements BeanDefinitionParser {

    public static final String ATTRIBUTE_ID = "id";

    public static final String URI = "uri";
    public static final String VERSION = "version";
    public static final String DOCKER_CERT_PATH = "dockerCertPath";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String SERVER_ADDRESS = "serverAddress";

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DockerClientFactoryBean.class);

        String id = element.getAttribute(ATTRIBUTE_ID);
        builder.addPropertyValue(URI, getUri(element));
        builder.addPropertyValue(VERSION, getVersion(element));
        builder.addPropertyValue(DOCKER_CERT_PATH, getDockerCertPath(element));
        builder.addPropertyValue(USERNAME, getUsername(element));
        builder.addPropertyValue(PASSWORD, getPassword(element));
        builder.addPropertyValue(EMAIL, getEmail(element));
        builder.addPropertyValue(SERVER_ADDRESS, getServerAddress(element));


        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

        return beanDefinition;
    }

    private String getVersion(Element element) {
        return getContentByTag(element, VERSION);
    }

    private String getServerAddress(Element element) {
        return getContentByTag(element, SERVER_ADDRESS);
    }

    private String getEmail(Element element) {
        return getContentByTag(element, EMAIL);
    }

    private String getPassword(Element element) {
        return getContentByTag(element, PASSWORD);
    }

    private String getUsername(Element element) {
        return getContentByTag(element, USERNAME);
    }

    private String getDockerCertPath(Element element) {
        return getContentByTag(element, DOCKER_CERT_PATH);
    }

    private String getUri(Element element) {
        return getContentByTag(element, URI);
    }

    private String getContentByTag(Element element, String tagName) {
        Element childElementByTagName = DomUtils.getChildElementByTagName(element, tagName);
        if (childElementByTagName != null) {
            return childElementByTagName.getFirstChild().getTextContent().trim();
        }
        return null;
    }
}

