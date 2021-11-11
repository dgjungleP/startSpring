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
    - PathResource
    - ServletContextResource
    > 可以读取Servlet上的所有资源，包括`jar包内的`，`数据库的连接`等
    - InputStreamResource
    > 只能读取一次，会强制占有文件句柄，尽量避免使用这种类型
    - ByteArrayResource
- ResourceLoader
- ResourcePatternResolver
- ResourceLoaderAware
- PropertyEditor 