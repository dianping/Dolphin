package com.dianping.paas.test;

import com.dianping.paas.util.JsonUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Web 测试的基础类,不需要启动web应用即可进行集成测试
 * <p/>
 * chao.yu@dianping.com
 * Created by yuchao on 15/11/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(value = {"classpath*:config/spring/appcontext-*"})
public abstract class ControllerBaseTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }


    protected MockHttpServletResponse get(String path, Map<String, String> params) throws Exception {
        MockHttpServletRequestBuilder httpServletRequestBuilder = MockMvcRequestBuilders.get(path);

        return service(params, httpServletRequestBuilder);
    }

    protected MockHttpServletResponse post(String path, Map<String, String> params) throws Exception {
        MockHttpServletRequestBuilder httpServletRequestBuilder = MockMvcRequestBuilders.post(path);

        return service(params, httpServletRequestBuilder);
    }

    private MockHttpServletResponse service(Map<String, String> params, MockHttpServletRequestBuilder httpServletRequestBuilder) throws Exception {
        if (params != null) {
            for (String name : params.keySet()) {
                httpServletRequestBuilder.param(name, params.get(name));
            }
        }

        return mockMvc.perform(httpServletRequestBuilder).andExpect(status().isOk()).andReturn().getResponse();
    }

    protected MockHttpServletResponse get(String path) throws Exception {
        return get(path, null);
    }

    protected MockHttpServletResponse post(String path) throws Exception {
        return post(path, null);
    }


    protected String getResponseAsString(String path) throws Exception {
        return get(path, null).getContentAsString();
    }

    protected String postResponseAsString(String path) throws Exception {
        return post(path, null).getContentAsString();
    }


    protected String getResponseAsString(String path, Map<String, String> params) throws Exception {
        return get(path, params).getContentAsString();
    }

    protected String postResponseAsString(String path, Map<String, String> params) throws Exception {
        return post(path, params).getContentAsString();
    }


    protected <T> T getResponseAsBean(String path) throws Exception {
        return getResponseAsBean(path, null);
    }

    protected <T> T postResponseAsBean(String path) throws Exception {
        return getResponseAsBean(path, null);

    }


    protected <T> T getResponseAsBean(String path, Map<String, String> params) throws Exception {
        String json = getResponseAsString(path, params);

        return JsonUtil.toBean(json);
    }

    protected <T> T postResponseAsBean(String path, Map<String, String> params) throws Exception {
        String json = postResponseAsString(path, params);
        return JsonUtil.toBean(json);
    }
}
