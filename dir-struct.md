# 描述了各个组件的文件目录结构, 主目录为 `/data/paas`

## repository

`/data/paas/repository`
  - `/webpackages`
  > 存放所有的war、zip，按token名字存取，token与app映射关系存DB
  
  - `/dockerfile-template/{image_type}`
  > 存放所有镜像的dockerfile Template
  
  - `/webapps/{app_id}/dockerfiles/Dockerfile`
  > 存放所有应用的Dockerfile

## agent

`/data/paas/agent`
  - `/config.json`
  > agent的配置
  
  - `/webapps/{app_id}/{instance_id}/ROOT`
  > 映射到 容器内部 `/data/webapps/paas/ROOT`

## demo

```
/data/paas
      |-- agent
      |   |-- config.json
      |   `-- webapps
      `-- repository
          |-- dockerfiles
          |   `-- tomcat-8.0
          |       `-- Dockerfile
          `-- webpackages
```
