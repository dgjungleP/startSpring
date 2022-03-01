package com.jungle.spring.mysrping.context;

import com.jungle.spring.mysrping.annotation.Autowired;
import com.jungle.spring.mysrping.annotation.Component;
import com.jungle.spring.mysrping.annotation.ComponentScan;
import com.jungle.spring.mysrping.annotation.Scope;
import com.jungle.spring.mysrping.aware.BeanNameAware;
import com.jungle.spring.mysrping.beanpostprocesser.BeanPostProcessor;
import com.jungle.spring.mysrping.config.ApplicationConfig;

import java.beans.FeatureDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 对象容器
 */
public class ApplicationContext {
    private Class<ApplicationConfig> configClass;
    private ConcurrentHashMap<String, Object> singletonObjectMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public ApplicationContext(Class<ApplicationConfig> configClass) {
        this.configClass = configClass;
        //解析配置
        handleConfig();
    }

    private void handleConfig() {
        handleComponentScan();
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition definition = beanDefinitionMap.get(beanName);
            if (definition.getScope().equals("Single")) {
                Object bean = createBean(definition, beanName);
                singletonObjectMap.put(beanName, Objects.requireNonNull(bean));
            }
        }
    }

    private Object createBean(BeanDefinition definition, String beanName) {
        Class<?> clazz = definition.getClazz();
        try {
            Object instance = clazz.getConstructor().newInstance();
            //自动注入
            List<Field> autoWiredFieldList = Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Autowired.class)).collect(Collectors.toList());
            for (Field field : autoWiredFieldList) {
                field.setAccessible(true);
                field.set(instance, getBean(field.getName()));
            }
            //aware回调
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }
            for (BeanPostProcessor processor : beanPostProcessorList) {
                instance = processor.postProcessBeforeInitialization(instance, beanName);
            }

            //初始化
            if (instance instanceof InitializeBean) {
                ((InitializeBean) instance).afterPropertiesSet();
            }

            for (BeanPostProcessor processor : beanPostProcessorList) {
                instance = processor.postProcessAfterInitialization(instance, beanName);
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("Can not create the bean");
        }
    }

    private void handleComponentScan() {
        if (!configClass.isAnnotationPresent(ComponentScan.class)) {
            return;
        }
        ComponentScan componentScan = configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScan.value();
        //扫描
        //1、 搜寻路径下的所有类
        ClassLoader loader = this.getClass().getClassLoader();
        URL resource = loader.getResource(path.replace(".", "/"));
        File file = new File(Objects.requireNonNull(resource).getFile());
        if (file.isDirectory()) {
            scanFile(loader, file);
        }
    }

    private void scanFile(ClassLoader loader, File file) {
        File[] files = file.listFiles();
        for (File f : Objects.requireNonNull(files)) {
            if (f.isDirectory()) {
                scanFile(loader, f);
            } else {
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
                        String beanName = clazz.getSimpleName();
                        beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
                        if (clazz.isAnnotationPresent(Scope.class)) {
                            Scope scope = clazz.getDeclaredAnnotation(Scope.class);
                            definition.setScope(scope.value());
                        } else {
                            definition.setScope("Single");
                        }
                        beanDefinitionMap.put(beanName, definition);
                        if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                            beanPostProcessorList.add((BeanPostProcessor) getBean(beanName));
                        }
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
            Object bean;
            if (definition.getScope().equals("Single")) {
                if (!singletonObjectMap.containsKey(beanName)) {
                    singletonObjectMap.put(beanName, createBean(definition, beanName));
                }
                bean = singletonObjectMap.get(beanName);

            } else {
                //创建bean
                bean = createBean(definition, beanName);
            }
            return bean;
        }
        throw new RuntimeException("Cant find the bean");
    }
}
