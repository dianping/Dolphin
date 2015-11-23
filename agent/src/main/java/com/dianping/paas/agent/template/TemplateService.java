package com.dianping.paas.agent.template;

import java.io.File;
import java.util.Map;

/**
 * Created by yuchao on 11/23/15.
 */
public interface TemplateService {
    String getContentFromTemplateContent(String templateContent, Map<String, Object> root);

    String getContentFromTemplateFile(File templateFile, Map<String, Object> root);
}
