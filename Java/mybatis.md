- [MyBatis一次执行多条SQL语句](https://www.cnblogs.com/qiumingcheng/p/5912349.html),oracle只需在mapper文件sql语句的首末加上begin end即可.
- [关于Oracle的NVARCHAR2在mybatis-generator中的使用](https://blog.csdn.net/u014418725/article/details/79526262)
- generantor配置中的targetProject可以是绝对路径或相对路径,写相对路径时eclipse是项目名开头,idea直接src开头;
- [解决运行mybatis-generator生成java.sql.SQLException: Cannot connect to database (possibly bad driver/URL combination)异常](http://www.xwood.net/_site_domain_/_root/5870/5874/t_c268747.html),注意:jdbc:后面没有//,跟yml里面的配置有区别
- [如何在idea中使用Mybatis-generator插件快速生成代码](https://www.cnblogs.com/yuanmiemie/p/6736347.html)
- [找不到org.springframework.dao.support.DaoSupport的类文件](https://blog.csdn.net/jacke121/article/details/71105421)
Spring tx：为JDBC、Hibernate、JDO、JPA等提供的一致的声明式和编程式事务管理。
- Mybatis mapper文件中的sql结尾不要写";",否则会报错 (oracles数据库:"ORA-00911: 无效字符")
- IncompleteElementException: Could not find result map ...

        出现这个错误,大概率是应该写resultType的,你写成了resultMap,orz
- [mybatis动态sql中trim, where, set的用法](http://www.mybatis.org/mybatis-3/zh/dynamic-sql.html)
- mybatis是否可以直接传sql语句执行?可以
- mybatis批量操作
- 出现错误Mapped Statements collection does not contain value for ...的原因(迁移重构代码时容易出现这个错误):
```
        1.mapper.xml中没有加入namespace 
        2.mapper.xml中的方法和接口mapper的方法不对应 
        3.mapper.xml没有加入到mybatis-config.xml中(即总的配置文件)，例外：配置了mapper文件的包路径的除外 
        4.mapper.xml文件名和所写的mapper名称不相同。2018/01/10
```
- 出现错误Parameter * not found. Available parameters are "]"1, 0, param1, param2]"的解决:
```
	当只传一个参数到sql语句时，可以直接写参数名，当传多个参数时，应当这样写(多个参数已#{0}开始)
```
