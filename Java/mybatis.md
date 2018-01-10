- IncompleteElementException: Could not find result map ...

        出现这个错误,大概率是应该写resultType的,你写成了resultMap,orz
- [mybatis动态sql中trim, where, set的用法](http://www.mybatis.org/mybatis-3/zh/dynamic-sql.html)
- mybatis是否可以直接传sql语句执行?可以
- mybatis批量操作
- 出现错误Mapped Statements collection does not contain value for ...的原因,(迁移重构代码时容易出现这个错误)

        1、mapper.xml中没有加入namespace 
        2、mapper.xml中的方法和接口mapper的方法不对应 
        3、mapper.xml没有加入到mybatis-config.xml中(即总的配置文件)，例外：配置了mapper文件的包路径的除外 
        4、mapper.xml文件名和所写的mapper名称不相同。2018/01/10
