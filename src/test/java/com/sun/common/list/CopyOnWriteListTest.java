/**
 * 
 */
package com.sun.common.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年11月5日下午4:36:41
 */
public class CopyOnWriteListTest {
//	@Test
	public void test() {
		
		List<Integer> list= new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		List<Integer> coa =new  CopyOnWriteArrayList<Integer>(list);
		for (Integer integer : coa) {
			if(integer == 3) {
				coa.remove(integer);
			}
		}
		
		list.clear();
		list.addAll(coa);
		System.out.println(list.size());
		
	}

	@Test
	public void test2() {
		List<Integer>  list=null;
		List<Integer> coa =new  CopyOnWriteArrayList<Integer>(list);
		
		System.out.println(coa);
	}
}
