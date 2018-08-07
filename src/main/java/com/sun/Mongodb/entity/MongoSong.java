package com.sun.Mongodb.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 歌曲实体
 * @author zhangsl
 * @date 2018-06-11
 */
@Document(collection = "mongo_song")
public class MongoSong {
	
	@Id
	private Long song_id;//歌手id主键,在mongo库中为_id
	private Long res_id;//统一资源id
	private String song_name;//歌曲名称
	private Long singer_id;//歌手ID
	private String singer_name;//歌手名字
	private Long album_id;//专辑ID
	private String language;//语言
	private Long mv_id;//相关联的mv_id
	private Integer unique;//去重后的唯一的
	private Integer is_only;//是否独家
	private List<Integer> vips;//需要的权限
	private List<String> tags;
	private List<Integer> radio_ids;
	private List<Long> diss_ids;//歌单ID
	private String album_mid;
	private String album_pic;
	private Integer type;
	private String poster_url;//海报图片
	private Integer audio_mode;//电台标记
	
	public String getPoster_url() {
		return poster_url;
	}
	public void setPoster_url(String poster_url) {
		this.poster_url = poster_url;
	}
	public Integer getIs_only() {
		return is_only;
	}
	public void setIs_only(Integer is_only) {
		this.is_only = is_only;
	}
	public List<Long> getDiss_ids() {
		return diss_ids;
	}
	public void setDiss_ids(List<Long> diss_ids) {
		this.diss_ids = diss_ids;
	}
	public Long getSong_id() {
		return song_id;
	}
	public void setSong_id(Long song_id) {
		this.song_id = song_id;
	}
	public Long getRes_id() {
		return res_id;
	}
	public void setRes_id(Long res_id) {
		this.res_id = res_id;
	}
	public String getSong_name() {
		return song_name;
	}
	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}
	public Long getSinger_id() {
		return singer_id;
	}
	public void setSinger_id(Long singer_id) {
		this.singer_id = singer_id;
	}
	public Long getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(Long album_id) {
		this.album_id = album_id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Long getMv_id() {
		return mv_id;
	}
	public void setMv_id(Long mv_id) {
		this.mv_id = mv_id;
	}
	public Integer getUnique() {
		return unique;
	}
	public void setUnique(Integer unique) {
		this.unique = unique;
	}
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}
	public List<Integer> getVips() {
		return vips;
	}
	public List<Integer> getRadio_ids() {
		return radio_ids;
	}
	public void setRadio_ids(List<Integer> radio_ids) {
		this.radio_ids = radio_ids;
	}
	public void setVips(List<Integer> vips) {
		this.vips = vips;
	}
	public String getAlbum_mid() {
		return album_mid;
	}
	public void setAlbum_mid(String album_mid) {
		this.album_mid = album_mid;
	}
	public String getAlbum_pic() {
		return album_pic;
	}
	public void setAlbum_pic(String album_pic) {
		this.album_pic = album_pic;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getAudio_mode() {
		return audio_mode;
	}
	public void setAudio_mode(Integer audio_mode) {
		this.audio_mode = audio_mode;
	}
	@Override
	public String toString() {
		return "MongoSong [song_id=" + song_id + ", res_id=" + res_id
				+ ", song_name=" + song_name + ", singer_id=" + singer_id
				+ ", singer_name=" + singer_name + ", album_id=" + album_id
				+ ", language=" + language + ", mv_id=" + mv_id + ", unique="
				+ unique + ", is_only=" + is_only + ", vips=" + vips
				+ ", tags=" + tags + ", radio_ids=" + radio_ids + ", diss_ids="
				+ diss_ids + ", album_mid=" + album_mid + ", album_pic="
				+ album_pic + ", type=" + type + ", poster_url=" + poster_url
				+ ", audio_mode=" + audio_mode + "]";
	}
}
