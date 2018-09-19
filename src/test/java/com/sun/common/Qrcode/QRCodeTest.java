package com.sun.common.Qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.common.QRCode.CreateQRCode;
import com.sun.common.jedis.JedisUtils;
import org.junit.Test;

import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * 二维码工具测试
 */
public class QRCodeTest {


    @Test
    public void test() {
            try {
                OutputStream stream = new FileOutputStream("D:\\File\\new.png");
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix bm = qrCodeWriter.encode("https://www.baidu.com/", BarcodeFormat.QR_CODE, 300, 300);
                MatrixToImageWriter.writeToStream(bm, "png", stream);
            }catch(Exception e){

            }
        }
    }