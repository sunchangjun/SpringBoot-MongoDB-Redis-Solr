/**
 * 
 */
package com.sun.common.hash;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import org.junit.Test;
/**
 * @author sunchangjunn
 * 2018年12月12日上午10:50:35
 */
public class FileHash {




/**
 * Created by JunZhu on 2017/3/3.
 */



    public static byte[] createChecksum(String filename) throws Exception {
        InputStream fis =  new FileInputStream(filename);
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("SHA1");//Java Security name (such as "SHA", "MD5", and so on).
        int numRead;

        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    public static String getMD5Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        String result = "";
        for (int i=0; i < b.length; i++) {
            result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }
    @Test
    public void test() {
    	try {
			System.out.println(getMD5Checksum("G:\\1532929.mp4").length());
			System.out.println("3167858c0f270cec097d215889ba5b2c".length());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}


