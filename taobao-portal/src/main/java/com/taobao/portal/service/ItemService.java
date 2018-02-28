package com.taobao.portal.service;

import com.taobao.portal.pojo.ItemInfo;

/**
 * Created by chenwei on 2017/12/18.
 */
public interface ItemService {

    ItemInfo getItemById(long itemId);


    String getItemDescById(long itemId);

    String getItemParam(long itemId);
}
