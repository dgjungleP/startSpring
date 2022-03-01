package com.jungle.spring.service;

import com.jungle.spring.mysrping.annotation.Autowired;
import com.jungle.spring.mysrping.annotation.Component;
import com.jungle.spring.mysrping.annotation.Scope;

@Component("userService")
public class UserService {


    @Autowired
    OrderService orderService;

    public void test() {
        System.out.println(orderService);
    }
}
