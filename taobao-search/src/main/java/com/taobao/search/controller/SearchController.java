package com.taobao.search.controller;

import com.taobao.common.utils.ExceptionUtil;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.search.pojo.SearchResult;
import com.taobao.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenwei on 2017/11/15.
 */

//商品查询
    @Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping(value="/query",method = RequestMethod.GET)
    @ResponseBody
    public TaobaoResult search(@RequestParam("q") String queryString,
                               @RequestParam(defaultValue="1")Integer page,
                               @RequestParam(defaultValue="40")Integer rows){
        if(StringUtils.isBlank(queryString)){
            return TaobaoResult.build(400,"查询条件不能为空");
        }

        SearchResult searchResult=null;

        try {
            queryString=new String(queryString.getBytes("iso8859-1"),"utf-8");

            searchResult = searchService.search(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return TaobaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaobaoResult.ok(searchResult);
    }
}

