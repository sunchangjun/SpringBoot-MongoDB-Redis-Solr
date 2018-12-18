package org.reco.media.music.utils;

import org.apache.http.client.CookieStore;

public class HttpResult {
	private String content;
	private CookieStore cookieStore;
	
	public HttpResult(String content) {
		this.content = content;
	}
	
	public HttpResult(String content, CookieStore cookieStore) {
		this.content = content;
		this.cookieStore = cookieStore;
	}
	
	public CookieStore getCookieStore() {
		return cookieStore;
	}
	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
