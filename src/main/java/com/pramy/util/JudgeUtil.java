package com.pramy.util;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class JudgeUtil {



	public static boolean isEmail(String str) {
		String regex = "^\\p{Alnum}+([-_.]\\p{Alnum}+)*@\\p{Alnum}+.\\p{Alnum}{2,4}$";
		return str.matches(regex);
	}

	public static boolean isName(String str) {
		String regex = "^([\u4E00-\u9FA5]{2,}(.[\u4E00-\u9FA5]+)*)|(\\p{Alpha}{3,}(.\\p{Alpha}+)*)$";
		return str.matches(regex);
	}

	public static boolean isNumber(String str) {
		String regex = "^\\d{6,10}$";
		return str.matches(regex);
	}

	public static boolean isMobile(String str) {
		String regex = "^1[34578]\\d{9}$";
		return str.matches(regex);
	}

	public static boolean isTelephone(String str) {
		String regex = "^(\\d{2,4}-)?\\d{7,8}$";
		return str.matches(regex);
	}

	public static boolean isDate(String str) {
		String regex = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";
		return str.matches(regex);
	}

	// 从^开始向前否定预阅览，不改变位置指针,如果不不符合？！后面的表达式则开始从先前匹配好的位置开始判断后面的表达式
	// ps:（我这里是从^）
	public static boolean isPassword(String str) {
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)(?!\\p{Punct}+$)\\p{Alnum}\\p{Graph}{5,15}$";
		return str.matches(regex);
	}
	public static boolean isUserName(String str) {
		String regex = "^\\p{Alnum}{6,16}$";
		return str.matches(regex);
	}

	public static boolean isChinese(String str) {
		String regex = "^[\u4E00-\u9FA5]{2,}$";
		return str.matches(regex);
	}

	public static boolean isDigit(String str) {
		String regex = "^[1-9][0-9]*$";
		return str.matches(regex);
	}
	public static boolean isImage(String str){
		if(str.equalsIgnoreCase("jpg")||str.equalsIgnoreCase("jpeg")||str.equalsIgnoreCase("gif")||
			str.equalsIgnoreCase("png")||str.equalsIgnoreCase("bmp")){
			return true;
		}
		return false;
	}
}
