package com.taobao.mapper;

import com.taobao.po.TbOrder;
import com.taobao.po.TbOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by chenwei on 2017/10/21.
 */
public interface TbOrderMapper {

    int countByExample(TbOrderExample example);

    int deleteByExample(TbOrderExample example);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    List<TbOrder> selectByExample(TbOrderExample example);

    TbOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(
            @Param("record")
                TbOrder record,
            @Param("example")
                TbOrderExample example

    );
    int updateByExample(
            @Param("record")
                    TbOrder record,
            @Param("example")
                    TbOrderExample example);

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);
}
