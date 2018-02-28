package com.taobao.sso.controller;

import com.taobao.common.utils.ExceptionUtil;
import com.taobao.common.utils.TaobaoResult;
import com.taobao.po.TbAdminUser;
import com.taobao.sso.srevice.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by chenwei on 2017/10/23.
 */

@Controller
@RequestMapping("/user/admin")
public class AdminController {

    @Autowired
    private AdminService userService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody

    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {

        TaobaoResult result = null;

        //参数有效检验
        if(StringUtils.isBlank(param)){
            result = TaobaoResult.build(400,"校验内容不能为空");
        }
        if(type == null){
            result = TaobaoResult.build(400,"校验内容类型不能为空");
        }
        if (type != 1 && type != 2 && type != 3 ) {
            result = TaobaoResult.build(400, "校验内容类型错误");
        }

        //校验出错
        if(null != result){
            if(null != callback){
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            } else {
                return result;
                 }
            }

           // 调用服务
        try{
            result = userService.checkData(param,type);
        }catch(Exception e){
            result = TaobaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        if (null != callback) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        } else {
            return result;
            }
        }

        //创建服务
        @RequestMapping(value = "/register",method= RequestMethod.POST)
        @ResponseBody
        public TaobaoResult createUser(TbAdminUser user){
            try{
                TaobaoResult result = userService.createUser(user);
                return result;
            } catch (Exception e){
                e.printStackTrace();
                return TaobaoResult.build(500,ExceptionUtil.getStackTrace(e));
            }

            //接收表单 ，包含用户名和密码
            //接收表单，包含用户名和密码
            @RequestMapping(value="/login",method=RequestMethod.POST)
            @ResponseBody
            public  TaobaoResult userLogin(String username,String password,
                    HttpServletRequest  request,HttpServletResponse response){
                try {
                    TaobaoResult result = userService.userLogin(username, password,request,response);
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                    return TaobaoResult.build(500, ExceptionUtil.getStackTrace(e));
                }
            }

            //接收token调用service返回用户信息

            @RequestMapping("/token/{token}")
            @ResponseBody
            public Object getUserByToken(@PathVariable  String token,String callback){

            }

            }
        }
    }

}
