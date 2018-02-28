package com.taobao.portal.interceptor;

import com.taobao.common.utils.CookieUtils;
import com.taobao.po.TbUser;
import com.taobao.portal.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenwei on 2017/12/19.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;

    //在handler执行之前处理
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //返回值决定handler是否执行
        //判断用户是否登陆，从cookie中取token，根据token获取用户信息，调用sso系统的接口。
        String token = CookieUtils.getCookieValue( httpServletRequest, "TT_TOKEN");
        TbUser user = userService.getUserByToken(token);
        if(user==null){
            //调到登陆页面
            httpServletResponse.sendRedirect(userService.SSO_INTERCEPTOR+userService.SSO_PAGE_LOGIN
                    +"?redirect="+userService.TAOBAO_MAIN+ httpServletRequest.getRequestURI());
            return false;
        }
        //把用户信息放到Request
        httpServletRequest.setAttribute("user",user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //返回modelAndView之后，响应用户操作
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
