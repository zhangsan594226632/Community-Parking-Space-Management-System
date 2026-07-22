
# 资料获取  点击  [**《基于springboot+vue小区停车位管理系统》资料**](https://nwqbsc0rm1n.feishu.cn/docx/QnFZdiPRloKSzwxY7hdc6MLUnlb)
---

## 一、项目概述

### 1\.1 项目背景

随着城市小区私家车保有量持续增长，小区停车位租赁、售卖、访客临时停车管理混乱，传统线下登记方式效率低下，业主无法线上查看空余车位、咨询车位租售信息，物业也难以统一管理车位资源、停车登记、活动公告。为解决以上痛点，开发**小区停车位管理系统**，搭建业主前端展示平台与后台管理平台，实现车位租售线上化、停车业务数字化管理。

### 1\.2 项目目标

1. 面向小区业主：支持浏览出租车位、出售车位、商城公告活动、车位预约租赁、注册登录、个人中心、临时停车登记。

2. 面向系统管理员：全量管理用户、物业账号、轮播图、出租车位、出售车位、公告活动、临时停车记录。

3. 面向物业管理员：维护车位信息、处理业主车位租赁预约、发布活动公告。

4. 实现前后端分离架构，页面交互流畅，数据统一管理。

### 1\.3 技术选型

#### 后端技术栈

- 核心框架：SpringBoot 2\.7\.x

- 持久层：MyBatis/MyBatis\-Plus

- 数据库：MySQL 8\.0

- 工具：Lombok、Hutool、FileUpload 文件上传

- 权限：Session/Token 登录鉴权

- 构建工具：Maven

#### 前端技术栈

- 框架：Vue2/Vue3 \+ Vue CLI

- UI 组件库：Element UI / Element Plus

- 网络请求：Axios

- 路由：Vue Router

- 页面轮播：Swiper（首页 banner 轮播）

- 后台布局：后台经典侧边栏管理布局

#### 部署环境

Tomcat / SpringBoot 内置 Tomcat；支持 Windows/Linux 服务器部署

### 1\.4 系统用户角色划分

1. **普通业主用户**：前台网页访问，注册账号、浏览车位、预约租车位、临时停车登记、查看公告活动。

2. **物业管理员**：后台登录，维护车位信息、管理租赁订单、发布商城活动公告。

3. **超级系统管理员**：最高权限，管理所有用户、物业账号、基础数据、轮播图、车位全部信息。

## 二、系统功能需求分析

### 2\.1 前台业主端功能（网站首页）

从截图页面梳理前台功能模块：

1. **首页模块**

    - 顶部导航栏：首页、出售车位、商城活动、出租车位；右上角登录 / 注册入口

    - Banner 轮播图（汽车轮播大图，左右切换）

    - 出租车位展示区：展示车位缩略图、车位名称、出租价格，提供【更多】跳转列表

    - 出售车位展示区：展示出售车位信息，提供【更多】跳转

2. **出租车位模块**

    - 出租车位列表页：分页展示所有可租赁车位

    - 车位详情页：展示停车场编号、停车场名称、车位编号、车位类型、位置、收费标准、出租价格、车位状态、充电桩、物业信息；提供**预约、租赁**操作按钮

3. **出售车位模块**

    - 出售车位列表、车位详情，业主查看车位售价、车位信息

4. **商城活动（公告信息）模块**

    - 首页展示活动摘要列表（标题、日期、简介、配图）

    - 活动列表页：支持标题搜索、分页查询活动公告

    - 活动详情：查看完整公告内容

5. **注册功能**
业主注册表单：用户账号、密码、确认密码、姓名、头像上传、性别、手机号、车牌号

6. **登录功能**
区分角色登录入口（管理员、物业管理员登录界面）

7. **个人中心**

    - 用户信息修改

    - **临时停车登记**：访客临时停车录入，填写登记名称、登记时间、账号、姓名、登记事项；提交保存停车登记记录

### 2\.2 后台管理端功能（侧边栏管理系统）

根据后台截图整理菜单功能：

1. 首页：后台欢迎主页

2. **用户管理**：管理小区业主账号，增删改查用户信息

3. **临时登记管理**：查看所有业主提交的临时停车登记记录

4. **轮播图管理**

    - 维护前台首页 Banner 轮播图（swiper 轮播），支持新增、修改、删除轮播图片、名称

5. **物业管理员管理**

    - 管理物业账号，新增、编辑、删除物业管理员账号，分配权限

6. **出租车位管理**

    - 车位信息 CRUD：停车场信息、车位编号、车位类型、位置、出租价格、车位状态（已出租 / 空闲）、充电桩配置、关联物业账号

7. **公告信息管理（商城活动）**

    - 后台发布、编辑、删除商城活动公告，设置标题、正文、配图、发布时间

8. **出售车位管理**

    - 维护待出售车位全部信息，价格、位置、配套设施等

