/**
 * 
 */
package com.sun.advanced.CallingExternalProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author sunchangjunn
 * 2018年11月14日下午6:14:59
 */
public class ProcessBuilderUse {
	
	public static  void  useProcessBuilder() throws IOException, InterruptedException {
		System.out.println("开始");
		ProcessBuilder pb=new  ProcessBuilder(new  ArrayList<String>(Arrays.asList("javac","SortGvf.java")));
		Process process=pb.start();
		if(process.waitFor() != 0) {
			System.exit(1);
		}
		pb=new  ProcessBuilder(new  ArrayList<String>(Arrays.asList("javac","SortGvf")));
		process=pb.start();
		
		InputStream in =process.getInputStream();
		BufferedReader br=new  BufferedReader(new  InputStreamReader(in));
		String  temp = null;
		while((temp = br.readLine()) !=  null) {
			String  str=new  String(temp.getBytes("utf-8"),"utf-8");
			System.out.println(str);
		}
		
		System.out.println(">>>>>>>>>完毕");
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		useProcessBuilder();
	}

}
