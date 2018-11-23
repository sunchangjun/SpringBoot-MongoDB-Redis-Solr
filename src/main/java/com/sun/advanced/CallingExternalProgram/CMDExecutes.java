/**
 * 
 */
package com.sun.advanced.CallingExternalProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * cmd连续执行用法,中间& 分隔
 * @author sunchangjunn
 * 2018年11月23日上午11:29:14
 */
public class CMDExecutes {
//	@Test
	public void test() throws Exception {
		Process p = Runtime.getRuntime().exec("cmd.exe /c dir");
		
		int status = p.waitFor();
		System.out.println(status);
		print(p);
		
	 
		
	
	}
	
	public  void  print(Process process) {
		try {
			int status = process.waitFor();
			System.out.println(status);
			
			InputStream in = process.getInputStream();
	      	BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				String str = new String(temp.getBytes("utf-8"), "utf-8");
				System.out.println(str);
			}
			System.out.println(">>>>>>>>>完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public  void test2() {
	
//	        String command1="cmd /c net start mysql";
//	        String command2="cmd /c net stop mysql";
//	        String command3="cmd /c start e:/blog";
//	        String command4="cmd /c start f:/视频";
	        String command5="cmd /c G: & mkdir oneTo & cd oneTo & curl -O https://blog.csdn.net/w405722907/article/details/78610503";
	        String[] arrs={/*command1,command2,command3,command4,*/command5};
	        try {
	        for (String string : arrs) {
	        	Process p = Runtime.getRuntime().exec(string);
	        	System.out.println(JSONObject.toJSONString(p));
	        	print(p);
	        }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
