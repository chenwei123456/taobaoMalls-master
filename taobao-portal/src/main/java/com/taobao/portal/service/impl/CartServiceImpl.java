package com.taobao.portal.service.impl;

import com.taobao.common.utils.CookieUtils;
import com.taobao.common.utils.HttpClientUtil;
import com.taobao.common.utils.JsonUtils;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbItem;
import com.taobao.portal.pojo.CartItem;
import com.taobao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenwei on 2017/12/14.
 */

@Service
public class CartServiceImpl  implements CartService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;



    @Override
    public TaobaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
        CartItem cartItem=null;
        List<CartItem> itemList = getCartItemList(request);


        //判断购物车商品列表中红是否存在此商品
        for (CartItem cItem : itemList) {
            if(cItem.getId()==itemId){
                cItem.setNum(cItem.getNum() + num);
                cartItem=cItem;
                break;
            }
        }
        if(cartItem==null){
            cartItem=new CartItem();

            //根据商品id查询商品基本信息
            String json= HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
            //把json转换成java对象
            TaobaoResult taotaoResult = TaobaoResult.formatToPojo(json, TbItem.class);
            if(taotaoResult.getStatus()==200){
                TbItem item = (TbItem) taotaoResult.getData();
                cartItem.setId(item.getId());
                cartItem.setTitle(item.getTitle());
                cartItem.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
                cartItem.setNum(num);
                cartItem.setPrice(item.getPrice());
            }
            itemList.add(cartItem);
        }

        //把购物车列表写入cookie
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
        return TaobaoResult.ok();

    }


    private List<CartItem> getCartItemList(HttpServletRequest request) {
        //从cookie中取商品列表
        String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
        if (cartJson == null) {
            return new ArrayList<>();
        }
        //把json转换成商品列表
        try {
            List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        return itemList;
    }


    @Override
    public TaobaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取购物车商品列表
        List<CartItem> itemList = getCartItemList(request);
        //从列表中找到此商品
        for (CartItem cartItem : itemList) {
            if (cartItem.getId() == itemId) {
                itemList.remove(cartItem);
                break;
            }
        }
        //把购物车列表重新写入cookie
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);

        return TaobaoResult.ok();
    }



}
