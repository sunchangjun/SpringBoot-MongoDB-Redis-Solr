/**
 * 
 */
package com.sun.advanced.CallingExternalProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * cmd连续执行用法,中间& 分隔
 * @author sunchangjunn
 * 2018年11月23日上午11:29:14
 */
public class CMDExecutes {
	@Test
	public void test3() {
		System.out.println(executeCmd("cmd /c java"));;
		System.out.println("执行完毕");
	}
	
	
	private Map<String, String> executeCmd(String cmd) {
        Runtime rt = Runtime.getRuntime(); // 运行时系统获取
        Map<String, String> lineMap = new HashMap<String, String>();//存放返回值
        try {
            Process proc = rt.exec(cmd);// 执行命令
            InputStream stderr = proc.getInputStream();//执行结果 得到进程的标准输出信息流
            InputStreamReader isr = new InputStreamReader(stderr);//将字节流转化成字符流
            BufferedReader br = new BufferedReader(isr);//将字符流以缓存的形式一行一行输出
            String line = null;
            while ((line = br.readLine()) != null) { 
                if (!StringUtils.isEmpty(line)) {
                    String[] strLine = line.split(":");
                    if(strLine.length>=2) {
                    	System.out.println(strLine[1].trim());
                        lineMap.put(strLine[0].trim(), strLine[1].trim());
                    }
                    
                }
            }
            br.close();
            isr.close();
            stderr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineMap;
    }

	public void test() throws Exception {
		Process p = Runtime.getRuntime().exec("systeminfo");
		Thread.sleep(3000);
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
