package com.taobao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taobao.common.pojo.EUDResult;
import com.taobao.common.utils.ExceptionUtil;
import com.taobao.common.utils.HttpClientUtil;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.mapper.TbContentMapper;
import com.taobao.po.TbContent;
import com.taobao.po.TbContentExample;
import com.taobao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenwei on 2017/11/18.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Value("${REST_BASE_URL}")
    private  String REST_BASE_URL;


    @Value("${REST_CONTENT_SYNC_URL}")
    private  String REST_CONTENT_SYNC_URL;

    @Override
    public TaobaoResult insertContent(TbContent content) {

        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        //添加缓存同步逻辑
        try {
            HttpClientUtil.doGet(REST_CONTENT_SYNC_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaobaoResult.ok();
    }

    //分页列表显示
    @Override
    public EUDResult getContentList(long page, long pageSize) {
        TbContentExample example = new TbContentExample();
        // 开始分页
        PageHelper.startPage((int) page, (int) pageSize);
        // 获取查询结果
        List<TbContent> rows = contentMapper.selectByExample(example);
        EUDResult dgr = new EUDResult();
        dgr.setRows(rows);
        // 获取分页信息 商品总数信息
        PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(rows);
        dgr.setTotal(pageInfo.getTotal());
        return dgr;
    }

    //删除
    @Override
    public TaobaoResult deleteContent(String ids) {
        try {
            String[] idsArray = ids.split(",");
            List<Long> values = new ArrayList<Long>();
            for(String id : idsArray) {
                values.add(Long.parseLong(id));
            }
            TbContentExample e = new TbContentExample();
            TbContentExample.Criteria c = e.createCriteria();
            c.andIdIn(values);
            contentMapper.deleteByExample(e);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return TaobaoResult.ok();
    }

    //修改
    @Override
    public TaobaoResult updateContent(TbContent content) {

        try {
            //更新商品
            TbContentExample e = new TbContentExample();
            com.taobao.po.TbContentExample.Criteria c = e.createCriteria();
            c.andIdEqualTo(content.getId());

            TbContent tbContent = contentMapper.selectByPrimaryKey(content.getId());

            content.setCreated(tbContent.getCreated());
            content.setUpdated(new Date());

            contentMapper.updateByExample(content, e);
            //添加缓存同步逻辑
            try {
                HttpClientUtil.doGet(REST_CONTENT_SYNC_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return TaobaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaobaoResult.ok();

    }

}
