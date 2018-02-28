package com.taobao.order.service;

import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbOrder;
import com.taobao.po.TbOrderItem;
import com.taobao.po.TbOrderShipping;

import java.util.List;

/**
 * Created by chenwei on 2017/10/20.
 */
public interface OrderService {
    TaobaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
