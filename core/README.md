#IOC Container
#说明
> 通过DI作为媒介操作，利用构造函数，工厂方法或者是配置信息创建对象实例，容器在创建Bean对象的时候进行注入  
> [Service Locator Pattern]()  
> `Bean` Spring创建的实例对象  
> `ApplicationContext` 来实例化、管理和组装`Bean`   
> `ApplicationContext` 支持注册在容器外创建的现有对象，同过`GetBeanFactory()` 获取到 `DefaultListableBeanFactory` 进行注册   
> 可以结合 `AspectJ` 在容器外创建实例  
> `Bean`的运行时类型，可以使用`AOP Proxy` 进行代理  
> 注入方式：`构造器注入` ，`Setter注入`   
> 利用`JMX MBeans` 是一种特殊的`Setter` 注入  
> `循环依赖问题`:可以使用`Setter`注入替代`构造器注入`缓解  
> 主动实例化单例对象，也可以配置成懒加载形式  
> `方法注入` Spring是通过`CGLIB` 字节码编程实现  
> 使用`Scope-Proxy`协助完成方法 注入

#源码包
- `org.springframework.beans`
- `org.springframework.context`
#组件
- BeanFactory
- ApplicationContext `(BeanFactory的扩展超集)`
- FactoryBean  
- ApplicationContextAware 
- BeanNameAware
- WebApplicationInitializer
- DispatcherServlet   
- RequestContextListener
- RequestContextFilter 
- Scope
- CustomScopeConfigurer 
- InitializingBean(不推荐使用,建议使用`@PostConstruct`)
- DisposableBean(不推荐使用,建议使用`@PreDestroy`)
- BeanPostProcess
- Lifecycle
- LifecycleProcess
- SmartLifecycle 

> 注：建议使用BeanFactory而不使用ApplicationContext进行扩展  
#Bean
- 在容器中以`BeanDefinition` 的形式存在
- `PropertyEdit`可以修改Bean的注入行为

#ApplicationContext

#BeanFactory

#FactoryBean
## ServiceLocatorFactoryBean
#Scope
> Scope包含以下几种：  
>- singleton
>- prototype
>- request
>- session
>- application
>- websocket
>- SimpleThreadScope
>#BeanPostProcess