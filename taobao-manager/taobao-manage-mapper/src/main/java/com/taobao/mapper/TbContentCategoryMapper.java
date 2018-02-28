package com.taobao.mapper;

import com.taobao.po.TbContentCategory;
import com.taobao.po.TbContentCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by chenwei on 2017/10/12.
 */
public interface TbContentCategoryMapper {

    int countByExample(TbContentCategoryExample example);

    int deleteByExample(TbContentCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbContentCategory record);

    int insertSelective(TbContentCategory record);

    List<TbContentCategory> selectByExample(TbContentCategoryExample example);

    TbContentCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(
            @Param("record")
                    TbContentCategory record,
            @Param("example")
                    TbContentCategoryExample example
    );
    int updateByExample(
            @Param("record")
                    TbContentCategory record,
            @Param("example")
                    TbContentCategoryExample example);

    int updateByPrimaryKeySelective(TbContentCategory record);

    int updateByPrimaryKey(TbContentCategory record);
}
