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
> 可以在带有返回值的注解中使用`returning`声明返回值对象，并在方法参数列表中使用
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
##Example
~~~bash
    execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) 
    throws-pattern?)
~~~
