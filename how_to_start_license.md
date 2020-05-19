### 部署机器:
172.16.110.15



### 启动license前端服务器

license前端项目使用nginx服务器，安装目录是 `/usr/local/nginx/`，
**进入到 `/usr/local/nginx/sbin`目录下运行` ./nginx `命令即可启动license服务器。**



### 启动license-server后端服务器
license-server使用java命令启动，license-server项目地址是`/usr/java/projects/license-service-1.0-SNAPSHOT.jar`，**进入目录`/usr/java/projects`使用以下命令启动service即可：**

```
//nohup意思是不挂断运行命令,当账户退出或终端关闭时,程序仍然运行
// & 表示以后台方式运行
nohup java -jar license-service-1.0-SNAPSHOT.jar --server.port=8080 & 
```



### 访问地址

```
http://172.16.110.15:9000/
```

*如果访问不同，参看关闭防火墙。*



### 关闭防火墙

```
service firewalld stop
```



