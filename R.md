- 在学习用Jupyter中使用R的过程中，需要：
    1.安装anaconda
    2.R中安装
      1)installl.package('devtools');
      2)library(devtools)
      3)devtools::install_github('IRkernel/IRkernel')
      4)确保juypter在系统路径中Sys.getenv('PATH')
      5)如果juypter不在路径中，使用命令Sys.setenv(PATH="...:/anaconda3/bin")将其加入（假定是在mac上安装）
      6)IRkernel::installspec(user = FALSE) (user = FALSE表示任何用户都可以使用）
- 查看R自带包search(),searchpaths()
- 卸载包remove.packages("xxx")或者detach(package="xxx",unload=TRUE)
- 查看函数案例example("xxx")
- 查看函数源代码（未封装函数直接输入函数名，已封装函数先methods("xxx"),然后xxx.default,带星号的函数getAnywhere("xxx")
- 错误
    - 导入csv文件时出现attempt to select less than one element in get1index错误
    - 导入xls文件时出现odbcConnectExcel is only usable with 32-bit Windows错误
