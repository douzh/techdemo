package com.iteedu.ssoboot.modules.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.iteedu.ssoboot.modules.sys.entity.UserEntity;


/**
 * 类ShiroUtils的功能描述:
 * Shiro工具类
 */
public class ShiroUtils {

	/**  加密算法 */
	public final static String algorithmName = "SHA-256";
	/**
	 * 加密散列次数
	 */
	public static final int hashIterations= 1;

	public static String EncodeSalt(String password, String salt) {
		return new SimpleHash(algorithmName, password, salt, hashIterations).toString();
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static UserEntity getUserEntity() {
		return (UserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	public static String getUserId() {
		return getUserEntity().getId();
	}
	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}

}
