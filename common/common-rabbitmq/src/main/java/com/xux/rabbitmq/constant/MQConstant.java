package com.xux.rabbitmq.constant;

/**
 * @author xux
 * @since 0.0.1
 * @since 2024/6/27 下午2:55
 */
public class MQConstant {


/*#############################          EXCHANGE_TYPE         #############################*/

    /**
     * 主题交换机类型
     */
    public static final String TOPIC = "topic";
    /**
     * 直接交换机类型
     */
    public static final String DIRECT = "direct";
    /**
     * 延迟交换机类型
     */
    public static final String DELAY_EXCHANGE_TYPE = "x-delayed-message";


/*#############################          ARG_KEY         #############################*/

    /**
     * 用于设置延迟交换机类型的KEY
     */
    public static final String DELAY_TYPE_ARG_KEY = "x-delayed-type";
    /**
     * 用于指定私信交换机的KEY
     */
    public static final String FAIL_EXCHANGE_ARG_KEY = "x-dead-letter-exchange";

    public static final String FAIL_ROUTE_ARG_KEY = "x-dead-letter-routing-key";


/*#############################          EXCHANGE_NAME         #############################*/

    /**
     * 用于业务的统一交换机
     */
    public static final String BUSINESS_EXCHANGE_NAME = "mall_exchange";
    /**
     * 用于转发失败消息的消息队列
     */
    public static final String FAIL_EXCHANGE_NAME = "fail_exchange";
    /**
     * 延迟交换机名称
     */
    public static final String DELAY_EXCHANGE_NAME = "delay_exchange";


/*#############################          QUEUE_NAME         #############################*/

    /**
     * 消息处理失败后进入的队列
     */
    public static final String FAIL_QUEUE_NAME = "fail_queue";
    /**
     * 创建订单队列名称
     */
    public static final String CREATE_ORDER_QUEUE_NAME = "create_order";
    /**
     * 超时订单队列，通过监听这个队列来更新订单超时状态
     */
    public static final String TIMEOUT_ORDER_QUEUE_NAME = "time_out_order";


/*#############################          ROUTE_KEY         #############################*/

    /**
     * 处理失败消息的路由key
     */
    public static final String MESSAGE_PROCESS_FAIL_ROUTE_KEY = "message_process_fail";
    /**
     * 订单的路由key
     */
    public static final String ORDER_ROUTE_KEY = "order.waitingPay";
    /**
     * 秒杀订单的路由key
     */
    public static final String SECKILL_ORDER_ROUTE_KEY = "seckill.order.create";


    /*#############################          BIND_ROUTE_KEY         #############################*/

    public static final String CREATE_ORDER_BIND_KEY = "*.order.create";
}
