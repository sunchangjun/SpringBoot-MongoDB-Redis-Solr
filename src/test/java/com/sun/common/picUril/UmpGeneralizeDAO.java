/**
 * 
 */
package com.sun.common.picUril;

/**
 * 读取图片大小
 * @author sunchangjunn
 * 2018年8月22日下午3:18:58
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;


import javax.imageio.ImageIO;
public class UmpGeneralizeDAO {
	public static void main(String[] args) {
		File file = new File("D:\\pic\\track_radio_315_10_2.jpg");
		FileChannel fc = null;
		if(file.exists() && file.isFile()){
		try {
		FileInputStream fs = new FileInputStream(file);
		fc = fs.getChannel();
		System.out.println(fc.size() + "-----fc.size()");
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
		System.out.println(file.length() + "-----file.length  B");
		System.out.println(file.length() * 1024 + "-----file.length  kb");
		BufferedImage bi = null;
		try {
		bi = ImageIO.read(file);
		} catch (IOException e) {
		e.printStackTrace();
		}

		int width = bi.getWidth();
		int height = bi.getHeight();

		System.out.println("宽：像素-----" + width + "高：像素"  + height);


		}
	

}
