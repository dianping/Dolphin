package com.dianping.paas.controller.test;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 测试用Controller
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/16 17:08.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final User USER = new User("chao.yu", 18);

    private static final List<User> USER_LIST = Collections.singletonList(USER);

    @RequestMapping(value = "getOne", method = RequestMethod.GET)
    public User getOne() {
        return USER;
    }

    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<User> getList() {
        return USER_LIST;
    }


    @RequestMapping(value = "postOne", method = RequestMethod.POST)
    public User postOne() {
        return USER;
    }

    @RequestMapping(value = "postList", method = RequestMethod.POST)
    public List<User> postList() {
        return USER_LIST;
    }

    @RequestMapping(value = "postOneWithRequestBody", method = RequestMethod.POST)
    public User postOneWithRequestBody(@RequestBody User user) {
        return user;
    }

    @RequestMapping(value = "postListWithRequestBody", method = RequestMethod.POST)
    public List<User> postListWithRequestBody(@RequestBody User user) {
        return Collections.singletonList(user);
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
