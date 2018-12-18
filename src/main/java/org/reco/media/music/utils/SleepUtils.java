package org.reco.media.music.utils;
/**
 * 延时工具
 * @author zhangsl
 * @date 2018年9月26日
 */
public class SleepUtils {

	/**
	 * 睡眠一定毫秒时间
	 * @param ms
	 */
	public static void sleep(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e1) {
		}
	}
}
