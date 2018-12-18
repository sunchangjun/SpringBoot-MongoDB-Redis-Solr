/**
 * 
 */
package org.reco.media.music.utils;


/**
 * Alex Tang
 * 2018年7月27日
 * desc:qq图片构造工具类
 */
public class QqImageUtils {
	public static final String default_singer_image = "http://imgcache.qq.com/music/photo/singer_300/singerpic_0_null.jpg";
	public static final String default_singer_prefix = "http://y.gtimg.cn/music/photo_new/T001R";
	public static final String default_album_prefix = "http://y.gtimg.cn/music/photo_new/T002R";
	public static final String default_mv_prefix = "http://puui.qpic.cn/qqvideo_ori/0/";
	public static final String default_wthx_prefix = "http://pic.recomusic.net/images/";
	public static final String default_tag = "M000";
	public static final String default_suffix = ".jpg";
	public static final String default_wthx_singer = "\\\\192.168.3.100\\qqmusic\\images\\u\\v\\d6ae9841b6b5417b94d44971723737c2.jpg";
	
	/**
	 * 拼接字符串
	 * @param strs
	 * @return
	 */
	private static String buildString(String... strs){
		StringBuilder result = new StringBuilder();
		for(String str : strs){
			result.append(str);
		}
		return result.toString();
	}
	
	
	/**
	 * 获取歌手120图片
	 * @param singerMid 歌手mid
	 * @param isDefaultImage 是否默认图片0否1是
	 * @return
	 */
	public static String getSingerImg120x120(String singerMid,Integer isDefaultImage){
		if(singerMid==null || isDefaultImage == null){
    		return null;
    	}
    	if(isDefaultImage.equals(1)){
    		return QqImageUtils.default_singer_image;
    	}else{
    		return buildString(QqImageUtils.default_singer_prefix,"120x120",
    				QqImageUtils.default_tag,singerMid,QqImageUtils.default_suffix);
    	}
	}
	
	/**
	 * 获取歌手150图片
	 * @param singerMid 歌手mid
	 * @param isDefaultImage 是否默认图片0否1是
	 * @return
	 */
	public static String getSingerImg150x150(String singerMid,Integer isDefaultImage){
		if(singerMid==null || isDefaultImage == null){
    		return null;
    	}
    	if(isDefaultImage.equals(1)){
    		return QqImageUtils.default_singer_image;
    	}else{
    		return buildString(QqImageUtils.default_singer_prefix,"150x150",
    				QqImageUtils.default_tag,singerMid,QqImageUtils.default_suffix);
    	}
	}
	
	/**
	 * 获取歌手300图片
	 * @param singerMid 歌手mid
	 * @param isDefaultImage 是否默认图片0否1是
	 * @return
	 */
	public static String getSingerImg300x300(String singerMid,Integer isDefaultImage){
		if(singerMid==null || isDefaultImage == null){
    		return null;
    	}
    	if(isDefaultImage.equals(1)){
    		return QqImageUtils.default_singer_image;
    	}else{
    		return buildString(QqImageUtils.default_singer_prefix,"300x300",
    				QqImageUtils.default_tag,singerMid,QqImageUtils.default_suffix);
    	}
	}
	
	/**
	 * 获取歌手500图片
	 * @param singerMid 歌手mid
	 * @param isDefaultImage 是否默认图片0否1是
	 * @return
	 */
	public static String getSingerImg500x500(String singerMid,Integer isDefaultImage){
		if(singerMid==null || isDefaultImage == null){
    		return null;
    	}
    	if(isDefaultImage.equals(1)){
    		return QqImageUtils.default_singer_image;
    	}else{
    		return buildString(QqImageUtils.default_singer_prefix,"500x500",
    				QqImageUtils.default_tag,singerMid,QqImageUtils.default_suffix);
    	}
	}
	
	/**
	 * 获取专辑120图片
	 * @param singerMid 专辑mid
	 * @return
	 */
	public static String getAlbumImg120x120(String albumMid){
		if(albumMid==null){
    		return null;
    	}
    	return buildString(QqImageUtils.default_album_prefix,"120x120",
				QqImageUtils.default_tag,albumMid,QqImageUtils.default_suffix);
	}
	
	/**
	 * 获取专辑150图片
	 * @param singerMid 专辑mid
	 * @return
	 */
	public static String getAlbumImg150x150(String albumMid){
		if(albumMid==null){
    		return null;
    	}
    	return buildString(QqImageUtils.default_album_prefix,"150x150",
				QqImageUtils.default_tag,albumMid,QqImageUtils.default_suffix);
	}
	
	/**
	 * 获取专辑300图片
	 * @param singerMid 专辑mid
	 * @return
	 */
	public static String getAlbumImg300x300(String albumMid){
		if(albumMid==null){
    		return null;
    	}
    	return buildString(QqImageUtils.default_album_prefix,"300x300",
				QqImageUtils.default_tag,albumMid,QqImageUtils.default_suffix);
	}
	
	/**
	 * 获取专辑500图片
	 * @param singerMid 专辑mid
	 * @return
	 */
	public static String getAlbumImg500x500(String albumMid){
		if(albumMid==null){
    		return null;
    	}
    	return buildString(QqImageUtils.default_album_prefix,"500x500",
				QqImageUtils.default_tag,albumMid,QqImageUtils.default_suffix);
	}
	
	/**
	 * 获取MV228图片
	 * @param mvVid
	 * @return
	 */
	public static String getMvImg228x128(String mvVid){
		if(mvVid==null){
    		return null;
    	}
    	return buildString(QqImageUtils.default_mv_prefix,mvVid,"_228_128/0");
	}
	
