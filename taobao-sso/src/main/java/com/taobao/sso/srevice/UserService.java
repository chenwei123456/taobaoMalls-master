package com.taobao.sso.srevice;

import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by chenwei on 2017/10/23.
 */
public interface UserService {

    TaobaoResult checkData(String content,Integer type);
    TaobaoResult createUser(TbUser user);
    TaobaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    TaobaoResult getUserByToken(String token);

    //退出登录
    TaobaoResult userLogout(String token);
}
