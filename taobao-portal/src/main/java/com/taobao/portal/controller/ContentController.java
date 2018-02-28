package com.taobao.portal.controller;

import com.taobao.common.pojo.EUDResult;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbContent;
import com.taobao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenwei on 2017/12/19.
 */

@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/save")
    @ResponseBody
    public TaobaoResult insertContent(TbContent content){
        TaobaoResult result = contentService.insertContent(content);
        return result;
    }


    //加载列表
    @RequestMapping("/content/query/list")
    @ResponseBody
    public EUDResult getContentList(Long page, Long rows){
        EUDResult result = contentService.getContentList(page, rows);
        return result;
    }

    //删除
    @RequestMapping("/content/delete")
    @ResponseBody
    public TaobaoResult deleteContent(String ids){
        return contentService.deleteContent(ids);
    }

    //更新
    @RequestMapping("/rest/content/edit")
    @ResponseBody
    public TaobaoResult updateItem(TbContent content){
        TaobaoResult result=contentService.updateContent(content);
        return result;
    }


}
