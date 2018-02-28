package com.taobao.service;

import com.taobao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by chenwei on 2017/11/18.
 */
public interface ItemCatService {

    List<EUTreeNode> getCatList(long parentId);
}
