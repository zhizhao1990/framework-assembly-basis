package com.cmc.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class HMACSHA1 {

	private static final Logger LOG = Logger.getLogger(HMACSHA1.class);

	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";
	private static final String HMAC_SHA1 = "HmacSHA1";

	/*
	 * 展示了一个生成指定算法密钥的过程 初始化HMAC密钥
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * public static String initMacKey() throws Exception { //得到一个 指定算法密钥的密钥生成器
	 * KeyGenerator KeyGenerator keyGenerator
	 * =KeyGenerator.getInstance(MAC_NAME); //生成一个密钥 SecretKey secretKey
	 * =keyGenerator.generateKey(); return null; }
	 */

	/**
	 * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
	 * 
	 * @param encryptText
	 *            被签名的字符串
	 * @param encryptKey
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
		byte[] data = encryptKey.getBytes(ENCODING);
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		// 生成一个指定 Mac 算法 的 Mac 对象
		Mac mac = Mac.getInstance(MAC_NAME);
		// 用给定密钥初始化 Mac 对象
		mac.init(secretKey);

		byte[] text = encryptText.getBytes(ENCODING);
		// 完成 Mac 操作
		return mac.doFinal(text);
	}

	/**
	 * 生成签名数据
	 * 
	 * @param data
	 *            待加密的数据
	 * @param key
	 *            加密使用的key
	 * @return 生成MD5编码的字符串
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getSignature(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data);

		byte[] data1 = new byte[rawHmac.length];
		for (int i = 0; i < rawHmac.length; i++) {
			data1[i] = (byte) (rawHmac[i] & 0xff);
		}

		return data1;
	}

	// cos start

	public static byte[] getSignature(String data, String key) throws Exception {
		return getSignature(data.getBytes(), key);
	}

	public static byte[] getSignature(byte[] data, String key) throws Exception {
		Mac mac = Mac.getInstance(HMAC_SHA1);
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), mac.getAlgorithm());
		mac.init(signingKey);
		return mac.doFinal(data);
	}

	public static String getFileSha1(String path) throws IOException {
		File file = new File(path);
		FileInputStream in = new FileInputStream(file);
		MessageDigest messagedigest;
		try {
			messagedigest = MessageDigest.getInstance("SHA-1");

			byte[] buffer = new byte[1024 * 1024 * 10];
			int len = 0;

			while ((len = in.read(buffer)) > 0) {
				// 该对象通过使用 update（）方法处理数据
				messagedigest.update(buffer, 0, len);
			}

			// 对于给定数量的更新数据，digest 方法只能被调用一次。在调用 digest 之后，MessageDigest
			// 对象被重新设置成其初始状态。
			return getFormattedText(messagedigest.digest());//byte2hex(messagedigest.digest());
		} catch (NoSuchAlgorithmException e) {
			//NQLog.e("getFileSha1->NoSuchAlgorithmException###", e.toString());
			LOG.error("", e);
		} catch (OutOfMemoryError e) {
			//NQLog.e("getFileSha1->OutOfMemoryError###", e.toString());
			LOG.error("", e);
			throw e;
		} finally {
			in.close();
		}
		return null;
	}

	private static String getFormattedText(final byte[] bytes) {
		char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	//cos end
}
