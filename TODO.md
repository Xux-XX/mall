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

# 2024/06/16

- 基本实现了CommentService,还有过滤敏感词功能没有实现,考虑用aop去实现

预定计划:
- ~~用aop实现敏感词过滤~~

2024/06/17

预定计划:
- *实现ProductService接口中的所有方法
- 修改鉴权模块使 admin权限 > creator权限，即creator能操作的admin也可以
- 重构敏感词模块,使其作用于controller的参数校验上(长期计划,暂时不实现)

# 2024、06、19
- 秋招紧急，先只写接口和关键逻辑，crud代码后面补充
