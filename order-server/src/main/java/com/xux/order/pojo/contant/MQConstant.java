package com.xux.order.pojo.contant;

/**
 * @author xux
 * @since 0.0.1
 * @since 2024/6/27 下午2:55
 */
public class MQConstant {
    /**
     * 创建订单队列名称
     */
    public static final String ORDER_QUEUE_NAME = "create_order";
    /**
     * 消息处理失败后进入的队列
     */
    public static final String FAIL_QUEUE_NAME = "";
    /**
     * 延迟交换机
     */
    public static final String DELAY_EXCHANGE = "";
    /**
     * 订单的路由key
     */
    public static final String ORDER_ROUTE_KEY = "";
    /**
     * 超时订单队列，通过监听这个队列来更新订单超时状态
     */
    public static final String TIMEOUT_ORDER_QUEUE_NAME = "";

}
