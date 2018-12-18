/**
 * 
 */
package com.sun.common.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sunchangjunn
 * 2018年12月13日下午1:46:54
 */
public class HttpServletRequestDemo {
	@RequestMapping(value="/SubCmsApi/Feedback.action",method=RequestMethod.POST)
	public void feedback(String cmsresult,HttpServletResponse resp,HttpServletRequest req){
	
		try {		
			InputStreamReader is=new InputStreamReader(req.getInputStream(),"UTF-8");
		    BufferedReader br = new BufferedReader(is);
			StringBuffer sb=new StringBuffer();
			String line = " ";
			while ((line = br.readLine()) != null){
				    sb.append(line);
			}
			System.out.println(sb.toString());
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}



}
