package com.taobao.search.controller;

import com.taobao.common.utils.TaobaoResult;
import com.taobao.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenwei on 2017/11/15.
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     *
     *导入商品到索引库
     */

    @RequestMapping("/importAll")
    @ResponseBody
    public TaobaoResult importAllItems(){
        TaobaoResult result = itemService.importAllItems();
        return result;
    }
}
