/**
 * 
 */
package com.sun.common.trycatch;

import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年8月21日下午4:55:47
 */
public class TryCatchTest {
	
	@Test
	public  void  test() {
		
		try {
			Integer  i =3;
			if(i == 3 ) {
				System.out.println("ceshi"+i);
				try {
					test2();
				} catch (Exception e) {
					System.out.println("222222222222");
					if(e instanceof RuntimeException ) {
						System.out.println("11111111");
					}
					
				}
			
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return  ;
	}
	
	public void  test2() {  
		
		
		
			throw new  RuntimeException();

	}

}
