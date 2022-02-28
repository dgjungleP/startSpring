package com.jungle.spring.mysrping.context;

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
        handleComponentScan();
    }

    private void handleComponentScan() {
    }

    public Object getBean(String beanName) {
        return null;
    }
}
