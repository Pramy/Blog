package com.pramy.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class StringUtil {

	public static String EncoderByMd5(String str){
		String password=null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();	
			password = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}
	
	public static boolean isEmpty(Object object) {
		if (object == null || object.toString().equals("")) {
			return true;
		}
		return false;
	}
}
