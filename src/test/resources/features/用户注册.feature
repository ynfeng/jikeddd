# language: zh-CN
功能: 用户注册
  用户使用用户名注册，简单起见，没有密码

  场景: 未注册的用户名可以注册成功
    当使用 "zhangsan" 注册
    那么注册成功

  @Igonre
  场景: 同一个用户名不能重复注册
  假如 "zhangsan" 已经被注册
    当使用 "zhangsan" 注册
    那么注册失败
