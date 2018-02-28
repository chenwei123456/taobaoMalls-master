package com.taobao.controller;

import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taobao.service.
/**
 * Created by chenwei on 2017/11/18.
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/save")
    @ResponseBody
    public TaobaoResult insertContent (TbContent content){
        TaobaoResult result = contentService.insertContent(content);
        return result;
    }
}
