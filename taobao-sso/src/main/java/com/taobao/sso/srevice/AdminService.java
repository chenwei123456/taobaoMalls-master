package com.taobao.sso.srevice;

import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbAdminUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenwei on 2017/10/23.
 */
public interface AdminService {

    TaobaoResult checkData(String content, Integer type);
    TaobaoResult createUser(TbAdminUser user);
    TaobaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    TaobaoResult getUserByToken(String token);
}
