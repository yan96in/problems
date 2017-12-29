- copy specified records from a table to another table already exists|复制一个表中的数据到另一个已创建的表中:
-insert into table 1 (column1,column2,column3,..) select col1,col2,col3,.. from table2 
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
l         CREATE TRIGGER语句文本的字符长度不能超过32KB；

l         触发器体内的SELECT 语句只能为SELECT … INTO …结构，或者为定义游标所使用的SELECT 语句。

l         触发器中不能使用数据库事务控制语句 COMMIT; ROLLBACK, SVAEPOINT 语句；

l         由触发器所调用的过程或函数也不能使用数据库事务控制语句；

l         触发器中不能使用LONG, LONG RAW 类型；

l         触发器内可以参照LOB 类型列的列值，但不能通过 :NEW 修改LOB列中的数据；
