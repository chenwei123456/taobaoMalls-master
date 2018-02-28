package com.taobao.service;

import com.taobao.po.TbAdminUser;

/**
 * Created by chenwei on 2017/10/18.
 */
public interface AdminService {
    TbAdminUser getUserByToken(String token);
}
