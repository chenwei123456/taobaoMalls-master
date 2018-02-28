package com.taobao.portal.pojo;

import com.taobao.po.TbItem;

/**
 * Created by chenwei on 2017/12/14.
 */
public class ItemInfo extends TbItem {

  public String[] getImaged(){
      String image=getImage();
      if(image!=null){
          String[] images = image.split(",");
      }
      return null;
  }
}
