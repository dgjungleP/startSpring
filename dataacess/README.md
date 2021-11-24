#Data Access

##Transaction Management
###说明
> 提供了`声明式事务`和`程序化事务`   
> `事务的策略`是Spring事务的核心解决方案  
> `TransactionStatus`与当前执行的线程有关  
> 使用事务的时候需要定义正确的`TransactionManager`   
> `声明式事务` 是基于`AOP`实现的   
> 可以自定义事务回滚的动作，也可以增加任意得通知机制   
> 不支持事务跨远程系统传播  
> 在默认配置的情况下事务仅当在运行时捕获到`不受约束`的异常或者`错误`是时进行回滚   
> 可以在指定异常发生时，进行回滚，也可以设置在发生指定异常时不回滚   
> 声明式事务不会影响到祖先类，但是可以影响到子类，如果需要影响到祖先类需要重新进行声明  
> 在默认情况下`@Transactional`只对public修饰的方法生效   
> 由于默认情况下，使用的是代理模式，所以自调用会导致有些情况下的事务失效  
> 如果希望自调用也可以起作用，可以使用`AspectJ mode`   
> 物理事务和逻辑事务，以及事务的传播性
###组件
- TransactionTemplate 
- TransactionalOperator
- TransactionManager
    - PlatformTransactionManager 
    - ReactiveTransactionManager
    - JtaTransactionManager
    - DataSourceTransactionManager 
    - HibernateTransactionManager 
- TransactionException 
- TransactionStatus 
- TransactionDefinition 
- JdbcTemplate
- DataSourceUtils 
- EntityManagerFactoryUtils 
- TransactionAwareDataSourceProxy
- TransactionInterceptor
- ReactiveAdapterRegistry 
- TransactionAttributeSource 
- AnnotationTransactionAspect
- DataSourceTransactionManager

###TransactionDefinition的特质
- Propagation
- Isolation
- Timeout
- Read-only status
###注解
- @Transactional
- @EnableTransactionManagement
- @EventListener
- @TransactionalEventListener
###声明式事务得实现
> 基于AOP和元数据配置   
> `@Transactional`基于`PlatformTransactionManager`提供得线程绑定，不会将事务传播到新起得线程中   
> `ReactiveTransactionManager`管理是基于`Reactor上下文`而不是线程
###事务方法调用图
![事务调用](./img/tx.png)
###Transaction配置说明
[@Transactional配置说明](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#transaction-declarative-attransactional-settings)

##DAO Support
###组件
- SessionFactoryUtils
###注解
- @Repository
- @PersistenceContext