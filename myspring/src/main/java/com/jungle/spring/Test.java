package com.jungle.spring;


import com.jungle.spring.mysrping.config.ApplicationConfig;
import com.jungle.spring.mysrping.context.ApplicationContext;
import com.jungle.spring.service.UserService;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(ApplicationConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.test();
    }

}
