package com.taobao.service;

import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbItemParam;

/**
 * Created by chenwei on 2017/11/20.
 */
public interface ItemParamItemService {

    String getItemParamByItemId(Long itemId);

    //规格参数模板
    TaobaoResult insertItemParam(TbItemParam itemParam);
}
