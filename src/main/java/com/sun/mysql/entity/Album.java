package com.sun.mysql.entity;

import java.util.List;
/**
 * 专辑
 * @author zhangsl
 * 2018年2月28日
 */
public class Album {
	
	private String album_desc;
	private long album_id;
	private String album_mid;
	private String album_name;
	private String album_translator_name;
	private String company_name;
	private int display;
	private int listen_num;
	private long public_time;
	private long singer_id;
	private String singer_name;
	private String song_id_list;
	private List<QmSong> songlist;
	private String album_pic;
	private String album_pic_150x150;
	private String album_pic_300x300;
	private String album_pic_500x500;
	private String album_pic_local;
	private String album_pic_150x150_local;
	private String album_pic_300x300_local;
	private String album_pic_500x500_local;
	
	public String getAlbum_desc() {
		return album_desc;
	}
	public long getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(long album_id) {
		this.album_id = album_id;
	}
	public void setAlbum_desc(String album_desc) {
		this.album_desc = album_desc;
	}
	public String getAlbum_mid() {
		return album_mid;
	}
	public void setAlbum_mid(String album_mid) {
		this.album_mid = album_mid;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public String getAlbum_translator_name() {
		return album_translator_name;
	}
	public void setAlbum_translator_name(String album_translator_name) {
		this.album_translator_name = album_translator_name;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	public int getListen_num() {
		return listen_num;
	}
	public void setListen_num(int listen_num) {
		this.listen_num = listen_num;
	}
	public long getPublic_time() {
		return public_time;
	}
	public void setPublic_time(long public_time) {
		this.public_time = public_time;
	}
	public long getSinger_id() {
		return singer_id;
	}
	public void setSinger_id(long singer_id) {
		this.singer_id = singer_id;
	}
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}
	public List<QmSong> getSonglist() {
		return songlist;
	}
	public void setSonglist(List<QmSong> songlist) {
		this.songlist = songlist;
	}
	public String getSong_id_list() {
		return song_id_list;
	}
	public void setSong_id_list(String song_id_list) {
		this.song_id_list = song_id_list;
	}
	public String getAlbum_pic() {
		return album_pic;
	}
	public void setAlbum_pic(String album_pic) {
		this.album_pic = album_pic;
	}
	public String getAlbum_pic_150x150() {
		return album_pic_150x150;
	}
	public void setAlbum_pic_150x150(String album_pic_150x150) {
		this.album_pic_150x150 = album_pic_150x150;
	}
	public String getAlbum_pic_300x300() {
		return album_pic_300x300;
	}
	public void setAlbum_pic_300x300(String album_pic_300x300) {
		this.album_pic_300x300 = album_pic_300x300;
	}
	public String getAlbum_pic_500x500() {
		return album_pic_500x500;
	}
	public void setAlbum_pic_500x500(String album_pic_500x500) {
		this.album_pic_500x500 = album_pic_500x500;
	}
	public String getAlbum_pic_local() {
		return album_pic_local;
	}
	public void setAlbum_pic_local(String album_pic_local) {
		this.album_pic_local = album_pic_local;
	}
	public String getAlbum_pic_150x150_local() {
		return album_pic_150x150_local;
	}
	public void setAlbum_pic_150x150_local(String album_pic_150x150_local) {
		this.album_pic_150x150_local = album_pic_150x150_local;
	}
	public String getAlbum_pic_300x300_local() {
		return album_pic_300x300_local;
	}
	public void setAlbum_pic_300x300_local(String album_pic_300x300_local) {
		this.album_pic_300x300_local = album_pic_300x300_local;
	}
	public String getAlbum_pic_500x500_local() {
		return album_pic_500x500_local;
	}
	public void setAlbum_pic_500x500_local(String album_pic_500x500_local) {
		this.album_pic_500x500_local = album_pic_500x500_local;
	}
}
