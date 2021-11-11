#Resource
##组件
- Resource
    - WriteableResource
    - UrlResource
    > 可支持的前缀 `file:`,`https:`,`ftp:`等  
    总是支持所有类型
    - ClassPathResource
    > 可支持的前缀 `classpath:`
    - FileSystemResource
    > 强制所有的路径都为相对路径，不管他是否以`/`开头
    - PathResource
    - ServletContextResource
    > 可以读取Servlet上的所有资源，包括`jar包内的`，`数据库的连接`等
    - InputStreamResource
    > 只能读取一次，会强制占有文件句柄，尽量避免使用这种类型
    - ByteArrayResource
- ResourceLoader
> 所有的ApplicationContext都实现了该接口  
> 在不指定特殊前缀的情况下，`getResource()`方法会根据具体的`context`产生对应的`resource`  
- ResourcePatternResolver
    - PathMatchingResourcePatternResolver
        - PathMatcher 
> 特殊的前缀`classpath*:` 匹配所有类路径中的资源  
- ResourceLoaderAware
- PropertyEditor 
    - ResourceArrayPropertyEditor 