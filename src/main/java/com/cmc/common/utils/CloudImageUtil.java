package com.cmc.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName CloudImageUtil
 * @Description 万象优图CI工具类
 * @author linb
 * @date 2015年10月28日 下午2:19:15
 */
public class CloudImageUtil {

	/**
	 * @Description 获取万象优图上图片的缩略图，等比缩放
	 * @param url 原图url
	 * @param width 宽
	 * @param height 高
	 * @param quality 压缩质量，范围为1~100，100时为原图
	 * @return 缩略图
	 */
	public static String getThumbnailUrl(String url, Integer width, Integer height, Integer quality) {
		String w = null == width ? "" : width.toString();
		String h = null == height ? "" : height.toString();
		String q = null == quality ? "" : quality.toString();
		return getThumbnailUrl(url, w, h, q);
	}

	/**
	 * @Description 获取万象优图上图片的缩略图，等比缩放
	 * @param url 原图url
	 * @param width 宽
	 * @param height 高
	 * @param quality 压缩质量，范围为1~100，100时为原图
	 * @return 缩略图
	 */
	public static String getThumbnailUrl(String url, String width, String height, String quality) {
		if (StringUtils.isBlank(url)) {
			return "";
		}
		StringBuffer thumbnailUrl = new StringBuffer();
		thumbnailUrl.append(url + "?imageView2/2");
		if (StringUtils.isNotBlank(width)) {
			thumbnailUrl.append("/w/" + width);
		}
		if (StringUtils.isNotBlank(height)) {
			thumbnailUrl.append("/h/" + height);
		}
		if (StringUtils.isNotBlank(quality)) {
			thumbnailUrl.append("/q/" + quality);
		}
		return thumbnailUrl.toString();
	}
}
