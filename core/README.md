#IOC Container
#说明
> 通过DI作为媒介操作，利用构造函数，工厂方法或者是配置信息创建对象实例，容器在创建Bean对象的时候进行注入  
> [Service Locator Pattern]()  
> `Bean` Spring创建的实例对象  
> `ApplicationContext` 来实例化、管理和组装`Bean`   
> `ApplicationContext` 支持注册在容器外创建的现有对象，同过`GetBeanFactory()` 获取到 `DefaultListableBeanFactory` 进行注册   
> 可以结合 `AspectJ` 在容器外创建实例
#源码包
- `org.springframework.beans`
- `org.springframework.context`
#核心组件
- BeanFactory
- ApplicationContext `(BeanFactory的扩展超集)`
- FactoryBean
> 注：建议使用BeanFactory而不使用ApplicationContext进行扩展  
#Bean
- 在容器中以`BeanDefinition` 的形式存在
