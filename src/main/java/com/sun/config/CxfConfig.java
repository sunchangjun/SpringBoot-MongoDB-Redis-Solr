///**
// * 
// */
//package com.sun.config;
//
///**
// * @author sunchangjunn
// * 2018年11月26日上午10:20:00
// */
//import org.apache.cxf.Bus;
//import org.apache.cxf.bus.spring.SpringBus;
//import org.apache.cxf.jaxws.EndpointImpl;
//import org.apache.cxf.transport.servlet.CXFServlet;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import com.sun.service.WebServicedemo;
//import com.sun.service.Impl.WebServicedemoImpl;
//import javax.xml.ws.Endpoint;
//
//
//
//import javax.xml.ws.Endpoint;
//
//import org.apache.cxf.Bus;
//import org.apache.cxf.jaxws.EndpointImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
///**
// * cxf配置
// * 
// * 
// * @author：WangYuanJun
// * @date：2017年12月19日 下午9:38:24
// */
//@Configuration
//public class CxfConfig {
//
//    @Autowired
//    private Bus bus;
//
//    @Autowired
//    private WebServicedemo webServicedemo;
//
//    @Bean
//    public Endpoint endpoint(){
//        EndpointImpl endpoint = new EndpointImpl(bus, webServicedemo);
//        endpoint.publish("/TestService");
//        return endpoint;
//    }
//}
//
////@Configuration
////public class CxfConfig {
////
////    @Bean
////    public ServletRegistrationBean dispatcherServlet() {
////        return new ServletRegistrationBean(new CXFServlet(),"/demo/*");
////    }
////    @Bean(name = Bus.DEFAULT_BUS_ID)
////    public SpringBus springBus() {
////        return new SpringBus();
////    }
////    @Bean
////    public WebServicedemo demoService() {
////        return new WebServicedemoImpl();
////    }
////    @Bean
////    public Endpoint endpoint() {
////        EndpointImpl endpoint = new EndpointImpl(springBus(), demoService());
////        endpoint.publish("/api");
////        return endpoint;
////    }
////    
////    
////}
