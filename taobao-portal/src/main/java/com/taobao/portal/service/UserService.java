package com.taobao.portal.service;

import com.taobao.po.TbUser;

/**
 * Created by chenwei on 2017/12/19.
 */
public interface UserService {

    TbUser getUserByToken(String token);
}
