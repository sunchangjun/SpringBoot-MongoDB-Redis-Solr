package com.sun.common;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class properties {

    /**第一种:*/
//    @Test
//    public void testPro() throws IOException {
//        Resource resource = new ClassPathResource("/application.properties");
//        Properties props = PropertiesLoaderUtils.loadProperties(resource);
//        System.out.println(props.getProperty("spring.data.solr.host"));
//    }
    @Test /**第二种:*/
    public void testPro2() throws IOException {
        InputStream insss = Object.class.getResourceAsStream("/application.properties");
        Properties pss = new Properties();
        pss.load(insss);
        System.out.println(pss.getProperty("spring.data.solr.host"));

    }
}
