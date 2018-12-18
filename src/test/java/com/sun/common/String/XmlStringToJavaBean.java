/**
 * 
 */
package com.sun.common.String;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.sun.common.conversion.XmlAndJavaBean.JaxbUtil;
import com.sun.common.conversion.XmlAndJavaBean.PostResult;

/**
 * @author sunchangjunn
 * 2018年12月13日下午2:50:25
 */
public class XmlStringToJavaBean {
	@Test
	public void test() {
		String xmlStr="<xmlresult><msgid>CE670107C36B4C4481723886D88EE303</msgid><state>1</state><msg>xml import ok</msg><info><cdn_id>e6dd1c6a8019899b4d0fce8c106ef9e9</cdn_id><site_id>bb82862e260a46259a85561fd8e41be9</site_id><cp_id>HW</cp_id><mg_asset_type>1</mg_asset_type><mg_asset_id>ser_0100000000612150000002811099</mg_asset_id><mg_part_id></mg_part_id><mg_file_id></mg_file_id></info></xmlresult>";
		PostResult postResult=	 JaxbUtil.converyToJavaBean( xmlStr,PostResult.class);
		System.out.println(JSONObject.toJSONString(postResult));
	}
	
	
}
