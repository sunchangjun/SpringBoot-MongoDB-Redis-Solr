package com.sun.util.Configuration;

import java.io.InputStream;
import java.util.Properties;

import java.io.IOException;
import java.util.Properties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ReadProperties {


    public void testPro() throws IOException{
//        Resource resource = new ClassPathResource("/conf/application.properties");
        Resource resource = new ClassPathResource("/application.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        System.out.println(props.getProperty("spring.data.solr.host"));
    }

    /**第二种:*/
    public void testPro2() throws IOException {
        InputStream insss = Object.class.getResourceAsStream("/application.properties");
        Properties pss = new Properties();
        pss.load(insss);
        System.out.println(pss.getProperty("spring.data.solr.host"));

    }



}
