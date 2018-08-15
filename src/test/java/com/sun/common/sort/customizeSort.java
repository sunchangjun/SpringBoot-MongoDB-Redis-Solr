/**
 * 
 */
package com.sun.common.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sun.mysql.entity.QmSong;

/**
 * @author sunchangjunn
 * 2018年8月10日上午11:14:43
 */
public class customizeSort {
	
	public void test() {
		List<QmSong> list=new ArrayList<QmSong>();
		
	       Collections.sort(list, new Comparator<QmSong>(){
	            /*
	             * int compare(Person p1, Person p2) 返回一个基本类型的整型，
	             * 返回负数表示：p1 小于p2，
	             * 返回0 表示：p1和p2相等，
	             * 返回正数表示：p1大于p2
	             */
	            public int compare(QmSong p1, QmSong p2) {
	                //按照Person的年龄进行升序排列
	                if(p1.getSingerId() > p2.getSingerId()){
	                    return 1;
	                }
	                if(p1.getSingerId() == p2.getSingerId()){
	                    return 0;
	                }
	                return -1;
	            }
	        });
	}

}
