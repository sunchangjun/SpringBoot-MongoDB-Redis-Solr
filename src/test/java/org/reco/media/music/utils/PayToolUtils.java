package org.reco.media.music.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class PayToolUtils {
	//订单未支付
	public static final int ORDER_STATUS_NOTPAY = 0;
	//订单已支付
	public static final int ORDER_STATUS_PAYED = 1;
	
	//订单2小时没支付就超时
	public static final int ORDER_OVERTIME = 2*3600*1000;
	
	public static final String WX_PAY_APP_ID = "wxbcee39328eb54db0";
	public static final String WX_PAY_MCH_ID = "1497038972";
	public static final String WX_PAY_SUB_MCH_ID = "1502233771";
	public static final String WX_PAY_KEY = "scwxhx1234SCWXHX1234scwxhx1234SC";
	public static final String WX_NOTIFY_URL = SettingUtils.getCommonSetting("wx.callback.url");
	//获取微信支付二维码url
	public static final String WX_SERVER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//查询微信订单信息url
	public static final String WX_QUERY_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	public static final String SERVER_IP = "182.150.63.48";
    
    /** 
     * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。 
     * @return boolean 
     */
    public static boolean isTenpaySign(SortedMap<Object, Object> packageParams, String apiKey) {  
        StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            String v = (String)entry.getValue();  
            if(!"sign".equals(k) && null != v && !"".equals(v)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
          
        sb.append("key=" + apiKey);  
          
        //算出摘要  
        String mysign = Md5Utils.encode(sb.toString()).toLowerCase();  
        String tenpaySign = ((String)packageParams.get("sign")).toLowerCase();  
          
        return tenpaySign.equals(mysign);  
    }  
    
    /** 
     * 取出一个指定长度大小的随机正整数. 
     *  
     * @param length int 设定所取出随机数的长度。length小于11 
     * @return int 返回生成的随机数。 
     */  
    public static int buildRandom(int length) {  
        int num = 1;  
        double random = Math.random();  
        if (random < 0.1) {  
            random = random + 0.1;  
        }  
        for (int i = 0; i < length; i++) {  
            num = num * 10;  
        }  
        return (int) ((random * num));  
    }
    
    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }
    
    /**
     * 
     * @param xmlStr
     * @return
     */
    public static String createSign(String xmlStr, String API_KEY) throws Exception{
    	Map<String, String> xmlMap = null;
		try {
			xmlMap = XmlUtils.xmlToMap(xmlStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	String [] keys = xmlMap.keySet().toArray(new String[xmlMap.size()]);
    	Arrays.sort(keys);
		StringBuffer stringSignTemp = new StringBuffer();
		for (String p : keys) {
			if (p.equals("sign"))
				continue;
			if (!StringUtils.isBlank(stringSignTemp.toString())) {
				stringSignTemp.append("&");
			}
			stringSignTemp.append(p).append("=").append(xmlMap.get(p));
		}
		stringSignTemp.append("&key=").append(API_KEY);
		return Md5Utils.encode(stringSignTemp.toString()).toUpperCase();
    }
    
    public static void main(String[] args) throws Exception{
    	String xmlStr = "<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid><out_trade_no><![CDATA[1409811653]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id><time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><coupon_fee><![CDATA[10]]></coupon_fee><coupon_count><![CDATA[1]]></coupon_count><coupon_type><![CDATA[CASH]]></coupon_type><coupon_id><![CDATA[10000]]></coupon_id><coupon_fee><![CDATA[100]]></coupon_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
		System.out.println(PayToolUtils.createSign(xmlStr,PayToolUtils.WX_PAY_KEY));
    }
}
