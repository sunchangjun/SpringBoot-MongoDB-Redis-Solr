package com.sun.common.QRCode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;



/**
 * java使用谷歌zxing包生成二维码
 * @author Administrator
 *
 */
public final class CreateQRCode {
	 private static final int BLACK = 0xFF000000;
	    private static final int WHITE = 0xFFFFFFFF;

	    private CreateQRCode() {}


	    public static BufferedImage toBufferedImage(BitMatrix matrix) {
	        int width = matrix.getWidth();
	        int height = matrix.getHeight();
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
	            }
	        }
	        return image;
	    }


	    public static BufferedImage writeToFile(BitMatrix matrix, String format, File file)
	            throws IOException {
	        BufferedImage image = toBufferedImage(matrix);
	        if (!ImageIO.write(image, format, file)) {
	            throw new IOException("Could not write an image of format " + format + " to " + file);
	        }
	        
	        return image;
	    }


	    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
	            throws IOException {
	        BufferedImage image = toBufferedImage(matrix);
	        if (!ImageIO.write(image, format, stream)) {
	            throw new IOException("Could not write an image of format " + format);
	        }
	    }
	    
	    
	    /**
	     * 根据内容，生成指定宽高、指定格式的二维码图片
	     *
	     * @param text   内容
	     * @param width  宽
	     * @param height 高
	     * @param format 图片格式
	     * @return 生成的二维码图片路径
	     * @throws Exception
	     */
	    public static String generateQRCode(String text, int width, int height, String format) throws Exception {
	        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
	        String pathName = "D:/new.png";
	        File outputFile = new File(pathName);
	        BufferedImage bufferedImage= CreateQRCode.writeToFile(bitMatrix, format, outputFile);
	        //将生成的二维码上传图片服务器
	        File file = new File(pathName);
	        String returnUrl = "";//AliOSSUtil.save(file,DateUtils.getCurrentStringTime_format_yy_MM_dd()+"_"+".png", null);
	        System.out.println(returnUrl);
	        return pathName;
	    }







}
