/**
 * 
 */
package com.sun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.sun.util.Shell;

/**
 * @author sunchangjunn 2018年11月23日上午10:45:15
 */
public class TestOne {
	@Test
	public void test() {
		Shell shell=new Shell("47.98.153.144","root","Scj19890606");
		System.out.println(shell.execute("free -m"));
	}

	

}
