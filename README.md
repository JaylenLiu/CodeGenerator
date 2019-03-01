# CodeGenerator(代码生成器)
### 概述
系统只生成后台代码，后台架构为MVC + Mybatis。
首先，系统集成mybatis-generator官方代码生成器，可以自动生成实体、mapper接口以及xml配置文件。
然后使用velocity模板引擎生成service接口和实现类以及controller。并重写了entity。

### 程序入口
1. 程序入口在test目录下CodeGenerator类，使用junit运行。
2. 前端页面存放在resource/static下，启动springboot后，访问localhost:8082/codegenerator/index.html即可。

### 准备工作
- 首先，初始化数据库，sql文件在doc/sql下。

- 页面登录密码：admin/123456

   ![](./src/doc/img/login.png)

- 由于生成器是通过数据库表生成后台代码及前端页面，所以需要首先配好数据库连接。
   ![](./src/doc/img/database.png)

- 最后安装流程指引完成页面生成。
   ![](./src/doc/img/codegenerator.png)

### 注意



### mybatis-generator

mybatis-generator是mybatis官方提供的代码生成器。其主要根据数据库表生成实体类以及与数据库交互的接口层。
其主要配置文件是resource下的generatorConfig.xml。xml中有详细的注释说明，其中较为重要的配置包括：
- defaultModelType<br>
\<context\>标签中的defaultModelType决定生成的实体结构。
<br>flat:实体所有内容（主键，blob）等全部生成在一个对象中。
<br>hierarchical:会把blob、text类型单独形成一个实体对象。
- targetRuntime<br>
主要是选择生成代码的模式。
    - MyBatis3 ：生成基于MyBatis3.x以上版本的内容，包括XXXBySample。
    - MyBatis3Simple：类似MyBatis3，只是不生成XXXBySample。
    - MyBatis3DynamicSql：动态sql，支持java8 lambda表达式。
### 目录结构介绍
- base包主要是基础类声明，将公共的方法抽离出来，减少代码重复，提高代码复用率。
- commom包主要是公共类。包括统一异常处理类，自定义异常类，http状态码枚举类，分页过滤器，统一的信息类以及代码生成的工具类。
- entity包主要是实体类。生成的实体类与普通实体类不同之处在于使用了lombok。只需要在实体声明前面加上
  @Data,即可自动添加get、set、toString、hashcode、equals等。如使用Intellij IDEA 则需要安装插件才能识别。
- util: 工具类中DatabaseUtil可以获取数据库的元信息。StringContextUtil可以获取spring上下文。
- aspect: 通过aop进行日志记录。