## 三、数据库设计

### 3\.1 核心数据表设计

#### （1）user 业主用户表（普通业主）

|字段名|类型|说明|
|---|---|---|
|id|bigint|主键自增|
|username|varchar|用户账号（唯一）|
|password|varchar|登录密码|
|name|varchar|用户姓名|
|avatar|varchar|头像图片地址|
|gender|varchar|性别|
|phone|varchar|手机号码|
|car\_number|varchar|车牌号|
|create\_time|datetime|注册时间|

#### （2）admin 系统管理员表（超级管理员）

|字段名|类型|说明|
|---|---|---|
|id|bigint|主键|
|username|varchar|账号|
|password|varchar|密码|
|name|varchar|管理员名称|

#### （3）property\_admin 物业管理员表

|字段名|类型|说明|
|---|---|---|
|id|bigint|主键|
|username|varchar|物业账号|
|password|varchar|密码|
|property\_name|varchar|物业名称|
|phone|varchar|联系电话|

#### （4）swiper 轮播图表

|字段名|类型|说明|
|---|---|---|
|id|bigint|主键|
|name|varchar|轮播图名称（swiper1/swiper2）|
|img\_url|varchar|图片访问地址|
|sort|int|排序序号|

#### （5）rent\_parking 出租车位表

|字段名|类型|说明|
|---|---|---|
|id|bigint|主键|
|park\_code|varchar|停车场编号|
|park\_name|varchar|停车场名称|
|parking\_code|varchar|车位编号|
|parking\_type|varchar|车位类型|
|location|varchar|车位位置|
|price|decimal|出租价格|
|fee\_standard|varchar|收费标准|
|status|varchar|车位状态：空闲 / 已出租|
|pile|varchar|充电桩配置|
|property\_id|bigint|关联物业管理员 id|
|detail|text|车位详细介绍|
|img\_url|varchar|车位图片|

#### （6）sell\_parking 出售车位表

|字段名|类型|说明|
|---|---|---|
|id|bigint|主键|
|park\_code|varchar|停车场编号|
|park\_name|varchar|停车场名称|
|parking\_code|varchar|车位编号|
|location|varchar|车位位置|
|sell\_price|decimal|出售价格|
|pile|varchar|充电桩|
|img\_url|varchar|车位图片|
|detail|text|车位详情描述|

#### （7）activity 商城活动（公告信息）表

|字段名|类型|说明|
|---|---|---|
|id|bigint|主键|
|title|varchar|活动标题|
|content|text|活动正文内容|
|publish\_date|date|发布日期|
|img\_url|varchar|配图地址|
|create\_time|datetime|创建时间|

#### （8）temp\_register 临时停车登记表

|字段名|类型|说明|
|---|---|---|
|id|bigint|主键|
|register\_name|varchar|登记名称|
|register\_time|datetime|登记时间|
|user\_id|bigint|业主用户 id|
|user\_name|varchar|业主姓名|
|matter|text|登记事项|

#### （9）park\_order 车位租赁预约订单表（扩展）

|字段名|类型|说明|
|---|---|---|
|id|bigint|主键|
|rent\_parking\_id|bigint|出租车位 id|
|user\_id|bigint|预约业主 id|
|order\_status|varchar|订单状态：预约中、租赁生效、取消|
|create\_time|datetime|预约时间|

## 四、系统模块详细设计

### 4\.1 前台页面模块流程

1. **访问首页**：加载轮播图、出租车位预览、出售车位预览；顶部导航切换页面

2. **车位浏览流程**
首页【更多】→车位列表→点击车位进入详情→登录后可预约 / 租赁车位

3. **商城活动流程**
导航【商城活动】→活动列表（支持标题搜索）→点击查看活动详情

4. **业主注册登录流程**
右上角头像图标→跳转登录页→没有账号跳转到注册页面，填写信息完成注册

5. **临时停车登记流程**
登录进入个人中心→临时登记页面→填写表单提交→后台管理员查看登记记录

### 4\.2 后台管理模块流程

1. 管理员 / 物业账号登录后台系统

2. 超级管理员：拥有全部菜单权限，可以新增物业账号、管理轮播图、管理业主、管理所有车位与公告

3. 物业管理员：仅操作归属自己的车位信息、处理租赁预约、发布活动公告

4. 基础数据维护：上传轮播图片、新增车位信息、录入公告内容

## 五、界面原型说明
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/b289914b16f648b2af1b402df779d447.png#pic_center)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/807dc5222326428facca476289a210ab.png#pic_center)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/0f61b6430d76411eac427a10ce11b214.png#pic_center)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/320918e2c2f44f9cb71fabbe62109216.png#pic_center)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/04899577d5a54492ae75978aa89bcc3f.png#pic_center)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/e459d40b28484d6ca927af6849991880.png#pic_center)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/c89bccf3ddef4a82a52d045f4a55e869.png#pic_center)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/19e82040577845e4b3e31eed25aec87d.png#pic_center)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/6b40f39d329044e298622d579886c8f6.png#pic_center)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/5e62cbf8f7b64742886ad0fa9957a82c.png#pic_center)


