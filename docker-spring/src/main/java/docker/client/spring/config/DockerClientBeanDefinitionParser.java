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
    public static final String ELEMENT_SERVER_URL = "serverUrl";

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DockerClientFactoryBean.class);

        String id = element.getAttribute(ATTRIBUTE_ID);
        builder.addPropertyValue(ELEMENT_SERVER_URL, getServerUrl(element));

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

        return beanDefinition;
    }

    private String getServerUrl(Element element) {
        return DomUtils.getChildElementByTagName(element, ELEMENT_SERVER_URL).getFirstChild().getTextContent().trim();
    }
}
