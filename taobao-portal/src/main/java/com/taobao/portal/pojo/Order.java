package com.taobao.portal.pojo;

import com.taobao.po.TbOrder;
import com.taobao.po.TbOrderItem;
import com.taobao.po.TbOrderShipping;

import java.util.List;

/**
 * Created by chenwei on 2017/12/14.
 */
public class Order extends TbOrder {

    private List<TbOrderItem>  orderItems;
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