### 5\.1 前台页面

1. **首页**：顶部导航 \+ 汽车 Banner 轮播（左右箭头切换），下方出租车位卡片展示、出售车位卡片展示

2. **出租车位详情页**：面包屑导航【首页 / 出租车位】；左侧车位图片，右侧展示所有车位属性，底部预约、租赁按钮

3. **商城活动首页**：浅黄色背景，列表展示活动标题、日期、正文摘要、配图

4. **商城活动列表页**：顶部搜索框（标题检索）、分页组件

5. **用户注册页面**：浅蓝色卡通背景表单，包含账号、密码、头像上传、车牌号等录入项

6. **临时停车登记页面**：表单录入停车登记信息，取消 / 保存按钮

### 5\.2 后台管理页面

1. **登录页面**：分【管理员】【物业管理员】标签页，输入账号密码登录

2. **后台主页布局**：左侧垂直菜单栏，顶部系统标题栏，右侧主内容区域
菜单列表：首页、用户管理、临时登记管理、轮播图管理、物业管理员管理、出租车位、公告信息管理、出售车位管理

3. **轮播图管理页面**：表格展示轮播名称、预览图片，提供修改操作

4. 各个管理页面统一采用表格 \+ 新增、编辑、删除标准后台 CRUD 布局

## 六、接口设计（核心接口示例）

### 6\.1 用户相关接口

```Plain Text
POST /api/user/register          业主注册
POST /api/user/login             业主前台登录
GET  /api/user/info              获取当前登录业主信息
PUT  /api/user/update            修改业主信息
```

### 6\.2 轮播图接口

```Plain Text
GET  /api/swiper/list            获取前台首页轮播图列表
GET  /admin/swiper/list          后台查询轮播图
PUT  /admin/swiper/update        修改轮播图
POST /admin/swiper/add           新增轮播图
```

### 6\.3 出租车位接口

```Plain Text
GET  /api/rent/list              前台出租车位分页列表
GET  /api/rent/{id}              车位详情
POST /api/rent/reserve           预约车位
GET  /admin/rent/list            后台车位管理列表
POST /admin/rent/save            新增出租车位
PUT  /admin/rent/update          修改车位信息
DELETE /admin/rent/{id}          删除车位
```

### 6\.4 商城活动（公告）接口

```Plain Text
GET  /api/activity/homeList      首页展示活动摘要
GET  /api/activity/list          前台活动分页+标题搜索
GET  /api/activity/{id}          活动详情
POST /admin/activity/save        后台新增公告
```

### 6\.5 临时停车登记接口

```Plain Text
POST /api/tempRegister/save      业主提交临时停车登记
GET  /admin/tempRegister/list    后台查看所有登记记录
```

## 七、部署方案

1. **数据库部署**
安装 MySQL8\.0，执行项目提供的 sql 脚本，初始化数据表、测试账号。

2. **后端部署**
SpringBoot 项目打包 jar 包，使用`java -jar xxx.jar`运行；配置 application\.yml 数据库连接、文件上传路径。

3. **前端部署**
Vue 项目执行`npm run build`打包 dist 文件夹；部署至 Nginx，反向代理后端接口地址。

4. **Nginx 配置**
静态资源访问、图片资源代理，跨域处理。

## 八、系统测试要点

1. **功能测试**

    - 业主注册、登录、退出；密码一致性校验、头像上传

    - 首页轮播正常切换，车位列表、详情正常加载

    - 活动搜索分页功能正常

    - 车位预约、临时停车表单提交保存

    - 后台管理员增删改查全部基础数据

2. **权限测试**

    - 普通业主无法访问后台地址

    - 物业管理员不能操作不属于自己的数据

    - 未登录用户无法执行预约、提交登记操作

3. **兼容性测试**
前台页面适配主流浏览器（Chrome、Edge）；后台管理系统适配 PC 端

## 九、项目扩展方向

1. 增加在线支付模块，车位租赁线上缴费

2. 接入车牌识别接口，实现小区自动进出闸机联动

3. 消息通知：租赁到期短信提醒业主

4. 数据统计看板：后台统计车位出租率、临时停车数量

5. 小程序端同步，业主微信快速访问系统

## 十、总结

小区停车位管理系统基于 SpringBoot\+Vue 前后端分离架构，区分**前台业主展示端**与**后台管理端**，覆盖小区车位出租、出售、公告活动、临时停车登记全场景业务。系统划分超级管理员、物业管理员、小区业主三类角色，解决传统小区车位管理低效、信息不透明问题，实现车位资源信息化线上管理，满足小区日常停车业务需求。






