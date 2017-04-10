package com.cmc.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.cmc.common.constants.Constants;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 * 头像上传工具类
 * @author Zexin.Wang
 */
public class PhotoUtil {

	private static final Logger LOG = Logger.getLogger(PhotoUtil.class);

	/**
	 * 上传图片
	 * @author Zexin.Wang
	 * @param request
	 * @return 图片路径
	 */
	public static String upload(HttpServletRequest request, String fileId, String folder) {

		if (!(request instanceof MultipartRequest)) {
			return null;
		}
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile(fileId);
			if (file == null || file.getSize() <= 0) {
				return null;
			}
			String filename = file.getOriginalFilename();
			if (filename == null || filename.equals("")) {
				return null;
			}
			int i = filename.lastIndexOf(".");
			String extname = filename.substring(i);
			String newName = rename(extname);
			String realFolder = Constants.PHOTO_UPLOAD_PACKAGE + folder + "/";
			File f = new File(realFolder);
			if (!f.exists()) {
				f.mkdir();
			}
			File uploadFile = new File(realFolder + newName);
			InputStream inputSream = file.getInputStream();
			byte[] advImageBytes = FileCopyUtils.copyToByteArray(inputSream);
			FileCopyUtils.copy(advImageBytes, uploadFile);
			inputSream.close();

			String url = "/" + folder + "/" + newName;
			return url;
		} catch (Exception e) {
			LOG.error("upload photo error ", e);
			return null;
		}
	}

	/**
	 * 上传图片-前端压缩后通过base64上传
	 * @author Zexin.Wang
	 * @param request
	 * @param fileId
	 * @param folder
	 * @return
	 */
	public static String uploadBase64(HttpServletRequest request, String fileId, String folder) {
		String img = request.getParameter("picture");
		String imgStr = img.substring(img.indexOf(",") + 1);
		if (imgStr == null) // 图像数据为空
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			String realFolder = Constants.PHOTO_UPLOAD_PACKAGE + folder + "/" + DateUtils.getToday() + "/";
			File f = new File(realFolder);
			if (!f.exists()) {
				f.mkdirs();
			}
			Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			Long random = (long) (Math.random() * now);
			String fileName = now + "" + random + ".jpg";
			File uploadFile = new File(realFolder + fileName);
			FileCopyUtils.copy(b, uploadFile);

			String url = "/" + folder + "/" + DateUtils.getToday() + "/" + fileName;
			return url;
		} catch (Exception e) {
			return null;
		}
	}

	public static String uploadBase64ToTx(HttpServletRequest request, String fileId, String folder) {
		String img = request.getParameter("picture");
		String imgStr = img.substring(img.indexOf(",") + 1);
		if (imgStr == null) // 图像数据为空
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			Long random = (long) (Math.random() * now);
			String fileName = now + "" + random + ".jpg";
			// String res = HttpPostUploadUtil.upload(b, fileName, Constants.BUCKETTOKEN, Constants.CREATOR_WEIXIN, "0");// （1：需要OCR解析；0：不需要）
			String res = null;
			String url = null;
			if (StringUtils.isNotBlank(res)) {
				JSONObject obj = JSONObject.fromObject(res);
				if (0 == obj.optInt("code")) {
					url = obj.optJSONObject("data").optString("download_url");
				} else {
					return null;
				}
			}
			return url;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 前端转化为Blob对象上传图片，直接重命名
	 * @author Zexin.Wang
	 * @param request
	 * @param fileId
	 * @param folder
	 * @return
	 * @date 2015年9月26日 下午4:08:08
	 */
	public static String uploadBlob(HttpServletRequest request, String fileId, String folder) {

		if (!(request instanceof MultipartRequest)) {
			return null;
		}
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile(fileId);
			if (file == null || file.getSize() <= 0) {
				return null;
			}
			Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			Long random = (long) (Math.random() * now);
			String fileName = now + "" + random + ".jpg";
			String realFolder = Constants.PHOTO_UPLOAD_PACKAGE + folder + "/" + DateUtils.getToday() + "/";
			File f = new File(realFolder);
			if (!f.exists()) {
				f.mkdir();
			}
			File uploadFile = new File(realFolder + fileName);
			InputStream inputSream = file.getInputStream();
			byte[] advImageBytes = FileCopyUtils.copyToByteArray(inputSream);
			FileCopyUtils.copy(advImageBytes, uploadFile);
			inputSream.close();

			String url = "/" + folder + "/" + DateUtils.getToday() + "/" + fileName;
			return url;
		} catch (Exception e) {
			LOG.error("upload photo error ", e);
			return null;
		}
	}

	/**
	 * rename the file
	 * @author Zexin.Wang
	 * @param name
	 * @return
	 */
	private static String rename(String name) {
		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random;

		if (name.indexOf('.') != -1) {
			fileName += name.substring(name.lastIndexOf('.'));
		}

		return fileName;
	}

	@SuppressWarnings("restriction")
	public static String uploadBase64ByTx(HttpServletRequest request, String fileId, String folder) {
		String img = request.getParameter("picture");
		String imgStr = img.substring(img.indexOf(",") + 1);
		if (imgStr == null) // 图像数据为空
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			Long random = (long) (Math.random() * now);
			String fileName = now + "" + random + ".jpg";
			// String res = HttpPostUploadUtil.upload(b, fileName, Constants.BUCKETTOKEN, Constants.CREATOR, "0");// （1：需要OCR解析；0：不需要）
			String res = null;
			LOG.info("res--sucuess------------------" + res);
			return res;
		} catch (Exception e) {
			LOG.info("res----exception----------");
			return null;
		}
	}

	public static String ocr(HttpServletRequest request, String fileId, String folder, String ocr) {
		String img = request.getParameter("picture");
		String imgStr = img.substring(img.indexOf(",") + 1);
		if (imgStr == null) // 图像数据为空
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			Long random = (long) (Math.random() * now);
			String fileName = now + "" + random + ".jpg";
			// String res = HttpPostUploadUtil.upload(b, fileName, Constants.BUCKETTOKEN, Constants.CREATOR, ocr);// （1：需要OCR解析；0：不需要）
			String res = null;
			LOG.info("res--sucuess------------------" + res);
			return res;
		} catch (Exception e) {
			LOG.info("res----exception----------");
			return null;
		}
	}

	public static final String DOWNLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	/**
	 * 从微信服务器下载文件并上传到腾讯-万象存储
	 * @author Zexin.Wang
	 * @param accessToken
	 * @param mediaId
	 * @return
	 * @throws IOException
	 * @date 2015年10月21日 下午7:39:23 
	 */
	public static String downloadMediaFromWxAndUploadTx(String accessToken, String mediaId) throws IOException {
		if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(mediaId))
			return null;
		String requestUrl = DOWNLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		InputStream in = conn.getInputStream();
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = -1;
		while ((len = in.read(data)) != -1) {
			bas.write(data, 0, len);
		}
		byte[] b = bas.toByteArray();
		in.close();
		conn.disconnect();
		String result = uploadWxPhotoToTx(b);
		return result;
	}

	public static String uploadWxPhotoToTx(byte[] b) {
		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random + ".jpg";
		// String res = HttpPostUploadUtil.upload(b, fileName, Constants.BUCKETTOKEN, Constants.CREATOR_WEIXIN, "0");// （1：需要OCR解析；0：不需要）
		String res = null;
		LOG.info("res--sucuess------------------" + res);
		return res;
	}

}