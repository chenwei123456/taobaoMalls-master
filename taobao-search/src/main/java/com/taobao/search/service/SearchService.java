package com.taobao.search.service;

import com.taobao.search.pojo.SearchResult;

/**
 * Created by chenwei on 2017/11/15.
 */
public interface SearchService {
    SearchResult search(String queryString, int page,int rows) throws Exception;
}
