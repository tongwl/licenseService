# Server部署

暂不支持部署在windows (没有license生成工具，后期可以改进)



## 部署在mac

1. 拷贝项目下的`target`文件夹 或 `target/license-service-1.0-SNAPSHOT.jar`文件到服务器

2. 赋予`target`文件夹 或 `target/license-service-1.0-SNAPSHOT.jar`文件足够的访问权限

   ```
   chmod -R 777 target/
   #or
   chmod -R 777 license-service-1.0-SNAPSHOT.jar
   ```

3. 可以用最简单的java命令(*需要安装jdk*)启动

   ```
   java -jar license-service-1.0-SNAPSHOT.jar --server.port=8080
   ```

4. 启动完成后，如果前后端不是部署在同一台机器上，或则service的启动端口不是8080，请手动将ip和端口填入到前端配置文件**host.json**中。 前端页面若使用yarn start启动的服务，只需要在public/host.json中修改；若前端页面使用的是其他服务器(如nginx)，那么需要在dist/host.json中修改。



## 部署在linux

1. 拷贝项目下的`target/license-service-1.0-SNAPSHOT.jar`文件到服务器的`/usr/java/projects/`目录

2. 拷贝项目根目录下的`go_lic`文件到服务器的`/usr/java/projects/` 目录***(必须是这个路径)***

3. 赋予`license-service-1.0-SNAPSHOT.jar` 和 `go_lic`足够的访问权限

   ```
   chmod -R 777 license-service-1.0-SNAPSHOT.jar
   chmod -R 777 go_lic
   ```

4. 可以用最简单的java命令(*需要安装jdk*)启动

   ```
   nohup java -jar license-service-1.0-SNAPSHOT.jar --server.port=8080 & 
   ```

5. 启动完成后，如果前后端不是部署在同一台机器上，或则service的启动端口不是8080，请手动将ip和端口填入到前端配置文件**host.json**中。 前端页面若使用yarn start启动的服务，只需要在public/host.json中修改；若前端页面使用的是其他服务器(如nginx)，那么需要在dist/host.json中修改。