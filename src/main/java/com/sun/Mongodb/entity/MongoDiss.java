/**
 * 
 */
package com.sun.Mongodb.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Alex Tang
 * 2018年6月21日
 * desc:
 */
@Document(collection = "mongo_diss")
public class MongoDiss {
	@Id
	private Long diss_id;//歌单id
	private Long res_id;//资源id
	private String diss_name;//歌单名称
	private String pic_url;//歌单图片
	private Long listen_num;//播放次数
	private String introduction;//歌单介绍
	private List<String> tags;//标签
	@DBRef
	private List<MongoSong> song_list;
	
	public List<MongoSong> getSong_list() {
		return song_list;
	}
	public void setSong_list(List<MongoSong> song_list) {
		this.song_list = song_list;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Long getDiss_id() {
		return diss_id;
	}
	public void setDiss_id(Long diss_id) {
		this.diss_id = diss_id;
	}
	public Long getRes_id() {
		return res_id;
	}
	public void setRes_id(Long res_id) {
		this.res_id = res_id;
	}
	public String getDiss_name() {
		return diss_name;
	}
	public void setDiss_name(String diss_name) {
		this.diss_name = diss_name;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public Long getListen_num() {
		return listen_num;
	}
	public void setListen_num(Long listen_num) {
		this.listen_num = listen_num;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diss_id == null) ? 0 : diss_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MongoDiss other = (MongoDiss) obj;
		if (diss_id == null) {
			if (other.diss_id != null)
				return false;
		} else if (!diss_id.equals(other.diss_id))
			return false;
		return true;
	}
}
