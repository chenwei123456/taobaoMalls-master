package com.taobao.portal.service.impl;

import com.taobao.common.utils.HttpClientUtil;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbUser;
import com.taobao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by chenwei on 2017/12/19.
 */
@Service
public class UserServiceImpl  implements UserService{

    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;

    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;

    @Value("${SSO_PAGE_LOGIN}")
    public String SSO_PAGE_LOGIN;

    @Value("${SSO_INTERCEPTOR}")
    public String SSO_INTERCEPTOR;

    @Value("${TAOBAO_MAIN}")
    public String TAOBAO_MAIN;

    @Override
    public TbUser getUserByToken(String token) {
        try{
            String json = HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+token);

            TaobaoResult result=TaobaoResult.formatToPojo(json, TbUser.class);
            if(result.getStatus()==200){
                TbUser  user=(TbUser) result.getData();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
