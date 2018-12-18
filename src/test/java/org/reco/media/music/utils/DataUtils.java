/**
 * 
 */
package org.reco.media.music.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Alex Tang
 * 2018年5月8日
 * desc:
 */
public class DataUtils {
	public static String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
