package com.jungle.spring.service;

import com.jungle.spring.mysrping.annotation.Autowired;
import com.jungle.spring.mysrping.annotation.Component;
import com.jungle.spring.mysrping.annotation.Scope;
import com.jungle.spring.mysrping.aware.BeanNameAware;
import com.jungle.spring.mysrping.context.InitializeBean;

@Component("userService")
public class UserService implements BeanNameAware, InitializeBean {


    @Autowired
    OrderService orderService;

    private String beanName;

    public void test() {
        System.out.println(orderService);
        System.out.println(beanName);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;

    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Initial success");
    }
}
