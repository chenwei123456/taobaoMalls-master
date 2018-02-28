package com.taobao.portal.service.impl;

import com.taobao.common.utils.HttpClientUtil;
import com.taobao.common.utils.JsonUtils;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbItemDesc;
import com.taobao.po.TbItemParamItem;
import com.taobao.portal.pojo.ItemInfo;
import com.taobao.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by chenwei on 2017/12/18.
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;


    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;


    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;

    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;
    @Override

    public ItemInfo getItemById(long itemId) {

        //调用rest服务查询商品基本信息
        try{
            String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
            if(!StringUtils.isBlank(json)){
                TaobaoResult taobaoResult = TaobaoResult.formatToPojo(json,ItemInfo.class);
                if(taobaoResult.getStatus()==200){
                    ItemInfo item = (ItemInfo)taobaoResult.getData();
                    return item;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //取商品描述
    @Override
    public String getItemDescById(long itemId) {

        try{
            String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_DESC_URL+itemId);
            TaobaoResult taobaoResult = TaobaoResult.formatToPojo(json, TbItemDesc.class);

            if(taobaoResult.getStatus()==200){
                TbItemDesc itemDesc = (TbItemDesc) taobaoResult.getData();
                String result=itemDesc.getItemDesc();
                return result;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String getItemParam(long itemId) {

        try{
            String json=HttpClientUtil.doGet(REST_BASE_URL+ITEM_PARAM_URL+itemId);
            TaobaoResult taobaoResult = TaobaoResult.formatToPojo(json,TbItemParamItem.class);

            if(taobaoResult.getStatus()==200){
                TbItemParamItem itemParamItem = (TbItemParamItem) taobaoResult.getData();
                String paramData=itemParamItem.getParamData();

                //生成html
                //把规格参数json转化为java对象
                List<Map> jsonList = JsonUtils.jsonToList(paramData,Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"1\"  cellspacing=\"1\"  width=\"100%\"  border=\"0\"  >\n");
                for(Map m1:jsonList){
                    sb.append("  <tr>\n");
                    sb.append("<th colspan=\"2\">"+m1.get("group")+"</th>\n");
                    sb.append( "</tr>\n");
                    List<Map> list2=(List<Map>) m1.get("params");
                    for (Map m2 : list2) {
                        sb.append("  <tr>\n");
                        sb.append("<th>"+m2.get("k")+"</th>\n");
                        sb.append("<th>"+m2.get("v")+"</th>\n");
                        sb.append( "</tr>\n");			}
                }

                sb.append(" </table>");
                return sb.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
