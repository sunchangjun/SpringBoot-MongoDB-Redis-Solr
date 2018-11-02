package com.sun.util.Configuration;

import org.springframework.beans.factory.annotation.Value;

public class ReadPropertiesByAnnotation {

    @Value("${spring.data.solr.host}")
    private String url;






}
