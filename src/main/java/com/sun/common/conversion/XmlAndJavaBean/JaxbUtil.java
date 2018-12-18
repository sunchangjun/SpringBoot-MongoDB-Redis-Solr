/**
 * 
 */
package com.sun.common.conversion.XmlAndJavaBean;

/**
 * @author sunchangjunn
 * 2018年12月13日下午2:52:52
 */
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.apache.commons.beanutils.BeanUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Unmarshaller;
//import com.alipayeco.medicalinscore.common.Global;

public class JaxbUtil {//工具类

    /**
     * java对象转换为xml文件
     * @param xmlPath  xml文件路径
     * @param load    java对象.Class
     * @return    xml文件的String
     * @throws JAXBException    
     */
    public static String beanToXml(Object obj, Class<?> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
//        marshaller.setProperty(Marshaller.JAXB_ENCODING, Global.ENCODING);
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }

    /**
     * xml文件配置转换为对象
     * @param xmlPath  xml文件路径
     * @param load    java对象.Class
     * @return    java对象
     * @throws JAXBException    
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xmlPath, Class<T> load) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xmlPath));
    }
    
    /** 
     * JavaBean转换成xml 
     * 默认编码UTF-8 
     * @param obj 
     * @param writer 
     * @return  
     */
    public static String convertToXml(Object obj) {
        //       return convertToXml(obj, "UTF-8");  
        return convertToXml(obj, "UTF-8");
    }

    /** 
     * JavaBean转换成xml 
     * @param obj 
     * @param encoding  
     * @return  
     */
    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    /** 
     * JavaBean转换成xml去除xml声明部分 
     * @param obj 
     * @param encoding  
     * @return  
     */
    public static String convertToXmlIgnoreXmlHead(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    
    

    /** 
     * xml转换成JavaBean 
     * @param xml 
     * @param c 
     * @return 
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }
    
    /**
     *将xml对象转换为javaBean
     * @param clazz
     * @param xml
     * @return
     */
    public static Object parseXmlToBean(Class clazz,String xml){
        if(xml!=null&&xml!=""){
            Field[] fields = clazz.getDeclaredFields();
            List<Field> fieldList = new ArrayList<Field>();
            for (Field fie : fields) {
                if (fie.isAnnotationPresent(XmlElementAnno.class)) {
                    fieldList.add(fie);
                }
            }
            try {
                StringReader read = new StringReader(xml);
                InputSource source = new InputSource(read);
                //创建一个新的SAXBuilder
                SAXBuilder sb = new SAXBuilder();
                Document doc = sb.build(source);
                //取的根元素
                Element root = doc.getRootElement();
                Object object = clazz.newInstance();
                if(!fieldList.isEmpty()){
                    for (Field field : fieldList) {
                        Element child = root.getChild(field.getName());
                        if(child!=null){
                            BeanUtils.setProperty(object, field.getName(), child.getValue());
                        }
                    }
                }
                return object;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 将javaBean转换为xml对象
     * @param clazz
     * @param bean
     * @return
     */
    public static String parseBeanToXml(Class clazz,Object bean){
        StringWriter sw = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            sw = new StringWriter();
            //该值默认为false,true则不会创建即头信息,即<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.marshal(bean, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

}