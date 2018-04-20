package com.sdtz.adlet.util;

import java.util.Date;
import java.util.Random;

public class GenerateUniqueId {

	/**
	 * 生成唯一标示符
	 * 由两部分组成：1、系统当前时间的毫秒数，2、随机生成7个小写字母
	 * @return
	 */
	public static String generateUniqueId(){
		String[] letters={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		Random rand=new Random();
		String result="";
		for(int i=0;i<7;i++){
			int randnum=rand.nextInt(26);
			result+=letters[randnum];
		}
		Date date=new Date();
		long longTimes=date.getTime();
		return result+longTimes;
	}
}
