#Aspect Oriented Programming with Spring
> Spring AOP目前只支持在`方法`上进行编织（通知Spring Bean 上执行的方法），并没有实现`字段`拦截  
> 如果需要使用字段拦截，建议使用`AspectJ`这种语言   
> 无法很好的支持细粒度的AOP场景  
> AOP默认使用`JDK的动态代理实现`，也可以使用`CGLIB动态`代理实现   
> Spring AOP的本质就是基于`代理模式`  
> 如果需要切入构造方法，则需要使用`Spring-driven native AspectJ weaving`来替代   
> AspectJ会自己在编译时，处理切入点的匹配性能优化  
> 在编写时，尽量保证切入点的搜索定义范围足够小（类，范围，上下文），在使用时至少能够包阔（类，范围）类型   
> 切入点范围描述的越明确，运行时的性能越高  
> 可以在带有返回值的注解中使用`returning`声明返回值对象、使用`trowing`声明异常对象，并在方法参数列表中使用  
> 可以使用`args`声明和切入点方法什么方法入参对象   
> 可以根据泛型类型截取到对应的实现方法参数，但是不能直接截取到泛型集合类型的方法参数，可以使用`<?>`全限定定义或者手动判断   
> 可以使用argNames注解属性，指定参数的限定名   
> 使用`Aspect`实例化模型,目前spring支持`perthis`、`pertarget` (多实例的情况)   
> 支持混合使用`XML`和`注解`形式   
> 在代理模式中，自调用会导致被调用的方法的代理没有办法正常运行，所以在代理模式下尽量不适用自调用  
> 纯粹的`AspectJ`没有自调用问题  
> 注释不会通过接口传递  
> `spring-instrument.jar` Agent
##组件
- JoinPoint
    - ProceedingJoinPoint
- AspectJProxyFactory 
- AnnotationBeanConfigurerAspect 
    - AbstractBeanConfigurerAspect 
- AnnotationTransactionAspect
    - AbstractTransactionAspect 
- JtaAnnotationTransactionAspect 
- InstrumentationSavingAgent
- LoadTimeWeaver 
    - DefaultContextLoadTimeWeaver 
    - TomcatLoadTimeWeaver
    - GlassFishLoadTimeWeaver
    - JBossLoadTimeWeaver
    - WebSphereLoadTimeWeaver
    - WebLogicLoadTimeWeaver
    - InstrumentationLoadTimeWeaver
    - ReflectiveLoadTimeWeaver
- AspectJWeavingEnabler
- LoadTimeWeavingConfigurer 
- ClassFileTransformers
    - ClassPreProcessorAgentAdapter 
##概念
- Aspect 
- Join Point
- Advice
- Pointcut
- Introduction
- Target Object
- AOP Proxy
- Weaving
##适用范围
- 声明式服务
- 自定义切面
##Pointcut 申明方式
> 切点的方法生命返回值必须为void    
> 指示器：
> - excution
> - within
> - this
> - target
> - args
> - @target
> - @args
> - @within
> - @annotation
> - bean
> - reference pointcut
##消息类型
- Before
- After Return
- After Throw
- After
- Around
##注解
- @EnableAspectJAutoProxy
- @AspectJ 
- @Pointcut
- @Before
- @AfterReturning
- @AfterThrowing
- @After
- @Around
- @DeclareParents
- @Configurable
- @EnableSpringConfigured 
- @Transactional
- @EnableLoadTimeWeaving
##Example
~~~bash
    execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) 
    throws-pattern?)
~~~
