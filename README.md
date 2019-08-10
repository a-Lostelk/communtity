### Coding社区
******
## 资料

[Spring文档](https://spring.io/guides)
 
[elastic中文社区](https://elasticsearch.cn/)

[GitHub授权登录](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Github OAuth授权登录方法详解](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)

[flyway数据库管理工具](https://flywaydb.org/getstarted/firststeps/maven)

[MyBatis Generator逆向工程](http://www.mybatis.org/generator/index.html)

[POSTman浏览器插件](https://chrome.google.com/webstore/detail/tabbed-postman-rest-clien/coohjcphdfgbiolnekdpbcijmhambjff)

#### 工具

Maven

Git：代码管理工具

H2：轻便小巧的数据库API

flyway：独立于数据库的应用、管理并跟踪数据库变更的数据库版本管理工具。

#### 脚本
```h2
create table USER
(
  ID           BIGINT default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_C833339F_EF97_4A7D_96A7_113C04736145) auto_increment
    primary key,
  ACCOUNT_ID   VARCHAR(100),
  NAME         VARCHAR(50),
  TOKEN        VARCHAR(36),
  GMT_CREATE   BIGINT,
  GMT_MODIFIED BIGINT,
  BIO          VARCHAR(256),
  AVATAR_URL   VARCHAR(100)
);
```
运行逆向工程命令：**mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate**