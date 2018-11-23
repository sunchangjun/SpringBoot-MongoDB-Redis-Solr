/**
 * 
 */
package com.sun.advanced.webService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

//import hk.reco.media.music.iptv.hndx.service.HndxCallbackService;

public class WebServiceCallTest {

	private static final QName SERVICE_NAME = new QName("iptv", "CSPResponseService");
	private static final QName PORT_NAME = new QName("iptv", "CSPResponse");

	public static void main(String[] args){
		Service service = Service.create(SERVICE_NAME);
		service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, "http://localhost:8080/hndx-run/soap/wthx?wsdl");
//		HndxCallbackService stub = service.getPort(PORT_NAME,HndxCallbackService.class);
//		stub.ResultNotify("输入参数1", "111", "1538965299", 0, "1111");
	}
}
