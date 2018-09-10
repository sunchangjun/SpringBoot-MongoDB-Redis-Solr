package com.sun.Mongodb.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 推荐内容
 * @author zhangsl
 * @date 2018-06-21
 */
@Document(collection = "mongo_reco")
public class MongoReco {
	
	@Id
	private String reco_id;//推荐内容id,在mongo库中为_id
	private Long create_time;//创建时间
//	@DBRef
//	private List<MongoRecoType> reco_datas;//推荐的内容
	private String date;
	private int version;
	
	public String getReco_id() {
		return reco_id;
	}
	public void setReco_id(String reco_id) {
		this.reco_id = reco_id;
	}
	public Long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
//	public List<MongoRecoType> getReco_datas() {
//		return reco_datas;
//	}
//	public void setReco_datas(List<MongoRecoType> reco_datas) {
//		this.reco_datas = reco_datas;
//	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
