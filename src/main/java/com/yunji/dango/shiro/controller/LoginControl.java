package com.yunji.dango.shiro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginControl{
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @RequestMapping({"/login.do"})
    @ResponseBody
    public String Login(HttpServletRequest request){
        String errorException = (String)request.getAttribute("shiroLoginFailure");
        logger.info("登录信息: " + errorException);
        String errorString = null;
        if (UnknownAccountException.class.getName().equals(errorException)) {
            errorString = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(errorException)) {
            errorString = "用户名/密码错误";
        } else if (ExcessiveAttemptsException.class.getName().equals(errorException)) {
            errorString = "登录失败多次，账户锁定,请五分钟后重试";
        } else if ("jCaptcha.error".equals(errorException)) {
            errorString = "验证码错误，请重新输入";
        } else if (errorException != null) {
            errorString = "其他错误：" + errorException;
        }
        if (errorString != null) {
            request.getSession().setAttribute("err", errorString);
        }
        return "login_err";
    }
}
