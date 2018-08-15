/**
 * 
 */
package com.sun.common.pojo;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.sun.mysql.entity.QmSong;

/**
 * @author sunchangjunn
 * 2018年8月8日下午1:40:41
 */
public class Benutils {
	@Test
	public  void  test() {
		QmSong qmSong = new QmSong();
		qmSong.setAlbum_mid("aaa");
		qmSong.setArea("2");
		qmSong.setSingerId(null);
		QmSong qmSong1 = new QmSong();
		qmSong1.setSingerId(1L);
		BeanUtils.copyProperties(qmSong, qmSong1);
		System.out.println(JSONObject.toJSONString(qmSong1));
		
		
	}

}
