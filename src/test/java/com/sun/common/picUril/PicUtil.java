/**
 * 
 */
package com.sun.common.picUril;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author sunchangjunn
 * 2018年8月22日下午4:17:33
 */
public class PicUtil {
	
	 
	/**
	 * 
	 * @param fileName  D:\\pic\\track_radio_315_10_2.jpg
	 * @param outPatch  D:\\pic\\456.jpg
	 * @param w
	 * @param h
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public static void resize(String fileName,String outPatch,int w, int h) throws IOException {  
		   
		   File file = new File(fileName);// 读入文件  
		   Image  img = ImageIO.read(file);      // 构造Image对象  

	        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
	        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
	        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图   
	        File destFile = new File(outPatch);  
	        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
	        // 可以正常实现bmp、png、gif转jpg  
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
	        encoder.encode(image); // JPEG编码  
	        out.close();  
	    } 
	
	public static void main(String[] args) throws IOException {
		resize("D:\\\\pic\\\\track_radio_315_10_2.jpg", "D:\\pic\\123.jpg", 128, 128);
	}
    
}
