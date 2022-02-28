package com.jungle.spring.mysrping.context;

import com.jungle.spring.mysrping.annotation.Component;
import com.jungle.spring.mysrping.annotation.ComponentScan;
import com.jungle.spring.mysrping.annotation.Scope;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象容器
 */
public class ApplicationContext {
    private Class configClass;
    private ConcurrentHashMap<String, Object> singletonObjectMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public ApplicationContext(Class configClass) {
        this.configClass = configClass;
        //解析配置
        handleConfig();
    }

    private void handleConfig() {
        handleComponentScan();
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition definition = beanDefinitionMap.get(beanName);
            if (definition.getScope().equals("Single")) {
                Object bean = createBean(definition);
                singletonObjectMap.put(beanName, bean);
            }
        }
    }

    private Object createBean(BeanDefinition definition) {
        Class clazz = definition.getClazz();
        try {
            Object instance = clazz.getConstructor().newInstance();
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void handleComponentScan() {
        if (!configClass.isAnnotationPresent(ComponentScan.class)) {
            return;
        }
        ComponentScan componentScan = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScan.value();
        System.out.println(path);
        //扫描
        //1、 搜寻路径下的所有类
        ClassLoader loader = this.getClass().getClassLoader();
        URL resource = loader.getResource(path.replace(".", "/"));
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                String className = f.getPath();
                className = className.substring(className.indexOf("com"), className.indexOf(".class")).replaceAll("\\\\", ".");
                try {
                    Class<?> clazz = loader.loadClass(className);
                    if (clazz.isAnnotationPresent(Component.class)) {
                        //表示当前类是一个Bean对象
                        //解析类 BeanDefinition
                        Component component = clazz.getDeclaredAnnotation(Component.class);
                        BeanDefinition definition = new BeanDefinition();
                        definition.setClazz(clazz);
                        String beanName = component.value();
                        if (clazz.isAnnotationPresent(Scope.class)) {
                            Scope scope = clazz.getDeclaredAnnotation(Scope.class);
                            definition.setScope(scope.value());
                        } else {
                            definition.setScope("Single");
                        }
                        beanDefinitionMap.put(beanName, definition);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition definition = beanDefinitionMap.get(beanName);
            if (definition.getScope().equals("Single")) {
                Object o = singletonObjectMap.get(beanName);
                return o;
            } else {
                //创建bean
            }
        } else {
            return null;
        }
        return null;
    }
}
