package com.sun.common.String;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringSub {

    @Test
    public void test(){
        String str="未找到歌曲信息:10086";
        String st= StringUtils.substringAfter(str,"未找到歌曲信息:");
        System.out.println(st);

        String str1= "我喜欢:你:";
        System.out.println(StringUtils.substringBetween(str1,":",":"));

    }

}
