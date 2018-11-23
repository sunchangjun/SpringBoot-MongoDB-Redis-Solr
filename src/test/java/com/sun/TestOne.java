/**
 * 
 */
package com.sun;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.sun.util.Shell;
import java.io.File;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
/**
 * @author sunchangjunn 2018年11月23日上午10:45:15
 */
public class TestOne {
	@Test
	public void test() {

		 getMemInfo();
	        System.out.println();
	        getDiskInfo();
	}
	

	    
	    public static void getDiskInfo()
	    {
	        File[] disks = File.listRoots();
	        for(File file : disks)
	        {
	            System.out.print(file.getPath() + "    ");
	            System.out.print("空闲未使用 = " + file.getFreeSpace() / 1024 / 1024 + "M" + "    ");// 空闲空间
	            System.out.print("已经使用 = " + file.getUsableSpace() / 1024 / 1024 + "M" + "    ");// 可用空间
	            System.out.print("总容量 = " + file.getTotalSpace() / 1024 / 1024 + "M" + "    ");// 总空间
	            System.out.println();
	        }
	    }
	    
	    public static void getMemInfo()
	    {
	        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	        System.out.println("Total RAM：" + mem.getTotalPhysicalMemorySize() / 1024 / 1024 + "MB");
	        System.out.println("Available　RAM：" + mem.getFreePhysicalMemorySize() / 1024 / 1024 + "MB");
	    }

	

}
