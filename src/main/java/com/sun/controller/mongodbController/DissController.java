/**
 * 
 */
package com.sun.controller.mongodbController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sun.Mongodb.entity.MongoDiss;
import com.sun.Mongodb.entity.MongoSong;

/**
 * @author sunchangjunn 2018年8月6日下午2:58:56
 */
@RestController
@RequestMapping("/diss")
public class DissController {

	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@GetMapping("/getOne")
	public Object getOne() {
		
		return "歌单";
	}

	@GetMapping("/find")
	public void find() {
		Query query = new Query(Criteria.where("song_list.$id").is(1280297));
		List<MongoDiss> dissList=mongoTemplate.find(query, MongoDiss.class);
		System.out.println(JSONObject.toJSONString(dissList));
	}
	@GetMapping("/update")
	public void update() {
		Query query = new Query(Criteria.where("song_list.$id").is(1280297));
		List<MongoDiss> dissList=mongoTemplate.find(query, MongoDiss.class);
		
		for (MongoDiss mongoDiss : dissList) {
			List<MongoSong> songList=mongoDiss.getSong_list();
			for (MongoSong song : songList) {
				if(song.getSong_id()==1L) {
					songList.remove(song);
				}
				
			}
		}
		mongoTemplate.save(dissList);

	}

}
