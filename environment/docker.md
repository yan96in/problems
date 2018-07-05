
- docker镜像centos的yum源设置步骤:
  1.先退出centos镜像;
  2.docker cp /etc/yum.repo.d/CentOS-... /etc/yum.repo.d;
  3.docker restart 镜像;
  4.docker exec -ti 镜像 /bin/bash;
  5.完成.
