package com.taobao.rest.service;

import com.taobao.po.TbContent;

import java.util.List;

/**
 * Created by chenwei on 2017/12/22.
 */
public interface ContentService {

    List<TbContent> getContentList(long contentCid);
}
