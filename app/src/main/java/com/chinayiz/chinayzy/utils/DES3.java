package com.chinayiz.chinayzy.utils;


import java.security.Key;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密、解密工具类
 */
public class DES3 {
	// 密钥
	private final static String secretKey = "e3a692a8385f420fa9f48bcff19e5cd5";
	
	// 向量
	private final static String iv = "01234567";
	
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText){
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
			return Base64.encode(encryptData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText
	 *            加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText) {
		byte[] decryptData = null;
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

			decryptData = cipher.doFinal(Base64.decode(encryptText));
			
			return new String(decryptData, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			//加密     
			long time = Calendar.getInstance().getTimeInMillis() ;
			String v = encode("{\"username\" : \"111111\"}");
			System.out.println("加密:"+v);
			System.out.println(Calendar.getInstance().getTimeInMillis() - time);
			//解密
			time = Calendar.getInstance().getTimeInMillis() ;
			System.out.println("解密:"+decode(v));
			System.out.println(Calendar.getInstance().getTimeInMillis() - time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
