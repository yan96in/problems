- int型数据不能与null进行比较
- JMX:Java Management Extensions，即Java管理扩展
- split用法小结 public String[] split(String regex)
```
1、使用String.split方法分隔字符串时，分隔符如果用到一些特殊字符，可能会得不到我们预期的结果。
如用“.”作为分隔的话，必须是如下写法：String.split("\\."),这样才能正确的分隔开，不能用String.split(".");
再比如如用“|”作为分隔的话，必须是如下写法：String.split("\\|"),这样才能正确的分隔开，不能用String.split("|");
还有“*”和“+”等都是转义字符，必须得加"\";
2、如果在一个字符串中有多个分隔符，可以用“|”作为连字符，比如：“acount=? and uu =? or n=?”,把三个都分隔出来，可以用String.split("and|or");
```
- [java转换doc为pdf](http://feifei.im/archives/93)
  文中说的dll放在java.library.path中任意路径下是错的,必须是jdk/jre/bin文件夹下
- [Java实现将文件或者文件夹压缩成zip](https://www.cnblogs.com/zeng1994/p/7862288.html)
