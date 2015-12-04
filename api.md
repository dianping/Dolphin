# Paas api

## 应用
- `GET` */apps*
> 获取所有的应用
> 原: GET /v1/apps

- `GET` */apps/{app_id}*
> 获取某个的应用信息
> 原: GET /v1/apps/{app_id}/

- `POST` */apps*
> 初始化应用(添加一个应用)
> 原: POST /v1/apps/{app_id}/init

- `PUT` */apps/{app_id}*
> 修改应用
> 原: POST /v1/apps/{app_id}

- `DELETE` */apps/{app_id}*
> 删除应用
> 原: POST /v1/apps/{app_id}/delete


## 应用套餐
- `GET` */appplans*
> 获取所有的应用套餐
> 原: GET /v1/appplans

-   
>

## 应用实例
- `GET` */instances*
> 获取所有实例
> 原: GET /v1/instances

- `GET` */instances/new*
> 获取新创建的实例
> 原: GET /v1/instances/app/new_creat

- `GET` */apps/{app_id}/instances*
> 获取某个app的所有实例
> 原: GET /v1/instances/app/{app_id}

- `GET` */apps/{app_id}/instances?status=(deploy_fail)*
> 获取某个app某个状态的的实例
> 原: GET /v1/instances/upgrade_fail

- `GET` */instances/{instance_id}*
> 获取单个实例
> 原: GET /v1/instances/{instance_id}

- `DELETE` */apps/{app_id}/instances*
> 删除某个app的所有实例
> 原: POST /v1/instances/app/{app_id}/delete

- `POST` */apps/{app_id}/instances/shutdowns*
> 关闭某个app的所有实例
> 原: POST /v1/instances/app/{app_id}/shutdowns

- `POST` */instances/shutdowns?(instance_ip=x.x.x.x)*
> 根据某个条件关闭实例
> 原: GET /v1/instances/{instance_ip}/shutdowns

- `POST` */apps/{app_id}/instances/startups*
> 启动某个app的所有实例
> 原: POST /v1/instances/app/{app_id}/start

- `POST` */instances/startups?(instance_ip=x.x.x.x)*
> 根据条件启动实例
> 原: GET /v1/instances/{instance_ip}/start

- `POST` */apps/{app_id}/instances/restarts*
> 重启某个app的所有实例
> 原: POST /v1/instances/app/{app_id}/restart

- `POST` */instances/restarts?(instance_ip=x.x.x.x)*
> 根据条件重启实例
> 原: GET /v1/instances/{instance_ip}/restart

- `POST` */apps/{app_id}/instances/scales(?count=2)*
> 将某个应用扩(缩)容, 版本为当前正在运行的版本
> 原: POST /v1/apps/{app_id}/scale

- `POST` */apps/{app_id}/instances?(app_version=v1&count=2)*
> 创建某个应用的实例并部署
> 原: POST /v1/apps/{app_id}/{app_version}/create


## 应用分组
- `GET` */apps/{app_id}/groups*
> 获取某个应用的分组
> 原: GET /v1/apps/{app_id}/groups

- `PUT` */apps/{app_id}/groups?app_version=v1*
> 升级某个应用的所有分组到某个版本
> 原: POST /v1/apps/{app_id}/{app_version}/upgradeAll

- `PUT` */apps/{app_id}/groups/{group_id}?app_version=v1*
> 升级某个应用的某个分组到某个版本
> 原: POST /v1/apps/{app_id}/{app_version}/{deploy_id}/{group_id}/upgrade

- `POST` */groups/{group_id}/transfers?instances=1,2,3*
> 将某个实例移到某个的分组
> 原: POST /v1/instancegroup/move

- `PUT` */groups/{group_id}*
> 修改分组的信息
> 原: POST /v1/instancegroup/update

- `POST` */groups*
> 增加分组的信息
> 原: POST /v1/instancegroup/add

- `DELETE` */groups/{group_id}*
> 删除某个分组
> 原: POST /v1/instancegroup/{group_id}/delete


## 应用仓库
- `POST` */apps/{app_id}/repos?app_version=v1*
> 为应用新建一个仓库,目前主要是 web package
> 原: /v1/apps/{app_id}/{app_version}/{md5}/allocate 

-   
>

## Agent
- `GET` */agents*
> 获取所有的agent信息
> 原: GET /v1/machines

- `GET` */agents?status=(error)*
> 获取某个状态的机器
> 原: GET /v1/machines/listFaults 获取故障机器列表 

- `PUT` */agents?field=(hostname)*
> 调整主机名
> 原: /v1/machines/hostname 调整所有物理机的索引

- `GET` */agents/{agent_id}*
> 获取某个agent的信息
> 原: GET /v1/machines/{machine_id}

- `POST` */agents/{agent_id}/shutdowns*
> 关闭某个机器
> 原: POST /v1/machines/{machine_id}/shutdown

- `POST` */agents/{agent_id}/startups*
> 启动某个机器
> 原: POST /v1/machines/{machine_id}/startup


## 网络
- `GET` */networks*
> 查看当前所有的网络信息
> 原: GET /v1/networks 获取所有的网络信息

- `GET` */networks/{network_id}*
> 查看某个网络信息
> 原: GET /v1/networks/{networkId} 获取某个网络信息

## 日志
- `POST` */userlogs*
> 添加一条用户日志
> 原: POST /v1/operationlog/add 添加一条用户操作日志

-   
>

## 操作
- `GET` */operations?status=(undone)*
> 获取某个状态的操作, 如未完成的操作
> 原: GET /v1/operations/undone 获取所有的未完成的操作

- `GET` */operations/{operation_id}*
> 获取某个操作的详情
> 原: GET /v1/operations/{operation_id} 获取操作详情


# 忽略的旧api
- POST /v1/apps/{app_id}/{deploy_id}/{git_url}/{kernel_version}/upgradeKernel 升级某个应用的Phoenix内核
- POST /v1/cmdb/add_instance 添加实例到cmdb
- POST /v1/cmdb/delete_instance 删除cmdb上的实例
- POST /v1/cmdb/update_instance 更新实例的主机名
- GET /v1/instancegroup/{app_id} 获取某个应用的逻辑分组 *重复了*
- POST /v1/instances/instanceIndex 迁移所有实例的索引，按从小到大排列
- GET /v1/machines/static 获取所有机器静态信息
- GET /v1/machines/{machine_ip}/status 获取某个机器动态信息
- POST /v1/machines/{machine_id}/shutdownWithFault 将这故障机器上的实例恢复到其他机器
- GET /v1/metadata/{app_id}/init [用于用于从kvm到paas的迁移]初始化某个应用，不传war包
- GET /v1/operationlog 获取所有的用户操作日志
- GET /v1/operations 获取所有的操作
- GET /v1/apps/{app_id}/config (放到GET /apps/{app_id} 中返回即可)
