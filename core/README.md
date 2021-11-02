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
> `XML注入`的优先级高于`注解注入的`因为`注解注入`比`XML注入`更先执行  
> 确定注入对象的过程：
>  - 查询所有直接相关联的Bean对象
>  - 匹配任何符合默认的自动配置原则的Bean对象
>  - 使用了`@Qualifier`和再`CustomAutowireConfigurer`中配置了的Bean对象
>  - 使用了`@Primary`注解确定了的对象   
>
> 可以通过：1.注解 2.AspectJ表达式 3.自定义筛选条件，将对象注入到Bean中  

#源码包
- `org.springframework.beans`
- `org.springframework.context`
#组件
- BeanFactory
- ApplicationContext `(BeanFactory的扩展超集)`
- FactoryBean  
- Aware
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
- CustomAutowireConfigurer
> 可以注册自定已的`qualifier`注解
- AutowireCandidateResolver 
- ConversionService 
>控制`@Value`的类型转化
- InjectionPoint 
> 获取当前Bean的请求注入点，可以放在工厂方法中  
> 在原型类型的中使用较多
- DependencyDescriptor
- BeanNameGenerator 
    - FullyQualifiedAnnotationBeanNameGenerator  


#Bean
- 在容器中以`BeanDefinition` 的形式存在
- `PropertyEdit`可以修改Bean的注入行为

#ApplicationContext

#BeanFactory
> 注：建议使用BeanFactory而不使用ApplicationContext进行扩展  
- SimpleJndiBeanFactory
##ServiceLocatorFactoryBean
#Scope
> Scope包含以下几种：  
>- singleton
>- prototype
>- request
>- session
>- application
>- websocket
>- SimpleThreadScope
#Extension Points
##BeanPostProcess
> 在类实例化过程中后对IOC中的对象进行操作  
> `ApplicationContext`可以自动识别并注入，还是推荐使用`ConfigurableBeanFactory`来进行注入  
> 在程序启动时即开始实例化  
> 部分`AOP auto-proxy`也是`BeanPostProcess`的实例，所以无法作用到其身上
- AutowiredAnnotationBeanPostProcessor
- ConfigurationClassPostProcessor
- CommonAnnotationBeanPostProcessor
- PersistenceAnnotationBeanPostProcessor
- EventListenerMethodProcessor
- RequiredAnnotationBeanPostProcessor
- CommonAnnotationBeanPostProcessor 
> 用以支持JSR-250中所描述的一些注解
##BeanFactoryPostProcessor
> 在类实例化之前对IOC中的对象的Definition进行操作  
> 修改类实例的源信息
###PropertySourcesPlaceholderConfigurer 
> 支持读取配置文件包括：  
>- 指定文件  
>- Spring Environment参数  
>- Java System参数    
> 如果使用这个配置来管理，那么`@bean`注入的时候必须是`static`模式  
###PropertyOverrideConfigurer
##FactoryBean
#Annotation
- @Required (在5.1版本中被弃用了)
- @Autowired
> 可以接受数据类型的数组和散列  
> 如果只有一个构造函数，那么一定会被调用   
> 如果有多个构造函数需要参与注入，那么有优先选择参数多的那个，并且需要将`required`设置为false  
> 构造函数不一定为public   
> 可以使用Java8中的`optinal`来保证不强制注入  
> 也可以使用`@Nullable`来设置不强制注入
> 需要调用相同实例身上的事务的时候，可以使用自己注入自己来实现
- @Resource
> 通过名称注入  
> 如果没有指定名称，则使用派生字段名或者setter方法注入
> 在没有明确的指定的情况下，会根据类型进行注入
- @Value 
> 如果不能解析属性，则会将属性名注入
> 可以自动进行拆箱装箱  
> 可以将以`,`隔开的数据，解析成字符串数组  
> 支持以`:`来指定默认值  
> 支持`SpEL`表达式
- @Inject
- @Nullable
- @Primary
> 优先注入
- @Qualifier
> 按配置注入，解决有多个Bean对象满足条件的情况
> 使用泛型也可以达到隔离的效果
- @PostConstruct
- @PreDestroy
- @Configuration
- @Bean
> `@Bean`在`@Configuration`中使用的时候会使用`CGLIB`进行增强，而在`@Component`中没有使用`CGLIB`来拦截和增强方法和属性  
> 可以使用static的来修饰方法，以达到提前注入的目的  
> 容器不能拦截static修饰的工厂方法，因为`CGLIB`只能重写非static的方法  
> Java定义的可见性对于`@Bean`修饰的方法没有作用  
> 在`@Configuration`中使用的时候不能修饰为`private`和`final`  
> 如果有多个工厂方法修饰同一个Bean对象，那么将采用`贪婪`的方式去自动选择工厂方法
- @Import
- @DependsOn 
- @Component
    - @Service
    - @Controller
    - @Repository 
    >支持持久层异常自动翻译
- @RestController
- @ResponseBody 
- @SessionScope   
> 将作用域编程会话模式      
- @Transactional
- @Documented 
- @AliasFor      
- @ComponentScan          
- @Lazy  
> 可以在对象注入的使用使用，也可以在工厂方法中使用   
- @Scope                                  
                                                               