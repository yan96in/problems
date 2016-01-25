# how-to-solve-these-problems
开发中遇到问题的解决方法总结
#Android
##开发框架

快速开发xUtils|注解ButterKnife|图片处理Glide/Fresco|网络volley/retrofit+okhttp|数据库greenDAO|OrmLite|json数据处理转换fastjson+GsonFormat|事件总线EventBus|RxJava|检测修复内存泄露 LeakCanary|语言kotlin|布局androidautolayout|推送 umeng|bug收集bugly

##UI
[1获取屏幕宽高/2设置popwin的位置](https://github.com/yan96in/problems/blob/master/android/getwindow-setpopwin.md)<br>
[按两次back键退出的2种方法](https://github.com/yan96in/problems/blob/master/android/back.java)<br>
[editText限制输入的4种方法](https://github.com/yan96in/problems/blob/master/android/edittext.java)<br>
[输入框一键删除内容](https://github.com/yan96in/problems/blob/master/android/edittextWithDelete.java)<br>
[倒计时功能](https://github.com/yan96in/problems/blob/master/android/countdown-timer.md)<br>
[加载图片时导致的内存溢出问题](https://github.com/yan96in/problems/blob/master/android/load-image-oom.md)<br>
[截屏](https://github.com/yan96in/problems/blob/master/android/screen-shot.md)<br>
[根据有无虚拟（back home）按键时正确显示界面](https://github.com/yan96in/problems/blob/master/android/menu-key)<br>
[全屏模式下半透明显示系统状态栏](https://github.com/yan96in/problems/blob/master/android/fullscreen-with-statusbar.md)<br>
[ListView中getChildAt(index)的使用注意事项](https://github.com/yan96in/problems/blob/master/android/getChildAt.md)<br>
[获取LayoutInflater的三种方法](https://github.com/yan96in/problems/blob/master/android/getLayoutInflater.md)
[改变背景染色](https://github.com/yan96in/problems/wiki/%E6%94%B9%E5%8F%98(%E8%AE%BE%E7%BD%AE)%E6%8E%A7%E4%BB%B6%E7%9A%84%E8%83%8C%E6%99%AFTint%E8%89%B2)<br>
[打开本地图片选择器并更换头像](https://github.com/yan96in/problems/blob/master/android/change-avatar.md)<br>
[ExpandableListView在右边显示箭头的方法](https://github.com/yan96in/problems/blob/master/android/expandable-right-indicator.md)<br>
[通用ViewHolder](https://github.com/yan96in/problems/blob/master/android/general-viewholder.md)<br>
##四大组件及消息传递机制
[Activity之间传值](https://github.com/yan96in/problems/blob/master/android/intent-value-transmit.md)<br>
[hanler在fragment中的使用](https://github.com/yan96in/problems/blob/master/android/handler.md)<br>
##数据存储
[web缓存机制](https://github.com/yan96in/problems/blob/master/android/web-cache)<br>
##传感器、手机硬件
[调用系统相机应用](https://github.com/yan96in/problems/blob/master/android/camera.md)<br>
##调试
[流量问题](https://github.com/yan96in/problems/blob/master/android/steam-relative.md)<br>
##
##gradle编译
[项目迁移到as上时的乱码问题] 未解决
[error: dexDebug]一般删除重复或者多余不用的jar包就可以了<br>
[plugin not found](https://github.com/yan96in/problems/blob/master/android/plugin-not-found.md)<br>
[6.0 不能使用httpclient的解决方法](https://github.com/yan96in/problems/blob/master/android/use-httpclient-under-6.md)<br>
添加module失败时不想再持有module的处理方法：settings.gradle去掉对应module<br>
#Java
[类型转换](https://github.com/yan96in/problems/blob/master/Java/type-conversion.md)<br>
[为什么匿名内部类和局部内部类只能访问final变量](https://github.com/yan96in/problems/blob/master/Java/inner-class-and-final.md)<br>
java是支持中文名的,比如
```
class 我{ 
  String 我="me";
  public String 返回我(){
    return 我;
  }
}
```
[String字符分割，中文字符排序](https://github.com/yan96in/problems/blob/master/Java/china-unicode.md)<br>
[Collections排序](https://github.com/yan96in/problems/blob/master/Java/collections-sort.md)<br>
[split用法小结](https://github.com/yan96in/problems/blob/master/Java/split.md)<br>
[enum的用法](https://github.com/yan96in/problems/blob/master/Java/enum.md)<br>
#git
1.中文乱码  未解决
#IOS/Object-c/Swift
#javascript/
