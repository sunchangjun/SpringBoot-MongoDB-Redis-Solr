/**
 * 
 */
package com.sun.common.conversion.XmlAndJavaBean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * @author sunchangjunn 2018年12月13日下午3:13:04
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xmlresult")
@XmlType(propOrder = {})
public class PostResult {

	@XmlElement(name="msgid")
	private String msgId;
	
	@XmlElement
	private Integer state;
	
	@XmlElement
	private String msg;
	
	@XmlElement(name="info")
	private Info info;



	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(propOrder = { })
	 static class Info {
			@XmlElement(name = "cdn_id")
			private String cdnId;
			
			@XmlElement(name = "site_id")
			private String siteId;
			
			@XmlElement(name = "cp_id")
			private Integer cpId;
			
			@XmlElement(name = "mg_asset_type")
			private String mgAssetType;
			
			@XmlElement(name = "mg_asset_id")
			private String mgAssetId;
			
			@XmlElement(name = "mg_part_id")
			private String mgPartId;
			
			@XmlElement(name = "mg_file_id")
			private String mgFileId;



			public String getCdnId() {
				return cdnId;
			}

			public void setCdnId(String cdnId) {
				this.cdnId = cdnId;
			}

			public String getSiteId() {
				return siteId;
			}

			public void setSiteId(String siteId) {
				this.siteId = siteId;
			}

			public Integer getCpId() {
				return cpId;
			}

			public void setCpId(Integer cpId) {
				this.cpId = cpId;
			}

			public String getMgAssetType() {
				return mgAssetType;
			}

			public void setMgAssetType(String mgAssetType) {
				this.mgAssetType = mgAssetType;
			}

			public String getMgAssetId() {
				return mgAssetId;
			}

			public void setMgAssetId(String mgAssetId) {
				this.mgAssetId = mgAssetId;
			}

			public String getMgPartId() {
				return mgPartId;
			}

			public void setMgPartId(String mgPartId) {
				this.mgPartId = mgPartId;
			}

			public String getMgFileId() {
				return mgFileId;
			}

			public void setMgFileId(String mgFileId) {
				this.mgFileId = mgFileId;
			}
		
	}



}
