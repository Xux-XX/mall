预定计划:
- 修改鉴权模块使 admin权限 > creator权限，即creator能操作的admin也可以
- 重构敏感词模块,使其作用于controller的参数校验上

(以上均是长期计划,暂时不实现)

- 完善秒杀模块：监听rabbitmq创建订单，订单超时通过rabbitmq加库存
- 使用BinLog配合mq完成数据库和缓存的最终一致性
- *使用Redis中zset数据结构实现商品销量排行榜功能
