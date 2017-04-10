# framework-assembly-basis
- assemble basal framework and bulid component-based standard project continuously.

- *— tommy*

## 〇、思维导图

<br>
## 一、已有功能
### 1.1 业务实例
- 登录注册
- CRUD实例
- 分页
- 验证码（文字、字母和图形）
- “记住我”功能

### 1.2 常用辅助
#### 1.2.1 后端
- 数据加密（有待完善）
- 文件上传（[Spring Project MultipartResolver][1]、[Apache Commons FileUpload Project][2]）
- 文件下载
- 拦截器（有待完善）
- 过滤器
- 日志系统
- 支持中文
- 缓存
	- Memcached
	- Redis
- 异常（统一）处理
- Spring AOP
- XML解析器
- HttpComponents
- 泛型<T>转换
- 定时任务 
- 数据校验（三种）
	- 后端校验
		- 后端直接校验
		- 通过AJAX间接校验
	- 前端校验
- MVC Framework
	- Spring MVC
	- Spring
-  ORM
	- MyBatis
		- XML版本
		- Annotation版本
	- Hibernate
- 消息队列
	- ActiveMQ

#### 1.2.2 前端
- Bootstrap

<br>
## 二、未有功能
- Restful API
- spring-mail
- Sturts2
- XORM
- Beego

<br>
## 三、注意
- 应始终保持此项目的设计规范标准
- 部分包Maven中央库中没有，需要卓键自己的库，最好找到中央库替代jar，或者把相关Jar文件进行存储
- 该项目为基础项目，可用于构建其他项目使用
- 部分知识只能使用的时候参考一下，而不能完全自己写出来，甚至不知道原理，有待理解原理，熟练使用 @version 2016/11/21 14:53

[1]: http://dakulaliu.iteye.com/blog/236235
[2]: http://www.cnblogs.com/xing901022/p/3855613.html