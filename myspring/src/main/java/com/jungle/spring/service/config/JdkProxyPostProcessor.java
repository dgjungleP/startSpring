package com.jungle.spring.service.config;

import com.jungle.spring.mysrping.annotation.Component;
import com.jungle.spring.mysrping.beanpostprocesser.BeanPostProcessor;
import com.jungle.spring.service.UserService;
import com.jungle.spring.service.UserServiceImpl;
import org.springframework.cglib.core.MethodWrapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class JdkProxyPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (UserService.class.isAssignableFrom(bean.getClass())) {
            return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("Proxy start");
                    return method.invoke(bean, args);
                }
            });
        }
        return bean;
    }
}
