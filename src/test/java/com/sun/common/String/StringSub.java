package com.sun.common.String;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringSub {

    @Test
    public void test(){
//        String str="未找到歌曲信息:10086";
//        String st= StringUtils.substringAfter(str,"未找到歌曲信息:");
//        System.out.println(st);
//
//        String str1= "我喜欢:你:";
//        System.out.println(StringUtils.substringBetween(str1,":",":"));
//        
//        String date="2018-12-21";
//        System.out.println(date.substring(0, 4));
//        
//        System.out.println(buildCDATAString("歌手"));
//        System.out.println("hndx_70835_1544598501".length());
    	System.out.println(Long.MAX_VALUE);
    	System.out.println("9000000000000000000L".length());
    	
    }
    
    private static String buildCDATAString(String str) {
    	String temp="<![CDATA[%s]]>";
    	String newString=String.format(temp, str);   	
    	return newString;
    }

}
