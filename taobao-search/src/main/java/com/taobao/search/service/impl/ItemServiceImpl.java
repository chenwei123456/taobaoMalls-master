package com.taobao.search.service.impl;


import com.taobao.common.utils.ExceptionUtil;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.search.mapper.ItemMapper;
import com.taobao.search.pojo.Item;
import com.taobao.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenwei on 2017/11/15.
 */
@Service
public class ItemServiceImpl  implements ItemService {

    @Autowired
    private ItemMapper itemMapper;


    @Autowired
    private SolrServer solrServer;

    @Override
    public TaobaoResult importAllItems() {

        try{
            List<Item> list = itemMapper.getItemList();

            for (Item item : list){
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id",item.getId());
                document.setField("item_title",item.getTitle());
                document.setField("item_sell_point", item.getSell_point());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategory_name());
                document.setField("item_desc", item.getItem_des());

                //将文档写入索引库
                solrServer.add(document);
            }
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            return TaobaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaobaoResult.ok();
    }

}
