/**
 * 
 */
package com.sun.common.picUril;

import java.io.FileInputStream;

import com.sun.common.UrlConnectionHelper;



/**
 * @author sunchangjunn
 * 2018年8月22日上午10:42:09
 */
public class DownPic {
	
	public static void main(String[] args) throws Exception {
//		EnvSupport.setEnv(EnvSupport.Env.develop);
//		String temp = "http://puui.qpic.cn/qqvideo_ori/0/m0027k01sls_%d_%d/0";
		//http://y.gtimg.cn/music/photo/radio/track_radio_199_%d_%d.jpg
		String temp = "http://puui.qpic.cn/qqvideo_ori/0/m0027k01sls_%d_%d/0";

		for(int i=0;i<=50;i++){
			System.out.println(i);
			for(int j=0;j<=50;j++){
				String url = String.format(temp, i,j);
				System.out.println("i="+i+",j="+j);
				try{
					UrlConnectionHelper.httpDownload(url, "d:/temp/"+i+"_"+j+"ffffff.jpg");
//					FileInputStream fis = new FileInputStream("d:/temp/ffffff.jpg");
					FileInputStream fis = new FileInputStream("d:/temp/"+i+"_"+j+"ffffff.jpg");
					if(fis.available()!=5093){
						System.out.println(url);
					}
					fis.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				//Thread.sleep(50);
			}
		}
	}

}
