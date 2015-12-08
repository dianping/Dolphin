package com.dianping.paas.repository.template;

import com.dianping.paas.core.util.TempFileGenerator;
import com.dianping.paas.core.spring.conditional.annotation.AgentModule;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by yuchao on 11/23/15.
 */
@Service
public class TemplateServiceFreemarkerImpl implements TemplateService {
    private static final Logger logger = LogManager.getLogger(TemplateServiceFreemarkerImpl.class);

    private Configuration cfg;

    public String getContentFromTemplateContent(String templateContent, Map<String, Object> root) {
        File tempFile = TempFileGenerator.generate(templateContent);

        return getContentFromTemplateFile(tempFile, root);
    }

    public String getContentFromTemplateFile(File templateFile, Map<String, Object> root) {
        String explictFileContent = null;
        try {
            Writer out = new StringWriter();
            Template template = cfg.getTemplate(templateFile.getAbsolutePath());
            template.process(root, out);
            out.flush();
            explictFileContent = out.toString();
        } catch (Exception e) {
            logger.error(String.format("error when createContent, templateFile[%s], root[%s]", templateFile, root), e);
        }

        return explictFileContent;
    }

    @PostConstruct
    public void init() throws Exception {
        cfg = new Configuration();
        cfg.setTemplateLoader(new FileTemplateLoader(new File("/")));
    }
}
