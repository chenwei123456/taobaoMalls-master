package com.taobao.service.impl;

import com.taobao.common.pojo.ContentCatTreeNode;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.mapper.TbContentCategoryMapper;
import com.taobao.po.TbContentCategory;
import com.taobao.po.TbContentCategoryExample;
import com.taobao.po.TbContentCategoryExample.Criteria;
import com.taobao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenwei on 2017/10/19.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<ContentCatTreeNode> getCategoryList(long parentId) {

        //根据parentId查询数据库
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        List<TbContentCategory> list = contentCategoryMapper
                .selectByExample(example);
        // 创建返回值list 并包装数据
        List<ContentCatTreeNode> listResult = new ArrayList<ContentCatTreeNode>();
        for (TbContentCategory contentCategory : list) {
            ContentCatTreeNode node = new ContentCatTreeNode();
            node.setId(contentCategory.getId());
            node.setState(contentCategory.getIsParent() ? "closed" : "open");
            node.setText(contentCategory.getName());
            node.setParentId(parentId);
            listResult.add(node);
        }
        return listResult;
    }

    @Override
    public TaobaoResult insertContentCategory(long parentId, String name) {
        TbContentCategory  contentCategory=new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);  //1是正常，2是删除
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategoryMapper.insert(contentCategory);
        //查看父节点的isParent列是否为true
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if(!parentCat.getIsParent()){
            parentCat.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        return TaobaoResult.ok(contentCategory);
    }

    @Override
    public TaobaoResult deleteContentCategory(Long parentId, Long id) {
        return null;
    }

    @Override
    public TaobaoResult updateContentCategory(Long id, String name) {
        return null;
    }

    @Override
    public TaobaoResult insertContentCategory(Long parentId, Long id) {
        return null;
    }

    @Override
    public TaobaoResult selectContentCategory(Long parentId, Long id) {
        return null;
    }
}
