package com.taobao.service;

import com.taobao.common.pojo.EUDResult;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbItemParamItem;

/**
 * Created by chenwei on 2017/11/22.
 */
public interface ItemParamService {

    TaobaoResult getItemParamCid (long cid);

    TaobaoResult insertItemParam(TbItemParamItem itemParam);

    EUDResult getItemList(int page,int rows);

    TbItemParamItem listParamDesc(Long id);
    /*
     * 删除
     */
    TaobaoResult deleteParam(String ids);
}
