```
mall
├─common
│  ├─common-core        -- 通用代码
│  ├─common-feign       -- 自定义feign相关配置
│  ├─common-rabbitmq    -- 自定义mq相关配置
│  ├─common-redis       -- 自定义redis相关配置
│  └─common-web         -- web服务公共依赖
├─file-server           -- 文件服务，用于上传图片或者视频
├─gateway-server        -- 网关
├─order-server          -- 订单服务，包含订单创建订单支付和订单状态管理
├─product-server        -- 商品服务，包括店铺管理和商品管理以及评论模块
├─seckill-server        -- 秒杀服务
└─User-server           -- 用户服务，目前只有登录登出这两个基本功能
```

