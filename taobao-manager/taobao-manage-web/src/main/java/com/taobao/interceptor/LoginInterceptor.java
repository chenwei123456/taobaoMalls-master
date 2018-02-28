package com.taobao.interceptor;

import com.taobao.common.utils.CookieUtils;
import com.taobao.po.TbAdminUser;
import com.taobao.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenwei on 2017/11/18.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private AdminService adminServiceImpl;

    //在handler执行之前处理
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)throws Exception{
        //返回值决定handler是否执行
        //判断用户是否登陆，从cookie中取token，根据token获取用户信息，调用sso系统的接口。
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN_ADMIN");
        TbAdminUser user = adminServiceImpl.getUserByToken(token);
        if(null==user){
            //调到登录页面
            response.sendRedirect("http://sso.taobaohbu.cn/page/admin/login");
            return false;
        }
        //把用户信息放入Request
        request.setAttribute("adminuser", user);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
