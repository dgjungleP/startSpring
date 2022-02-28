package com.jungle.spring;


import com.jungle.spring.mysrping.config.ApplicationConfig;
import com.jungle.spring.mysrping.context.ApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(ApplicationConfig.class);
        System.out.println(applicationContext.getBean("userService"));
    }

}
