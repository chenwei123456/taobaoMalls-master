package com.taobao.portal.controller;

import com.taobao.common.pojo.ContentCatTreeNode;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by chenwei on 2017/12/19.
 */

//内容分类管理
    @Controller
    @RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    List<ContentCatTreeNode> getCategoryList(@RequestParam(value = "id",defaultValue = "0")long parentId){

        return contentCategoryService.getCategoryList(parentId);
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaobaoResult createContentCategory(Long parentId,String name){
        TaobaoResult result = contentCategoryService.insertContentCategory(parentId,name);
        return result;
    }

    //删除节点
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @ResponseBody
    public TaobaoResult deleteContentCategory(Long parentId,Long id){

        TaobaoResult result = contentCategoryService.deleteContentCategory(parentId, id);
        return result;
    }

    //更新节点
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public TaobaoResult updateContentCategory(Long id, String name) {
        return contentCategoryService.updateContentCategory(id, name);
    }

}
