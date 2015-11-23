package com.dianping.paas.agent.template;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuchao on 11/23/15.
 */
public class TemplateServiceTest {
    private static final Logger logger = LogManager.getLogger(TemplateServiceTest.class);

    private File templateFile;
    private String templateContent;
    private Map<String, Object> root;

    private TemplateService templateService;

    @Before
    public void setUp() throws Exception {
        String templateLocation = getClass().getClassLoader().getResource("template.ftl").getPath();
        templateFile = new File(templateLocation);
        templateContent = "hello, ${name}...";
        root = new HashMap<String, Object>();
        root.put("name", "chao.yu");

        templateService = new TemplateServiceFreemarkerImpl();
        ((TemplateServiceFreemarkerImpl) templateService).init();
    }

    @Test
    public void getContentFromTemplateFile() throws Exception {
        String content = templateService.getContentFromTemplateFile(templateFile, root);
        logger.info("The content is ==> " + content);
        Assert.assertNotNull(content);

    }

    @Test
    public void getContentFromTemplateContent() throws Exception {
        String content = templateService.getContentFromTemplateContent(templateContent, root);
        logger.info("The content is ==> " + content);
        Assert.assertNotNull(content);
    }
}