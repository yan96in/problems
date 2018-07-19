- @Transactional注解加在service层
- [spring的proxy-target-class详解](https://blog.csdn.net/shaoweijava/article/details/76474652)
- [ajax成功请求到后台，但是前端报404错误](https://blog.csdn.net/qq_33678838/article/details/76422326)
- 自动扫描到包路径要具体，要正确<context:component-scan base-package="com/\*\*/service/impl" />,mybatis要特殊配置
 <!-- 配置扫描器 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 扫描me.gacl.dao这个包以及它的子包下的所有映射接口类 -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <property name="basePackage" value="com.gcoreinc.dao"/>
    </bean>
- todo spring-test整合junit进行单元测试
- [Spring 中的事件监听的实现](http://blog.csdn.net/blueboz/article/details/49949573)
- 路径在RequestMapping注解中定义,而不是在Controller注解!
- [spring3.2只能用java7编译](http://blog.csdn.net/blueheart20/article/details/50150529)
- [spring boot快速创建](http://start.spring.io/)
- [springMVC3.2 与 springMVC4.3配置mediaTypes的不同之处](https://blog.csdn.net/xianglingchuan/article/details/72988155)
