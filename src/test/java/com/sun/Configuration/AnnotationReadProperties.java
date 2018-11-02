package com.sun.Configuration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 注解读取配置文件
 */

@Configuration
//@ComponentScan("com.xzp.ch1_2_3.ch2.el")
@PropertySource("classpath:/application.properties")
public class AnnotationReadProperties {
    @Value("${spring.data.solr.host}")
    private String url;

    @Test
    public void test(){
        System.out.println(url);
    }
}
