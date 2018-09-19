package com.sun.common;

import com.sun.common.down.LinkHelper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;


/**
 * 从互联网上下载MP3,mv
 */
public class DownFileFromUrl {
    @Test
    public void test(){
        try {
        String str=  "https://www.aliyun.com/jiaocheng/554695.html";
        String st= StringUtils.substringAfterLast(str,".");
        System.out.println(st);

        }catch(Exception e){
           e.printStackTrace();
        }
    }

}
