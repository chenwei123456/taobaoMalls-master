package com.taobao.portal.service.impl;

import com.taobao.common.utils.HttpClientUtil;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.portal.pojo.SearchResult;
import com.taobao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenwei on 2017/12/19.
 */
@Service
public class SearchServiceImpl  implements SearchService{


    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, int page) {

        Map<String,String> param = new HashMap<>();
        param.put("q",queryString);
        param.put("page",page+"");

        //执行调用服务
        try{
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL,param);

            //字符串转换
            TaobaoResult result = TaobaoResult.formatToPojo(json,SearchResult.class);

            if(result.getStatus()==200){
                SearchResult searchResult=(SearchResult) result.getData();
                return searchResult;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
