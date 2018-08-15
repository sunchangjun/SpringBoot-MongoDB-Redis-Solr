package com.sun.common.encoder;

import java.io.UnsupportedEncodingException;

public class testEncoder {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String name=java.net.URLEncoder.encode("{\"albumlib\":{\"method\":\"get_album_by_tags\",\"param\":{\"area\":1,\"company\":-1,\"genre\":-1,\"type\":-1,\"year\":-1,\"sort\":2,\"get_tags\":1,\"sin\":0,\"num\":20,\"click_albumid\":0},\"module\":\"music.web_album_library\"}}");
		System.out.println(name);


	}

}
