/**
 * 
 */
package com.sun.common.String;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年8月9日下午2:14:22
 */
public class contains {
	

	@Test
	public void test() {
//		String str="<p>生日：1989-07-03</p>";
//		boolean bool=StringUtils.contains(str, "<p> 代表作品");
//		System.out.println(bool);

//		String one ="这一段话能否被匹配";
//
//		boolean bool=StringUtils.contains(one, "一段话");
//		System.out.println(bool);
//		String CSPID="SP_QQYY";
//	 	  UUID uuid = UUID.randomUUID();
//  		  String uuidString=uuid.toString().replace("-", "");
//  		  String movieId=CSPID+uuidString.subSequence(CSPID.length(), uuidString.length());
//     System.out.println(movieId);
//		String two="SP_QQYYedfdb7403d9ef91c6c48272036";
//		System.out.println(movieId.length());
		
		System.out.println(getV4IP());;
	}

	
	public static String getV4IP() {
		String ip = "";
		String chinaz = "http://ip.chinaz.com/";

		String inputLine = "";
		String read = "";
		try {
		URL url = new URL(chinaz);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		while ((read = in.readLine()) != null) {
		inputLine += read;
		}
		System.out.println(inputLine);

		} catch (Exception e) {
		e.printStackTrace();
		}

		Pattern p = Pattern.compile("\\<strong class\\=\"red\">(.*?)\\<\\/strong>");
		Matcher m = p.matcher(inputLine);
		if(m.find()){
		String ipstr = m.group(1);
		System.out.println(ipstr);
		}
		return ip;
		}
	

	
}
