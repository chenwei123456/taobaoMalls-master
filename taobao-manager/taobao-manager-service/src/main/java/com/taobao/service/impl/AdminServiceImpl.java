package com.taobao.service.impl;

import com.taobao.common.utils.HttpClientUtil;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbAdminUser;
import com.taobao.service.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by chenwei on 2017/10/18.
 */
@Service
public class AdminServiceImpl implements AdminService {


    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;

    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;

    @Value("${SSO_PAGE_LOGIN}")
    public String SSO_PAGE_LOGIN;

    @Value("${SSO_INTERCEPTOR}")
    public String SSO_INTERCEPTOR;

    @Override
    public TbAdminUser getUserByToken(String token) {

        try {
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            //把json转换为taotaoresult
            TaobaoResult result = TaobaoResult.formatToPojo(json, TbAdminUser.class);
            if (result.getStatus() == 200) {
                TbAdminUser user = (TbAdminUser) result.getData();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }
}