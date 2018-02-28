package com.taobao.portal.service.impl;

import com.taobao.common.utils.HttpClientUtil;
import com.taobao.common.utils.JsonUtils;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.portal.pojo.Order;
import com.taobao.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by chenwei on 2017/12/19.
 */

@Service
public class OrderServiceImpl implements OrderService{

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    @Override
    public String createOrder(Order order) {

        String json = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_URL, JsonUtils.objectToJson(order));

        TaobaoResult taobaoResult = TaobaoResult.format(json);


        if(taobaoResult.getStatus()==200){
            Object orderId = taobaoResult.getData();
            return orderId.toString();
        }
        return "";
    }
}
