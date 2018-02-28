package com.taobao.service;

import com.taobao.common.pojo.ContentCatTreeNode;
import com.taobao.common.utils.TaobaoResult;

import java.util.List;

/**
 * Created by chenwei on 2017/10/19.
 */
public interface ContentCategoryService {

    List<ContentCatTreeNode> getCategoryList(long parentId);

    TaobaoResult insertContentCategory(long parentId,String name);

    /**
     *  删除节点
     * @param parentId
     * @param id
     * @return
     */
    TaobaoResult deleteContentCategory(Long parentId,Long id);

    /**
     * 更新节点
     * @param id
     * @param name
     * @return
     */
    TaobaoResult updateContentCategory(Long id,String name);

    /**
     * 插入节点
     * @param parentId
     * @param id
     * @return
     */
    TaobaoResult insertContentCategory(Long parentId,Long id);

    /**
     * 查询节点
     * @param parentId
     * @param id
     * @return
     */

    TaobaoResult selectContentCategory(Long parentId,Long id);
}
