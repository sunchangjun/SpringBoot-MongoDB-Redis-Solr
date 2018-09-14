package com.sun.common.Http;


import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionTest {

    @Test
    public void test(HttpServletRequest request){
       HttpSession session =  request.getSession();
    }

}
