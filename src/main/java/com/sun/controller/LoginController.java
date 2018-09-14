package com.sun.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户登录
 * 1,用户登录踢掉前一个
 * 2,单点登录
 */
public class LoginController {

    public void login(HttpServletRequest request){
        /**
         * 实现踢出先登录用户的实现逻辑:
         * 用户控制权状态,0,1
         * 用户一登录,拿到控制权redis变为1,存入session,每次redis记录和session比较一致,然后操作一切正常,
         * 用户二登录,redis从1变为0,存入session0,用户一进行新的操作时redis和session不一致,所以被踢出
         *
         * 另外:security配置也能实现
         */
        HttpSession session= request.getSession();
        session.setAttribute("用户名","登录状态");
    }

}
