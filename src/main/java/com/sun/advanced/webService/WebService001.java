/**
 * 
 */
package com.sun.advanced.webService;

/**
 * @author sunchangjunn
 * 2018年11月21日下午2:05:04
 */


import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @WebService接口建立webservice服务,Endpoint.publish()发布服务
 * @author sunchangjunn
 * 2018年11月21日下午2:32:59
 */
@WebService
public class WebService001 {

    public void testWebService(String name) {
        System.out.println("Test sucessfully, you input name is :" + name);
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/webService/test", new WebService001());
        System.out.println("webService pulish completed!");
    }
}
