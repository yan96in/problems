- [tomcat版本号的修改](https://blog.csdn.net/xxy_ywh1221/article/details/78851391)
- [错误The method getJspApplicationContext(ServletContext) is undefined for the type JspFactory解决方式](https://blog.csdn.net/zhouzhiwengang/article/details/51220226)
- Unable to add the resource at ... to the cache for ... because there was insufficient free space available after 
evicting expired cache entries consider increasing the maximum size of the cache.

   解决方法:修改tomcat安装目录下的conf/context.xml文件,增加/修改后面内容:<Resources cachingAllowed="true" cacheMaxSize="100000" />

- 怎样做可以在启动tomcat时不显示VersionLoggerListener?

   注释掉conf/server.xml中的<Listener className="org.apache.catalina.startup.VersionLoggerListener" />
