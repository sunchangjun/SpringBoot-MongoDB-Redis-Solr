package com.sun.common.bean;

import java.util.ArrayList;
/**
 * 对象内套对象,内部对象注意某些要求必须是static类
 * @author sunchangjunn
 * 2018年8月9日下午4:47:01
 */
public class QqSongAlbum {
	int code;
	Albumlib albumlib;
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Albumlib getAlbumlib() {
		return albumlib;
	}

	public void setAlbumlib(Albumlib albumlib) {
		this.albumlib = albumlib;
	}

	 public class Albumlib {
		AlbumlibData data;
			
		int code;
		
		
	

		public AlbumlibData getData() {
			return data;
		}

		public void setData(AlbumlibData data) {
			this.data = data;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

	}

	 public static class AlbumlibData {
			int total;
		ArrayList<Album> list;
		
		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public ArrayList<Album> getList() {
			return list;
		}

		public void setList(ArrayList<Album> list) {
			this.list = list;
		}

	}

	 public static class Album {
		long  album_id;
		String album_mid;
		String album_name;
		String public_time;
		int week;
		int year;
		public long getAlbum_id() {
			return album_id;
		}
		public void setAlbum_id(long album_id) {
			this.album_id = album_id;
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

		public String getPublic_time() {
			return public_time;
		}

		public void setPublic_time(String public_time) {
			this.public_time = public_time;
		}

		public int getWeek() {
			return week;
		}
		public void setWeek(int week) {
			this.week = week;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
	
		
	

	}



}
