- 连接oracle数据库出现oracle ORA-12526: TNS: 监听程序: 所有适用例程都处于受限模式

      解决办法：使用系统管理员身份运行以下一段代码
      ALTER SYSTEM DISABLE RESTRICTED SESSION;

- [sql递归查询](http://www.cnblogs.com/linjiqin/archive/2013/06/24/3152674.html)<br>

      select * from table start with {conditon} connect by pid=prior id
      condition是一个条件,如id='xxx',不是值'xxx'
- sql查询某节点的同级节点<br>

      with tmp as(
            select a.*, level leaf        
            from tb_menu a                
            start with a.parent is null     
            connect by a.parent = prior a.id)
      select *                               
      from tmp                             
      where leaf = (select leaf from tmp where id = 50); 
      
- copy specified records from a table to another table already exists|复制一个表中的数据到另一个已创建的表中:
- insert into table 1 (column1,column2,column3,..) select col1,col2,col3,.. from table2 
- 视图中包含集合操作,distinct等语句时不能进行数据更新操作

      可以在视图上加个字段,表明数据是从那个基础表里取出来的,然后根据这个去直接操作基础表达到想要的功能.
- 触发器语法:

      CREATE [OR REPLACE] TRIGGER trigger_name
      {BEFORE | AFTER }
      {INSERT | DELETE | UPDATE [OF column [, column …]]}
      [OR {INSERT | DELETE | UPDATE [OF column [, column …]]}...]
      ON [schema.]table_name | [schema.]view_name 
      [REFERENCING {OLD [AS] old | NEW [AS] new| PARENT as parent}]
      [FOR EACH ROW ]
      [WHEN condition]
      PL/SQL_BLOCK | CALL procedure_name;
- DML触发器的限制:

      1.CREATE TRIGGER语句文本的字符长度不能超过32KB；
      2.触发器体内的SELECT 语句只能为SELECT … INTO …结构，或者为定义游标所使用的SELECT 语句。
      3.触发器中不能使用数据库事务控制语句 COMMIT; ROLLBACK, SVAEPOINT 语句；
      4.由触发器所调用的过程或函数也不能使用数据库事务控制语句；
      5.触发器中不能使用LONG, LONG RAW 类型；
      6.触发器内可以参照LOB 类型列的列值，但不能通过 :NEW 修改LOB列中的数据；
- [ Oralce导入数据时提示ORA-12899错误value too large for column](http://blog.csdn.net/lyb3290/article/details/53758884) 

      从生产导出数据到测试,出现如下错误
      ORA-02374: conversion error loading table "SWTONLINE"."TBL_TRANS_LOG_HISTORY_B"
       ORA-12899: value too large for column MER_ADDR_NAME (actual: 54, maximum: 40)
      初步估计为字符集差异导致.中文在UTF-8里占3个字节，ZHS16GBK里占2个字节
      查看测试库字符集
       代码如下 复制代码
      sys@PROD>select * from V$NLS_PARAMETERS
         2  ;
      PARAMETER                                                        VALUE
       ---------------------------------------------------------------- ----------------------------------------
       NLS_LANGUAGE                                                     SIMPLIFIED CHINESE
       NLS_TERRITORY                                                    CHINA
       NLS_CURRENCY                                                     ￥
      NLS_ISO_CURRENCY                                                 CHINA
       NLS_NUMERIC_CHARACTERS                                           .,
       NLS_CALENDAR                                                     GREGORIAN
       NLS_DATE_FORMAT                                                  DD-MON-RR
       NLS_DATE_LANGUAGE                                                SIMPLIFIED CHINESE
       NLS_CHARACTERSET                                                 AL32UTF8
       NLS_SORT                                                         BINARY
       NLS_TIME_FORMAT                                                  HH.MI.SSXFF AM
       NLS_TIMESTAMP_FORMAT                                             DD-MON-RR HH.MI.SSXFF AM
       NLS_TIME_TZ_FORMAT                                               HH.MI.SSXFF AM TZR
       NLS_TIMESTAMP_TZ_FORMAT                                          DD-MON-RR HH.MI.SSXFF AM TZR
       NLS_DUAL_CURRENCY                                                ￥
      NLS_NCHAR_CHARACTERSET                                           AL16UTF16
       NLS_COMP                                                         BINARY
       NLS_LENGTH_SEMANTICS                                             BYTE
       NLS_NCHAR_CONV_EXCP                                              FALSE
      19 rows selected.
      Elapsed: 00:00:00.00

      查看生产库字符集
       代码如下 复制代码
      sys@ORADB>select * from V$NLS_PARAMETERS;
      PARAMETER                                                        VALUE
       ---------------------------------------------------------------- ----------------------------------------
       NLS_LANGUAGE                                                     AMERICAN
       NLS_TERRITORY                                                    AMERICA
       NLS_CURRENCY                                                     $
       NLS_ISO_CURRENCY                                                 AMERICA
       NLS_NUMERIC_CHARACTERS                                           .,
       NLS_CALENDAR                                                     GREGORIAN
       NLS_DATE_FORMAT                                                  DD-MON-RR
       NLS_DATE_LANGUAGE                                                AMERICAN
       NLS_CHARACTERSET                                                 ZHS16GBK
       NLS_SORT                               (www.111cn.net)                          BINARY
       NLS_TIME_FORMAT                                                  HH.MI.SSXFF AM
       NLS_TIMESTAMP_FORMAT                                             DD-MON-RR HH.MI.SSXFF AM
       NLS_TIME_TZ_FORMAT                                               HH.MI.SSXFF AM TZR
       NLS_TIMESTAMP_TZ_FORMAT                                          DD-MON-RR HH.MI.SSXFF AM TZR
       NLS_DUAL_CURRENCY                                                $
       NLS_NCHAR_CHARACTERSET                                           AL16UTF16
       NLS_COMP                                                         BINARY
       NLS_LENGTH_SEMANTICS                                             BYTE
       NLS_NCHAR_CONV_EXCP                                              FALSE
      19 rows selected.
      Elapsed: 00:00:00.00

      更改测试库的字符集和生产库一样
       代码如下 复制代码
      sys@PROD>SHUTDOWN IMMEDIATE;
       Database closed.
       Database dismounted.
       ORACLE instance shut down.
       sys@PROD>STARTUP MOUNT
       ORACLE instance started.
      Total System Global Area  939495424 bytes
       Fixed Size                  2233960 bytes
       Variable Size             671091096 bytes
       Database Buffers          260046848 bytes
       Redo Buffers                6123520 bytes
       Database mounted.
       sys@PROD>ALTER SYSTEM ENABLE RESTRICTED SESSION;
      System altered.
      Elapsed: 00:00:02.19
       sys@PROD>ALTER SYSTEM SET JOB_QUEUE_PROCESSES=0;
      System altered.
      Elapsed: 00:00:00.02
       sys@PROD>ALTER SYSTEM SET AQ_TM_PROCESSES=0;
      System altered.
      Elapsed: 00:00:00.01
       sys@PROD>ALTER DATABASE OPEN;
      Database altered.
      Elapsed: 00:00:05.09
       sys@PROD>ALTER DATABASE ﻿CHARACTER SET ZHS16GBK ;
       ALTER DATABASE ﻿CHARACTER SET ZHS16GBK
                      *
       ERROR at line 1:
       ORA-00911: 无效字符

      Elapsed: 00:00:00.00

      ---报字符集不兼容，此时用INTERNAL_USE指令不对字符集超集进行检查
       代码如下 复制代码
      sys@PROD>ALTER DATABASE CHARACTER SET INTERNAL_USE ZHS16GBK;

       Database altered.
      Elapsed: 00:00:53.35

      重新启动数据库,再次导入,即可成功.
       代码如下 复制代码
      [oracle@idata ~]$  impdp  '/ as sysdba ' directory=backup dumpfile=cil_20130702.dmp logfile=cil_20130703.log table_exists_action=replace schemas=EPAYMENT,LOGSDB,PREPAID,RISK,SWTSETTLE,SWTONLINE

      补充一下oracle字符编码问题
      通常会查询NLS_CHARACTERSET（数据库字符集），NLS_NCHAR_CHARACTERSET（国家字符集），应该要存储多种语言，需要字符集为UTF-8。

       代码如下 复制代码 
      SELECT * FROM V$NLS_PARAMETERS WHERE PARAMETER IN ('NLS_CHARACTERSET', 'NLS_NCHAR_CHARACTERSET');


      结果UTF-8，OK。

      查看没问题的DB里 FieldA varchar2(10 char)

      查看有问题的DB里 FieldA varchar2(10)

      （没出现问题之前还真没注意到这两种定义是有区别的。。。）

      UTF-8里一个中文字符是3 bytes，从上面的定义可以看出来，如果char/byte 定义导致的可存储数据长度相差很大了。

      设置参数NLS_LENGTH_SEMANTICS可以在create table时对CHAR 或者VARCHAR2列指定使用字节（byte）或者字符（character）来定义长度。

      NCHAR, NVARCHAR2, CLOB, and NCLOB 列都是基于字符（character）的。

      NLS_LENGTH_SEMANTICS不会影响到SYS和SYSTEM用户表，数据字典定义都使用字节（byte）。

      可以在定义列时候显示指定使用字节（byte）或者字符（character）来定义长度：

      CHAR(10 BYTE)  - 无论NLS_LENGTH_SEMANTICS设置成什么，都使用字节（byte）。

      CHAR(10 CHAR) - 无论NLS_LENGTH_SEMANTICS设置成什么，都使用字符（char）。`
