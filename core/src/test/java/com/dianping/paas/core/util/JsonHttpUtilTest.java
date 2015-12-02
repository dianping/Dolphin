package com.dianping.paas.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.List;

/**
 * with {@link com.dianping.paas.api.controller.test.TestController} to test
 * <p/>
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/16 17:25.
 */
public class JsonHttpUtilTest {

    private static final String BASE_URL = "http://127.0.0.1:8080/test";

    private static final User USER = new User("chao.yu", 28);

    @Test
    public void testPostOne() throws Exception {
        String url = buildUrl("postOne");
        User user = JsonHttpUtil.postOne(url, User.class);
        Assert.hasLength(user.getName());
    }

    private String buildUrl(String path) {
        return BASE_URL + "/" + path;
    }

    @Test
    public void testPostCollection() throws Exception {
        String url = buildUrl("postList");
        List<User> userList = JsonHttpUtil.postCollection(url, new TypeReference<List<User>>() {
        });
        Assert.hasLength(userList.get(0).getName());
    }

    @Test
    public void testPostOneWithRequestBody() throws Exception {
        String url = buildUrl("postOneWithRequestBody");
        User user = JsonHttpUtil.postOne(url, USER, User.class);
        Assert.hasLength(user.getName());
    }

    @Test
    public void testPostCollectionWithRequestBody() throws Exception {
        String url = buildUrl("postListWithRequestBody");
        List<User> userList = JsonHttpUtil.postCollection(url, USER, new TypeReference<List<User>>() {
        });
        Assert.hasLength(userList.get(0).getName());
    }

    @Test
    public void testGet() throws Exception {
        String url = buildUrl("getOne");
        User user = JsonHttpUtil.getOne(url, User.class);
        Assert.hasLength(user.getName());
    }

    @Test
    public void testGetCollection() throws Exception {
        String url = buildUrl("getList");
        List<User> userList = JsonHttpUtil.getCollection(url, new TypeReference<List<User>>() {
        });
        Assert.hasLength(userList.get(0).getName());
    }

    @Data
    static class User {
        private String name;

        private int age;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}