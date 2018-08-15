/**
 * 
 */
package com.sun.common.if_while_switch;

import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年8月9日下午5:18:20
 */
public class switchT {
	@Test
	public void test() {
//		int i=1;
//		String str="你好";
//		switch (i) {
//		case 1:
//			str="你好吗";
//			break;
//		case 2:
//			str="你好啊";
//			break;
//			
//		}
//		System.out.println(str);
		String coun="中国";
		String area="200";
		
		if("中国".equals(coun)){
			switch (Integer.valueOf(area)) {
			case 200:
				coun="内地";
				break;
			case 2:									
				coun="港台";
				break;
			}
			System.out.println(coun);
//			qmSinger.setCountry(coun);
		}
	}

}
