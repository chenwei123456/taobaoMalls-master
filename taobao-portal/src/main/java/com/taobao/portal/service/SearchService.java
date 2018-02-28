package com.taobao.portal.service;

import com.taobao.portal.pojo.SearchResult;

/**
 * Created by chenwei on 2017/12/19.
 */
public interface SearchService {

    SearchResult search(String queryString,int page);
}
