package com.taobao.rest.service.impl;

import com.taobao.common.utils.JsonUtils;
import com.taobao.mapper.TbContentMapper;
import com.taobao.po.TbContent;
import com.taobao.po.TbContentExample;
import com.taobao.rest.dao.JedisClient;
import com.taobao.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenwei on 2017/12/22.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    //private JedisClientCluster jedisClientCluster;
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;


    @Override
    public List<TbContent> getContentList(long contentCid) {

        //从缓存中取内容

        try{
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid+"");


            if(!StringUtils.isBlank(result)){
                List<TbContent> list = JsonUtils.jsonToList(result, TbContent.class);
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        List<TbContent> list = contentMapper.selectByExample(example);


        //向缓存中添加内容
        try{
            //吧list转换为字符串
            String cacheString=JsonUtils.objectToJson(list);
            //jedisClientCluster.hset(INDEX_CONTENT_REDIS_KEY, contentCid+"", cacheString);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid+"", cacheString);

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
