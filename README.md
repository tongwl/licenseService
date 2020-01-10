# 部署简介

1. 拷贝项目下的**target**文件夹或target/**license-service-1.0-SNAPSHOT.jar**文件到服务器

2. **赋予target文件夹 或 target/license-service-1.0-SNAPSHOT.jar文件 足够的访问权限！！！！！！**

```
chmod -R 777 target/
```

3. 部署完成后，如果前后端不是部署在同一台机器上，或则service的启动端口不是8080，请手动将ip和端口填入到前端配置文件**host.json**中。 前端页面若使用yarn start启动的服务，只需要在public/host.json中修改；若前端页面使用的是其他服务器(如nginx)，那么需要在dist/host.json中修改。

   ![image-20200110211513912](/Users/weitong/Library/Application Support/typora-user-images/image-20200110211513912.png)

   