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
 * 2018年7月6日
 * desc:
 */
@Document(collection = "mongo_album")
public class MongoAlbum {
	@Id
	private Long album_id;
	private String album_name;
	private String album_pic;
	private String singer_name;
	private String public_time;
	@DBRef
	private List<MongoSong> song_list;
	public String getPublic_time() {
		return public_time;
	}
	public void setPublic_time(String public_time) {
		this.public_time = public_time;
	}
	public Long getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(Long album_id) {
		this.album_id = album_id;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public String getAlbum_pic() {
		return album_pic;
	}
	public void setAlbum_pic(String album_pic) {
		this.album_pic = album_pic;
	}
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}
	public List<MongoSong> getSong_list() {
		return song_list;
	}
	public void setSong_list(List<MongoSong> song_list) {
		this.song_list = song_list;
	}
}
