# 2024/06/11
已完成:

- 完成评论controller层的编写
- 导入Spring Security并进行一定的配置

未完成:

- 测试评论接口
- 配置好Spring Security, 实现权限验证功能

# 2024/0612
先暂时放置Spring Security的构建,先重构评论模块

# 2024/06/15
已完成:
- 写了User-server的登录部分内容
未完成:
- ~~实现StringRedisUtil,用于登录时的jwt保存(现在他放在common模块下,考虑直接放在User模块下)~~
- ~~login时存jwt到redis,logout时移除jwt~~
