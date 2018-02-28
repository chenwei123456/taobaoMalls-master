package com.taobao.order.service.impl;


import com.taobao.common.utils.TaobaoResult;
import com.taobao.mapper.TbOrderItemMapper;
import com.taobao.mapper.TbOrderMapper;
import com.taobao.mapper.TbOrderShippingMapper;
import com.taobao.order.dao.JedisClient;
import com.taobao.order.service.OrderService;
import com.taobao.po.TbOrder;
import com.taobao.po.TbOrderItem;
import com.taobao.po.TbOrderShipping;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by chenwei on 2017/10/21.
 */
@Service
public class OrderServiceImpl implements OrderService{


    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;

    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;

    @Override
    public TaobaoResult createOrder(TbOrder order, List<TbOrderItem> itemList,
                                    TbOrderShipping orderShipping) {

        //向订单表中插入记录
        String string = jedisClient.get(ORDER_GEN_KEY);
        if(StringUtils.isBlank(string)){
            jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
        }
        //获得订单号
        long orderId = jedisClient.incr(ORDER_GEN_KEY);


        //补全pojo的属性
        order.setOrderId(orderId+"");

        //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，
        order.setStatus(1);
        Date date=new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order.setBuyerRate(0);  //0未评价，1评价

        orderMapper.insert(order);
        //插入订单明细

        for (TbOrderItem tbOrderItem : itemList) {
            //补全订单明细
            long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            tbOrderItem.setId(orderDetailId+"");

            //向订单明细插入到记录
            tbOrderItem.setOrderId(orderId+"");
            orderItemMapper.insert(tbOrderItem);
        }
        //插入物流表
        //补全物流表
        orderShipping.setOrderId(orderId+"");
        orderShipping.setUpdated(date);
        orderShipping.setCreated(date);
        orderShippingMapper.insert(orderShipping);

        return TaobaoResult.ok(orderId);
    }
}
