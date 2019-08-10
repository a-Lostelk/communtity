### Coding社区
******
### 资料

[Spring文档](https://spring.io/guides)

[springboot中文文档](<https://springcloud.cc/spring-boot.html>)
 
[elastic中文社区](https://elasticsearch.cn/)

[GitHub授权登录](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Github OAuth授权登录方法详解](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)

[flyway数据库管理工具](https://flywaydb.org/getstarted/firststeps/maven)

[MyBatis Generator逆向工程](http://www.mybatis.org/generator/index.html)

[POSTman浏览器插件](https://chrome.google.com/webstore/detail/tabbed-postman-rest-clien/coohjcphdfgbiolnekdpbcijmhambjff)

[个人学习笔记](https://github.com/a-Lostelk/notes/blob/master/coding%E7%A4%BE%E5%8C%BA%E7%AC%94%E8%AE%B0.md)

[springboot学习笔记](https://github.com/a-Lostelk/notes/edit/master/SpringBoot%E8%BF%9B%E4%BF%AE%E4%B9%8B%E8%B7%AF.md)


### 工具

Maven

Git：代码管理工具

H2：轻便小巧的数据库API

flyway：独立于数据库的应用、管理并跟踪数据库变更的数据库版本管理工具。

#### 项目介绍
主要是面向个人的个人小博客，涉及到多人难度和操作度成倍递增（本人技术尚未有这么高深），使用的是h2数据库，选择h2是因为它是小巧便携的API数据库，登录个人账号是调用github登录的接口登录，省去了user和password等的管理和验证，只需要GitHub的token和授权认证成功后，就可以直接登录

##### 大致界面
![11](https://github.com/a-Lostelk/MyNotes/blob/master/codingImages/QQ拼音截图20190810094020.png)

##### github授权登录
**调用github登录的核心**
```
@Component
public class GithubProvider {
    /**
     * 从Gitub中获取access token凭证
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url(" https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
         //如果抛出异常的话返回null
        return null;
    }

    /**
     * 从GitHub中获取user信息
     * https://api.github.com/user
     */
    public GithubUser  getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            //  将从GitHub获取到的用户数据存储到GithubUser对象中（头像，id，用户名等）
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}
```



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

