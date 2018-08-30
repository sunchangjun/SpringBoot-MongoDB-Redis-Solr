
package com.sun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sunchangjunn
 * 2018年8月30日上午11:08:46
 */
@Controller
@RequestMapping("/html")
public class HtmlController {
	
	
	@RequestMapping("/login")
	public String login() {
		System.out.println("跳转登录");
		return "login2";
	}

}
