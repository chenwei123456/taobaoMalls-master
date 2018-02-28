package com.taobao.service.impl;

import com.taobao.common.pojo.EUTreeNode;
import com.taobao.mapper.TbItemCatMapper;
import com.taobao.po.TbItemCat;
import com.taobao.po.TbItemCatExample;
import com.taobao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenwei on 2017/11/19.
 */
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper  itemCatMapper;


    @Override
    public List<EUTreeNode> getCatList(long parentId) {
        TbItemCatExample  example=new TbItemCatExample();
        TbItemCatExample.Criteria createCriteria = example.createCriteria();
        createCriteria.andParentIdEqualTo(parentId);
        //根据条件查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List<EUTreeNode>  resultList=new ArrayList<>();
        //把列表转换为treenodelist
        for (TbItemCat tbItemCat : list) {
            EUTreeNode node=new EUTreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;

    }

}