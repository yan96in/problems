一、将字串 String 转换成整数 int
```
A. 有2个方法:
1). int i = Integer.parseInt([String]); 或 i = Integer.parseInt([String],[int radix]);
2). int i = Integer.valueOf(my_str).intValue();
PS：
字串转成 Double, Float, Long 的方法大同小异.
第一种方法：i=Integer.parseInt([String]);//直接使用静态方法，不会产生多余的对象，但会抛出异常。
第二种方法：i=Integer.valueOf(my_str).intValue();//Integer.valueOf(my_str) 相当于 new Integer(Integer.parseInt(my_str))，也会抛出异常。
```
二、将整数 int 转换成字串 String 
```
A. 有3种方法:<br>
1.) String s = String.valueOf(i);
2.) String s = Integer.toString(i);
3.) String s = "" + i;
PS：
Double, Float, Long 转成字串的方法大同小异.
第1种方法：s=String.valueOf(i); //直接使用String类的静态方法，只产生一个对象。
第3种方法：s= "" + i; //会产生两个String对象。
```
