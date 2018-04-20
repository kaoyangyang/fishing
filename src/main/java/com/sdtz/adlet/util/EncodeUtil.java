package com.sdtz.adlet.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.util.UUID;

//import org.apache.tomcat.util.codec.binary.Base64;

public class EncodeUtil {
	
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	/**
	 * 将传入的src加密处理
	 * @param src 明文字符串
	 * @return 加密后的字符串结果
	 * @throws Exception 
	 */
	public static String md5(String str) throws Exception{
		//将字符串信息采用MD5处理
		str=str+"lyadlet";
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] output = md.digest(str.getBytes());
		//将MD5处理结果利用Base64转成字符串
		String s = 
		  Base64.encodeBase64String(output);
		return s;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(md5("123456"));
//		System.out.println(md5("12354654676dfdfdfdf"));
		System.out.println(createId());
		System.out.println(createId());
	}
	
}
