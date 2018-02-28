package com.taobao.service;

import com.taobao.common.pojo.EUDResult;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbContent;

/**
 * Created by chenwei on 2017/11/18.
 */
public interface ContentService {

    /**
     * 插入操作
     *
     * @param content
     * @return
     */
    TaobaoResult insertContent(TbContent content);

    /**
     * 列表
     * @param page
     * @param pageSize
     * @return
     */
    EUDResult getContentList(long page,long pageSize);

    /**
     * 删除
     * @param ids
     * @return
     */
    TaobaoResult deleteContent(String ids);
    /**
     * 更新操作
     *
     * @param content
     * @return
     */
    TaobaoResult updateContent(TbContent content);
}
