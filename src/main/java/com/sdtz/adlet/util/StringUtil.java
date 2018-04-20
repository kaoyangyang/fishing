//接口类型：互亿无线触发短信接口，支持发�?�验证码短信、订单�?�知短信等�??
// 账户注册：请通过该地�?�?通账户http://sms.ihuyi.com/register.html
// 注意事项�?
//�?1）调试期间，请用默认的模板进行测试，默认模板详见接口文档�?
//�?2）请使用APIID（查看APIID请登录用户中�?->验证码短�?->产品总览->APIID）及 APIkecom.sdtz.adlet.util接入互亿无线短信接口参�?�使用，客户可根据实际需要自行编写；

package com.sdtz.adlet.util;

import java.security.MessageDigest;

public class StringUtil {
	public static String str;
	public static final String EMPTY_STRING = "";

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 转换字节数组�?16进制字串
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

}
