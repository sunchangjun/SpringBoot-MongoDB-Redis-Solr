/**
 * 
 */
package com.sun.common.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

/**
 * @author sunchangjunn 2018年11月19日下午5:33:52
 */
public class FileUtil {

	@Test
	public void test() {
//		exitFile("G:/ccc.txt");
		exitFiledirectory("G:/ddd/eee/t");
	}

	/* 判断文件是否存在,不存在则创建 */
	public void exitFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	/*判断目录是否存在,不存在则创建*/
	public void exitFiledirectory(String fileDirPath) {
		File file = new File(fileDirPath);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//不存在");
			file.mkdir();
		} else {
			System.out.println("//目录存在");
		}
	}
	
	  /**
     * 删除某个文件夹下的所有文件夹和文件
     */
    public static boolean deletefile(String delpath)
                    throws FileNotFoundException, IOException {
            try {

                    File file = new File(delpath);
                    if (!file.isDirectory()) {
                            System.out.println("1");
                            file.delete();
                    } else if (file.isDirectory()) {
                            System.out.println("2");
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                                    File delfile = new File(delpath + "\\" + filelist[i]);
                                    if (!delfile.isDirectory()) {
                                            System.out.println("path=" + delfile.getPath());
                                            System.out.println("absolutepath="
                                                            + delfile.getAbsolutePath());
                                            System.out.println("name=" + delfile.getName());
                                            delfile.delete();
                                            System.out.println("删除文件成功");
                                    } else if (delfile.isDirectory()) {
                                            deletefile(delpath + "\\" + filelist[i]);
                                    }
                            }
                            file.delete();

                    }

            } catch (FileNotFoundException e) {
                    System.out.println("deletefile()   Exception:" + e.getMessage());
            }
            return true;
    }
    
    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
            try {

                    File file = new File(filepath);
                    if (!file.isDirectory()) {
                            System.out.println("文件");
                            System.out.println("path=" + file.getPath());
                            System.out.println("absolutepath=" + file.getAbsolutePath());
                            System.out.println("name=" + file.getName());

                    } else if (file.isDirectory()) {
                            System.out.println("文件夹");
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                                    File readfile = new File(filepath + "\\" + filelist[i]);
                                    if (!readfile.isDirectory()) {
                                            System.out.println("path=" + readfile.getPath());
                                            System.out.println("absolutepath="
                                                            + readfile.getAbsolutePath());
                                            System.out.println("name=" + readfile.getName());

                                    } else if (readfile.isDirectory()) {
                                            readfile(filepath + "\\" + filelist[i]);
                                    }
                            }

                    }

            } catch (FileNotFoundException e) {
                    System.out.println("readfile()   Exception:" + e.getMessage());
            }
            return true;
    }

}
