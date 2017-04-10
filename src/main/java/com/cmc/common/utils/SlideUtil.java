package com.cmc.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.cmc.common.constants.Constants;

/**
 * @ClassName SlideUtil
 * @Description 病理切片工具类
 * @author linb
 * @date 2015年11月10日 下午8:15:29
 */
public class SlideUtil {

    public static final Logger LOG = Logger.getLogger(SlideUtil.class);

    /**
     * @Description 获取切片缩略图url
     * @param slideUrl
     *            切片下载地址
     * @return 切片缩略图url
     */
    public static String getSlideThumbnailUrl(String slideUrl) {
        String res = null;
        try {
            res = Constants.THUMBNAIL_PRE_URL
                    + DESCoder.desEncrypt(slideUrl, Constants.BL_DES_KEY);
        } catch(Exception e) {
            LOG.error("get slide thumbnail url error", e);
        }
        return res;
    }

    /**
     * @Description 获取切片的截图，没有截图使用缩略图
     * @param slideUrl
     *            切片下载url
     * @param slideId
     *            切片id
     * @return
     */
    public static String getSlideThumbnailUrl(String slideUrl,
            Integer slideId) {
        if (null == slideId) {
            return getSlideThumbnailUrl(slideUrl);
        }
        return getSlideThumbnailUrl(slideUrl, slideId.toString());
    }

    /**
     * @Description 获取切片的截图，没有截图使用缩略图
     * @param slideUrl
     *            切片下载url
     * @param slideId
     *            切片id
     * @return
     */
    public static String getSlideThumbnailUrl(String slideUrl,
            String slideId) {
        if (StringUtils.isBlank(slideId)) {
            return getSlideThumbnailUrl(slideUrl);
        }
        String res = null;
        String shotList = null; // 截图列表字符串，格式为287/287_1.jpg|287/287_2.jpg|
        // 获取截图列表
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder
                .build();
        try {
            String url = Constants.SLIDE_SHOT_LIST_PER_URL + slideId
                    + "&kfbpath="
                    + DESCoder.desEncrypt(slideUrl, Constants.BL_DES_KEY);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = closeableHttpClient
                    .execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (null != httpEntity) {
                shotList = (String) EntityUtils.toString(httpEntity);
            }
            // 有截图用截图，没有用缩略图
            if (StringUtils.isNotBlank(shotList)) {
                res = getSlideShotUrl(shotList, 1);

            } else {
                res = getSlideThumbnailUrl(slideUrl);
            }
        } catch(Exception e) {
            LOG.error("get slide shot list error", e);
        }
        return res;
    }

    /**
     * @Description 获取切片的截图
     * @param shotList
     *            截图列表
     * @param index
     *            第几张截图，从1开始
     * @return 获取指定第几张的截图
     */
    public static String getSlideShotUrl(String shotList, Integer index) {
        if (StringUtils.isBlank(shotList) || null == index || index <= 0) {
            return null;
        }
        String[] shots = shotList.split("\\|");
        if (shots.length < index) {
            return null;
        }
        return shots[index - 1];
    }

    /**
     * @Description 获取切片展示地址，无截图等功能
     * @param slidePath
     *            切片下载地址
     * @return 切片展示地址
     * @throws Exception 加密异常
     */
    public static String getSlideShowUrl(String slidePath)
            throws Exception {
        String slideUrl = Constants.SLIDE_PRE_URL
                + DESCoder.desEncrypt(slidePath, Constants.BL_DES_KEY)
                + "&SwitchLabel=1&SwitchAnno=1&SwitchShowAnno=1&SwitchCut=0";
        return slideUrl;
    }

}