	/**
	 * 获取MV360图片
	 * @param mvVid
	 * @return
	 */
	public static String getMvImg360x204(String mvVid){
		if(mvVid==null){
    		return null;
    	}
    	return buildString(QqImageUtils.default_mv_prefix,mvVid,"_360_204/0");
	}
	
	/**
	 * 获取MV496图片
	 * @param mvVid
	 * @return
	 */
	public static String getMvImg496x280(String mvVid){
		if(mvVid==null){
    		return null;
    	}
    	return buildString(QqImageUtils.default_mv_prefix,mvVid,"_496_280/0");
	}
	
	/**
	 * 获取mv分类、歌手分类、歌单分类小图片
	 * @param id
	 * @param type 1 mv/歌手  ，2歌单
	 * @return
	 */
	public static String getWthxImgSmall(Integer wthxId,int type){
		if(wthxId == null){
			return null;
		}
		String context = null;
		if(type == 1){
			context = "index/small/";
		}else if(type == 2){
			context = "tags/small/";
		}else{
			return null;
		}
		return buildString(QqImageUtils.default_wthx_prefix,context,wthxId.toString(),".png");
	}
	
	/**
	 * 获取mv分类、歌手分类、歌单分类中图片
	 * @param id
	 * @param type 1 mv/歌手  ，2歌单分类
	 * @return
	 */
	public static String getWthxImgMiddle(Integer wthxId,int type){
		if(wthxId == null){
			return null;
		}
		String context = null;
		if(type == 1){
			context = "index/middle/";
		}else if(type == 2){
			context = "tags/middle/";
		}else{
			return null;
		}
		return buildString(QqImageUtils.default_wthx_prefix,context,wthxId.toString(),".png");
	}
	
	/**
	 * 获取mv分类、歌手分类、歌单分类大图片
	 * @param id
	 * @param type 1 mv/歌手  ，2歌单分类
	 * @return
	 */
	public static String getWthxImgBig(Integer wthxId,int type){
		if(wthxId == null){
			return null;
		}
		String context = null;
		if(type == 1){
			context = "index/big/";
		}else if(type == 2){
			context = "tags/big/";
		}else{
			return null;
		}
		return buildString(QqImageUtils.default_wthx_prefix,context,wthxId.toString(),".png");
	}
	
	/**
	 * 根据level获取index、tags图片
	 * @param level
	 * @param wthxId
	 * @param type 1 mv/歌手  ，2歌单分类
	 * @return
	 * /home/pic/images/(index/tags)
	 */
	public static String getWthxImgByLevel(Integer level,Integer wthxId,int type){
		level = (level == null)?0:level;
		String img = "";
		switch (level) {
		case 1:
			img = QqImageUtils.getWthxImgSmall(wthxId, type);
			break;
		case 2:
			img = QqImageUtils.getWthxImgMiddle(wthxId, type);
			break;
		case 3:
			img = QqImageUtils.getWthxImgBig(wthxId, type);
			break;
		default:
			img = QqImageUtils.getWthxImgMiddle(wthxId, type);
			break;
		}
		return img;
	}
	
	/**
	 * 电视版图片
	 * @param wthxId
	 * @param type
	 * @return
	 */
	public static String getWthxImgByTv(Integer wthxId,int type){
		if(wthxId == null){
			return null;
		}
		String context = null;
		if(type == 1){
			context = "index/tv/";
		}else if(type == 2){
			context = "tags/tv/";
		}else{
			return null;
		}
		return buildString(QqImageUtils.default_wthx_prefix,context,wthxId.toString(),".png");
	}
	
	/**
	 * 根据level获取歌手图片
	 * @param level
	 * @param singerMid
	 * @param isDefaultImage
	 * @return
	 */
	public static String getSingerImgByLevel(Integer level,String singerMid,Integer isDefaultImage){
		level = (level == null)?0:level;
		String img = "";
		switch (level) {
		case 1:
			img = QqImageUtils.getSingerImg120x120(singerMid, isDefaultImage);
			break;
		case 2:
			img = QqImageUtils.getSingerImg300x300(singerMid, isDefaultImage);
			break;
		case 3:
			img = QqImageUtils.getSingerImg500x500(singerMid, isDefaultImage);
			break;
		default:
			img = QqImageUtils.getSingerImg300x300(singerMid, isDefaultImage);
			break;
		}
		return img;
	}
	
	/**
	 * 根据level获取专辑图片
	 * @param level
	 * @param albumMid
	 * @return
	 */
	public static String getAlbumImgByLevel(Integer level,String albumMid){
		level = (level == null)?0:level;
		String img = "";
		switch (level) {
		case 1:
			img = QqImageUtils.getAlbumImg120x120(albumMid);
			break;
		case 2:
			img = QqImageUtils.getAlbumImg300x300(albumMid);
			break;
		case 3:
			img = QqImageUtils.getAlbumImg500x500(albumMid);
			break;
		default:
			img = QqImageUtils.getAlbumImg300x300(albumMid);
			break;
		}
		return img;
	}
	
	/**
	 * 根据level获取MV图片
	 * @param level
	 * @param mvVid
	 * @return
	 */
	public static String getMvImgByLevel(Integer level,String mvVid){
		level = (level == null)?0:level;
		String img = "";
		switch (level) {
		case 1:
			img = QqImageUtils.getMvImg228x128(mvVid);
			break;
		case 2:
			img = QqImageUtils.getMvImg360x204(mvVid);
			break;
		case 3:
			img = QqImageUtils.getMvImg496x280(mvVid);
			break;
		default:
			img = QqImageUtils.getMvImg360x204(mvVid);
			break;
		}
		return img;
	}
	
	public static void main(String[] args){
		System.out.println(QqImageUtils.getSingerImg500x500("002IDUsp3TrbyA", 0));
	}
}
