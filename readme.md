## 统一认证系统idp   1.0.0v


### 目录
- 单点登录系统介绍
- 数据建模文档
- 类编写文档
- 应用签名文档

### 一. 单点登录系统介绍
	单点登录个人认为可以概括为两种理解
	1. 针对于手机APP，一个账号只能在一个APP上登录。就比如说手机QQ，当账号在另一个手机
	上登录后，上一个账号会在另一个手机上异地登陆。
	2. 针对于网站系统，针对于多个web应用，比如淘宝和天猫，两个web应用，但是这两个应用共享
	同一个账号体系。在A应用登录后，B应用无需再次登陆。此时用专业术语讲解，有多个sp应用，
	和一个idp统一认证应用。多个sp应用有下面三种情况
	
		a. 在同一个域名下的不同站点
			www.domain.com/site1
			www.domain.com/site2
		b. 同一个域但是不同的子域
			sub1.domain.com
			sub2.domain.com
		c. 不同域之间
			www.domain1.com
			www.domain2.com

### 二. 数据建模文档
	一览：用户表，登录日志表，应用表，验证码表
	
		用户表（主键，用户名，密码，创建时间）
		登录日志表（主键，时间，用户id，备注）
		应用表（主键，应用名称，appkey，appsecret，登录地址，注销地址）
		验证码表（主键，手机号，验证码）

### 三. 类编写文档
	Oauth权限类
		登录方法
		注销方法
	Cert颁发票据类
		颁发方法
	Config配置类
		全局变量定义
	AppUtil工具类
		pwd密码加密方法（md5加盐）
		sign应用签名方法
### 四. 应用签名文档
	每个sp应用都需要配置4个参数，appkey，appsecret，登录地址，注销地址
	主要用在：用户登录成功后，idp端加密字段，跳转到sp的登录地址，sp解析加密字段，判断是否
	从idp中跳转过来。防止黑客用不法手段登录sp应用。
	
	加密前字段主要有两个ttl和uid。加密后字段为ttl，uid和sign。
	这个签名文档主要介绍sign的生成办法。
	
	1、构造验证参数字符串。
	
		将请求参数按照参数名字典升序排列后，把所有参数param=value用&连接起来，类似URI中Query
		string的构造方式。目前支持的参数有：签名失效时间ttl（单位为秒）和用户uid。例：		ts=1443079775&uid=U123456789

	2、使用HMAC-SHA1方式，以API密钥（key）对上一步生成的参数字符串进行加密。
	
		常见程序语言通常会内置加密函数，或通过扩展库提供支持。例如在NodeJS中，您可以使用crypto
		模块中的中的createHmac函数，例：crypto.createHmac("sha1", key)。
		其中key为应用的appsecret

	3、将上一步生成的加密结果用base64编码，并做一个urlencode，得到签名sign。
		例：假设key为"secret"，步骤（1）中的参数例子加密后得到的结果应为为
		dTYeoN8WdOfW4PiwgEdLa0gWFzo=，做完urlencode最终得到的签名sign为
		dTYeoN8WdOfW4PiwgEdLa0gWFzo%3d

	4、将上一步得到的签名sig附在第一步构造的参数字符串后，作为请求的一个参数发送。

		上述例子里，请求参数即为
		ttl=30&uid=U123456789&sign=dTYeoN8WdOfW4PiwgEdLa0gWFzo%3d
	
	因此，跳转到应用的登录地址，按照此方法验证sign即可判断是否是idp跳转跳转过来的。
	
	
	