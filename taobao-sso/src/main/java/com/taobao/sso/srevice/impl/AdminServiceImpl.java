package com.taobao.sso.srevice.impl;

import com.taobao.common.utils.CookieUtils;
import com.taobao.common.utils.JsonUtils;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.mapper.TbAdminUserMapper;
import com.taobao.order.dao.JedisClient;
import com.taobao.po.TbAdminUser;
import com.taobao.po.TbAdminUserExample;
import com.taobao.po.TbAdminUserExample.Criteria;
import com.taobao.sso.srevice.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by chenwei on 2017/10/25.
 */
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TbAdminUserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_ADMIN_SESSION_KEY}")
    private String REDIS_USER_ADMIN_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;


    @Override
    public TaobaoResult checkData(String content, Integer type) {

        TbAdminUserExample example = new TbAdminUserExample();
        Criteria criteria = example.createCriteria();

        //对数据进行校验
        if (1 == type) {
            //用户名校验
            criteria.andUsernameEqualTo(content);
        } else if (2 == type) {
            //电话校验
            criteria.andPhoneEqualTo(content);

        } else {
            //邮件校验
            criteria.andPhoneEqualTo(content);
        }
        //执行查询
        List<TbAdminUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return TaobaoResult.ok(true);
        }
        return TaobaoResult.ok(false);
    }

    @Override
    public TaobaoResult createUser(TbAdminUser user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        //msd5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        userMapper.insert(user);
        return TaobaoResult.ok();
    }

    //用户登录
    @Override
    public TaobaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        TbAdminUserExample example = new TbAdminUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);

        List<TbAdminUser> list = userMapper.selectByExample(example);
        //如果没有该用户名
        if (null == list || list.size() == 0) {
            return TaobaoResult.build(400, "用户名或密码错误");
        }
        TbAdminUser user = list.get(0);
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return TaobaoResult.build(400, "用户名或密码错误");
        }
        //生成token
        String token = UUID.randomUUID().toString();
        //把用户信息写入redis
        user.setPassword(null);
        jedisClient.set(REDIS_USER_ADMIN_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        //设置过期时间
        jedisClient.expire(REDIS_USER_ADMIN_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);

        //添加写cookie的逻辑，cookie的有效期是关闭浏览器失效
        CookieUtils.setCookie(request, response, "TT_TOKEN_ADMIN", token);

        return TaobaoResult.ok(token);
    }

    @Override
    public TaobaoResult getUserByToken(String token) {
//根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_ADMIN_SESSION_KEY + ":" + token);
        if (StringUtils.isBlank(json)) {
            return TaobaoResult.build(400, "会话过期，请重新登录");
        }
        //更新过期时间
        jedisClient.expire(REDIS_USER_ADMIN_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        //返回用户信息
        return TaobaoResult.ok(JsonUtils.jsonToPojo(json, TbAdminUser.class));
    }
}