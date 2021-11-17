#Data Access

##Transaction Management
###说明
> 提供了`声明式事务`和`程序化事务`   
> `事务的策略`是Spring事务的核心解决方案  
> `TransactionStatus`与当前执行的线程有关  
> 使用事务的时候需要定义正确的`TransactionManager` 
###组件
- TransactionManager
    - PlatformTransactionManager 
    - ReactiveTransactionManager
    - JtaTransactionManager
- TransactionException 
- TransactionStatus 
- TransactionDefinition 

###TransactionDefinition的特质
- Propagation
- Isolation
- Timeout
- Read-only status