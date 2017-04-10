package com.cmc.common.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * @author renyh
 */
public class DESCoder {
	public static Base64 base64 = new Base64();

	// 密钥

	public static void main(String[] args) {

		String utl = "{\"F\":1,\"S\":2,\"M\":0,\"UId\":\"UH151201102823243e4d\",\"CId\":369,\"T\":1450409533}";
		try {
			String out = desEncrypt(utl, strkey);
			System.out.println(out);
			System.out.println(desDecrypt("ouUoxUlhtOM2G6Wbc8z705Fs4q5NLj2qy4usEiuVUwltmJHDLSmvwMMfiteNAOJOg6ALA4l0FJpyuvC4tLo3vprqqPZYofbxcEUUCEbRaimu6R w3HfSVw==".replace(" ", "+"), strkey));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String strkey = "kfbucmed88851766kfbucmed88851766";

	public static String desEncrypt(String input, String strkey) throws Exception {

		Base64 base64d = new Base64();
		DESedeKeySpec p8ksp = null;
		p8ksp = new DESedeKeySpec(base64d.decode(strkey.getBytes()));
		Key key = null;
		key = SecretKeyFactory.getInstance("DESede").generateSecret(p8ksp);

		byte[] plainBytes = (byte[]) null;
		Cipher cipher = null;
		byte[] cipherText = (byte[]) null;
		// “算法/模式/填充”
		plainBytes = input.getBytes("UTF8");
		cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		SecretKeySpec myKey = new SecretKeySpec(key.getEncoded(), "DESede");
		cipher.init(1, myKey);
		cipherText = cipher.doFinal(plainBytes);
		return removeBR(new String(base64.encode(cipherText)));
	}

	public static String desDecrypt(String cipherText, String strkey) throws Exception {

		Base64 base64d = new Base64();
		DESedeKeySpec p8ksp = null;
		p8ksp = new DESedeKeySpec(base64d.decode(strkey.getBytes()));
		Key key = null;
		key = SecretKeyFactory.getInstance("DESede").generateSecret(p8ksp);

		Cipher cipher = null;
		byte[] inPut = base64d.decode(cipherText.getBytes());
		// “算法/模式/填充”
		cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		SecretKeySpec myKey = new SecretKeySpec(key.getEncoded(), "DESede");
		cipher.init(2, myKey);
		byte[] output = cipher.doFinal(inPut);
		return new String(output, "UTF8");
	}

	private static String removeBR(String str) {
		StringBuffer sf = new StringBuffer(str);

		for (int i = 0; i < sf.length(); ++i) {
			if (sf.charAt(i) == '\n') {
				sf = sf.deleteCharAt(i);
			}
		}
		for (int i = 0; i < sf.length(); ++i)
			if (sf.charAt(i) == '\r') {
				sf = sf.deleteCharAt(i);
			}

		return sf.toString();
	}
}
