package org.reco.media.music.utils;
/**
 * 生成随机字符串
 *<p>Description: </p>
 * @date 2018年4月24日
 */
public class InvitationCode {
	
	/**
	 * 根据传入字符串长度生成对应字符串 重复概率为 62的len次方分之一的几率
	 * @param len 长度
	 * @return
	 */
	public static String generateRandomStr(int len) {
	   //字符源，可以根据需要增减
	   String generateSource = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	   String rtnStr = "";
	   for (int i = 0; i < len; i++) {
	       //循环随机获得当次字符，并移走选出的字符
	       String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
	       rtnStr += nowStr;
	       generateSource = generateSource.replaceAll(nowStr, "");
	   }
	   return rtnStr;
	  }
}