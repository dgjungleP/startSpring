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
###组件
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
###TransactionDefinition的特质
- Propagation
- Isolation
- Timeout
- Read-only status
###注解
- @Transactional
- @EnableTransactionManagement
###声明式事务得实现
> 基于AOP和元数据配置   
> `@Transactional`基于`PlatformTransactionManager`提供得线程绑定，不会将事务传播到新起得线程中   
> `ReactiveTransactionManager`管理是基于`Reactor上下文`而不是线程
###事务方法调用图
![事务调用](./img/tx.png)
