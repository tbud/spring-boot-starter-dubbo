# spring-boot-starter-dubbo

## 使用方式

在项目的pom文件中增加如下依赖：

```
<dependency>
    <groupId>io.tbud</groupId>
    <artifactId>spring-boot-starter-dubbo</artifactId>
    <version>1.0.3</version>
</dependency>
```

在application.properties文件中增加如下配置：

```
# 项目名称设置，必须
dubbo.application.name=application_name_XXX
# 包扫描设置，必须
dubbo.annotation.package=XXX.XXX.XXX
```
