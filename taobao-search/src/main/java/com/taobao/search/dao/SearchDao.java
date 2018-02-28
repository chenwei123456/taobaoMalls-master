package com.taobao.search.dao;

import com.taobao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by chenwei on 2017/11/15.
 */
public interface SearchDao {
    SearchResult searchItem(SolrQuery query) throws Exception;
}
