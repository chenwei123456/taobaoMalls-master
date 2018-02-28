package com.taobao.rest.service;

import com.taobao.common.utils.TaobaoResult;

/**
 * Created by chenwei on 2017/12/22.
 */
public interface ItemService {

    TaobaoResult getItemBaseInfo(long itemid);

    TaobaoResult  getItemDesc(long itemId);

    TaobaoResult getItemParam(long itemId);
}
