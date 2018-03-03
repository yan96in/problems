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
