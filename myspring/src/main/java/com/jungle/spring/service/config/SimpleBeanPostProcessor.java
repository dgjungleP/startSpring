package com.jungle.spring.service.config;

import com.jungle.spring.mysrping.annotation.Component;
import com.jungle.spring.mysrping.beanpostprocesser.BeanPostProcessor;

@Component
public class SimpleBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Before initial :" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("After initial :" + beanName);
        return bean;
    }
}
