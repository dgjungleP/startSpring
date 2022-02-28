package com.jungle.spring.mysrping.context;

import com.jungle.spring.mysrping.annotation.Component;
import com.jungle.spring.mysrping.annotation.ComponentScan;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;

/**
 * 对象容器
 */
public class ApplicationContext {
    private Class configClass;

    public ApplicationContext(Class configClass) {
        this.configClass = configClass;
        //解析配置
        handleConfig();
    }

    private void handleConfig() {
        try {
            handleComponentScan();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleComponentScan() throws ClassNotFoundException {
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
                Class<?> clazz = loader.loadClass(className);
                if (clazz.isAnnotationPresent(Component.class)) {

                }
            }
        }
    }

    public Object getBean(String beanName) {
        return null;
    }
}
