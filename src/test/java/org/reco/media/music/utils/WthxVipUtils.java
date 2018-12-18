/**
 * 
 */
package org.reco.media.music.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Alex Tang
 * 2018年10月29日
 * desc:
 */
public class WthxVipUtils {
	
	/**
     * 设置vip信息
     * @param vipStr
     * @return
     */
    public static List<Integer> getVipList(String vipStr){
    	List<Integer> vipList = new ArrayList<Integer>();
    	if(StringUtils.isNotBlank(vipStr)){
    		String tempStr = vipStr.replace("v", "");
    		String[] vips = tempStr.split(",");
    		for(String vip : vips){
    			try {
					vipList.add(Integer.parseInt(vip));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
    		}
    	}
    	return vipList;
    }
}
