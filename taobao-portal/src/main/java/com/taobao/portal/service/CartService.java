package com.taobao.portal.service;

import com.taobao.common.utils.TaobaoResult;
import com.taobao.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by chenwei on 2017/12/14.
 */
public interface CartService {

    TaobaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);


    //得到购物车列表
    List<CartItem> getCartItemList(HttpServletRequest request);

    //删除
    TaobaoResult deleteCartItem(long itemId, HttpServletRequest request,
                                HttpServletResponse response);


}
