/**
 * 
 */
package com.sun.common.file;

import java.io.File;

import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年12月14日下午5:30:57
 */
public class FileName {
	@Test
	public void test() {
		File file= new File("G:\\444.jpg");
		File newfile=new File(file.getParent()+File.separator+"testUpdateName.jpg");//创建新名字的抽象文件
		file.renameTo(newfile);
	}

}
