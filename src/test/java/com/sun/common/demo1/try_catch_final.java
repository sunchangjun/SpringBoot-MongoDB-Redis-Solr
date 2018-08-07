package com.sun.common.demo1;



public class try_catch_final {
	
	public void test() throws Exception {
	
	}
	
	public static void main(String[] args) {
		int i = 0;
		System.out.println(i);
		try {
			for ( ;i < 10; i++) {
				if(i==5) {
					throw new  Exception();
				}
				System.out.println("___"+i);
				
			}
		} catch (Exception e) {
			System.out.println("catch"+i);
			i++;
			main(args);
			
		}
	
		
	}

}
