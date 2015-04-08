package com.ustcsoft.gs.crm.webui.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class CryptUtil {

	public static String encrypt(String normal) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			byte[] digest = md5.digest(normal.getBytes("UTF-8"));
			return Base64.encodeBase64String(digest);
		} catch (NoSuchAlgorithmException e) {
			return normal;
		} catch (UnsupportedEncodingException e) {
			return normal;
		}
	}

	public static String encode(String normal) throws Exception {
		return Base64.encodeBase64String(normal.getBytes("UTF-8"));
	}

	public static String decode(String encoded) throws Exception {
		return new String(Base64.decodeBase64(encoded), "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encrypt("000000"));
	}

}
