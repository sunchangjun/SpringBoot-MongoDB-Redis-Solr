package org.reco.media.music.utils;

/**
 * 用于返回json数据
 * @author comelk
 * created in 2016年8月23日
 */
public class JsonResult {
	
	private boolean success = true;
	
	private String msg;
	
	private Integer code = 9999;
	
	private Object data;

	public boolean isSuccess() {
		return success;
	}
	

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public JsonResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JsonResult(String  msg) {
		this.success = false;
		this.msg = msg;
	}


	public JsonResult(boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	
	public JsonResult(boolean success,Object data) {
		super();
		this.success = success;
		this.data = data;
	}
	
}
