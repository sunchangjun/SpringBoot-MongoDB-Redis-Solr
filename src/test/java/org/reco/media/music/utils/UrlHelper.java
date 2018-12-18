package org.reco.media.music.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 从URL解析文件名工具
 * @author zhangsl
 */
public class UrlHelper {

	public static String getFileNameFromUrl(String url) {
		// http://y.gtimg.cn/music/photo_new/T001R120x120M000000nmQ1v0JGExN.jpg
		// http://isure.stream.qqmusic.qq.com/C2000028kjnJ3SZW7O.m4a?guid=12347284&vkey=FD8012B125BCF69781527D528CD8E1C3AD1E78049EA8926F258A4BF18A7E2582A36939CD0275901F0BDA78D65773760B8361471A6463F118&uin=&fromtag=50
		int qpos = url.lastIndexOf("?");
		url = (qpos == -1) ? url : url.substring(0, qpos);
		int pos = url.lastIndexOf("/");
		return url.substring(pos + 1);
	}

	public static void main(String[] args) {
		String name = getFileNameFromUrl("http://isure.stream.qqmusic.qq.com/C2000028kjnJ3SZW7O.m4a?guid=12347284&vkey=FD8012B125BCF69781527D528CD8E1C3AD1E78049EA8926F258A4BF18A7E2582A36939CD0275901F0BDA78D65773760B8361471A6463F118&uin=&fromtag=50");
		System.out.println(name);
	}
	
	public static String getRequestDomain(HttpServletRequest request){
		String domain = null;
		try {
			String headPort = request.getHeader("X-Real-Port"); 
			String headProtocol = request.getHeader("X-Real-Protocol");
			
			int port = (StringUtils.isBlank(headPort))?request.getServerPort():Integer.parseInt(headPort);
			String protocol = (StringUtils.isBlank(headPort))?request.getScheme():headProtocol;
			
			domain = protocol + "://" + request.getServerName();
			if((port != 80) && (port != 443)){
				domain +=  ":" + port;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return domain;
	}

}
