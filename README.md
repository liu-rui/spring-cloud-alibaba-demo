# spring-cloud-alibaba-demo
1. 注册中心nocas 
```
git clone https://github.com/alibaba/nacos.git
cd nacos/
mvn -Prelease-nacos clean install -U
cd distribution/target/nacos-server-0.7.0/nacos/bin

# Linux
./startup.sh -m standalone

浏览器：
http://localhost:8848/nacos 
```
2. 熔断器Sentinel 控制台
```
# 下载源码
git clone https://github.com/alibaba/Sentinel.git

# 编译打包
mvn clean package
cd sentinel-dashboard\target
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar

打开浏览器访问：http://localhost:8080/#/dashboard/home
```

4. nacos config 
```$xslt
配置的data ID一定要配置扩展名，坑
nacos-provider-config-prod.yaml
```

５．　SkyWalking
```

```

6. 
