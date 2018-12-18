package org.reco.media.music.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
/**
 * 环境支持
 * @author zhangsl
 * @date 2018-07-13
 */
public class EnvSupport {

	@Autowired
	private Environment env;
	private static String profile = null;
	public static final String PROFILE_KEY = "spring.profiles.active";
	
	public enum Env {
		develop, product
	}

	/**
	 * web容器起来时,初始化环境
	 */
	@PostConstruct
	public void initSystem() {
		String[] profiles = env.getActiveProfiles();
		for (String p : profiles) {
			profile = p;
			System.setProperty(PROFILE_KEY, profile);
			break;
		}
	}

	/**
	 * main方法等运行时,初始化环境
	 * @param env
	 */
	public static void setEnv(Env env) {
		profile = env.name();
		System.setProperty(PROFILE_KEY, profile);
	}

	/**
	 * 获取环境
	 * @return
	 */
	public static String getEnv() {
		if(profile == null){
			throw new IllegalStateException("环境状态异常, profile not set, try set 'EnvSupport.setEnv(Env.develop);' before your code start");
		}
		return profile;
	}
}
