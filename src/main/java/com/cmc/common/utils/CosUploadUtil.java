package com.cmc.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * COS Uploading Utility.
 * 
 * @author linb
 * @author HT-LiChuanbin
 * @since 2016年12月9日 下午5:52:44
 */
public class CosUploadUtil {

    private static final int DEFAULT_ERROR_CODE = -1;
    private static final Logger LOG = LoggerFactory.getLogger(CosUploadUtil.class);
    /** COS分片上传单片默认大小：1M */
    private static final int COS_SLICE_DEFAULT_SIZE = 1024 * 1024;

    private CosUploadUtil() {
    }

    /**
     * 将本地文件上传到COS对应文件夹
     * 
     * @param filePath 本地文件位置
     * @param folderPath COS上文件路径
     * @param fileUrlKey 返回结果中文件下载地址key值
     * @return 上传结果
     */
    public static JSONObject cosUpload(String filePath, String folderPath, String fileUrlKey) {
        JSONObject res = new JSONObject();
        if (StringUtils.isBlank(filePath)) {
            LOG.info("file path is null");
            res.put("ret_code", UploadResCode.FILE_NOT_EXISTS.getCode());
            res.put("ret_info", UploadResCode.FILE_NOT_EXISTS.getDesc());
            return res;
        }
        Long expired = System.currentTimeMillis() / 1000 + Constants.VALID_TIME;
        String sign = FileCloudSign.appSignature(Constants.COS_APP_ID, Constants.COS_SECRET_ID, Constants.COS_SECRET_KEY, expired, Constants.BUCKET_NAME);
        String url = null;
        int pos = filePath.lastIndexOf("\\");
        if (pos == -1) {
            pos = filePath.lastIndexOf("/");
        }
        String fileName = filePath.substring(pos + 1);
        try {
            url = Constants.COSAPI_CGI_URL + Constants.COS_APP_ID + "/" + Constants.BUCKET_NAME + folderPath + URLEncoder.encode(fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("url编码错误", e);
            res.put("ret_code", UploadResCode.CODE_FAILED.getCode());
            res.put("ret_info", UploadResCode.CODE_FAILED.getDesc());
            return res;
        }
        try {
            // 文件sha值
            String sha1 = HMACSHA1.getFileSha1(filePath);
            // 完整上传
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("op", "upload");
            data.put("sha", sha1);
            // data.put("fileContent", getBytes(filePath));
            HashMap<String, String> header = new HashMap<String, String>();
            header.put("Authorization", sign);
            String resStr = Request.sendRequest(url, data, "POST", header, filePath);
            resStr = new String(resStr.getBytes("ISO-8859-1"), "UTF-8");
            LOG.info("------------------------------cos upload res : " + resStr);
            JSONObject resJson = JSONObject.fromObject(resStr);
            res.put("ret_code", resJson.optString("code"));
            res.put("ret_info", resJson.optString("message"));
            if (UploadResCode.UPLOAD_SUCCESS.getCode().equals(resJson.optString("code"))) {
                res.put(fileUrlKey, resJson.optJSONObject("data").optString("access_url"));
            }
        } catch (Exception e) {
            LOG.error("cos upload error", e);
            res.put("ret_code", UploadResCode.COS_UPLOAD_FAILED.getCode());
            res.put("ret_info", UploadResCode.COS_UPLOAD_FAILED.getDesc());
            return res;
        }
        return res;
    }

    /**
     * 将本地文件上传到COS（v4）
     * 
     * <p>
     * - 问题
     *     1. 应用场景
     *     2. 同一文件（重名）上传，可能会出错，因为没有设置“insertOnly”，也就是说默认不覆盖，文件名称需要进行唯一算法，已经在过滤器进行重新命名了（保证唯一）
     * </p>
     * 
     * @param filePath 本地文件位置
     * @param folderPath COS上文件路径
     * @param fileUrlKey 返回结果中文件下载地址key值
     * @return 上传结果
     */
    public static JSONObject cosUpload_v4(String filePath, String folderPath, String fileUrlKey) {
        JSONObject res = new JSONObject();
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("op", "upload");
            data.put("sha", HMACSHA1.getFileSha1(filePath));

            Map<String, String> header = new HashMap<String, String>();
            header.put("Authorization", getSign());

            String strResFromCOS = new String(Request.sendRequest(getRestfulUrl(filePath, folderPath), data, RequestMethod.POST.toString(), header, filePath).getBytes("ISO-8859-1"), "UTF-8");
            JSONObject jResFromCOS = JSONObject.fromObject(strResFromCOS);

            res.put("ret_code", jResFromCOS.optString("code"));
            res.put("ret_info", jResFromCOS.optString("message"));
            if (UploadResCode.UPLOAD_SUCCESS.getCode().equals(jResFromCOS.optString("code"))) {
                res.put(fileUrlKey, jResFromCOS.optJSONObject("data").optString("access_url"));
            }
        } catch (IOException e) {
            LOG.error("", e);
            res.put("ret_code", UploadResCode.COS_UPLOAD_FAILED.getCode());
            res.put("ret_info", UploadResCode.COS_UPLOAD_FAILED.getDesc());
        }
        return res;
    }

    public static void main(String[] args) {
        //        String filePath = "F:/2.jpg";
        //        JSONObject rst = CosUploadUtil.cosUploadSlice(filePath, "/test/");
        //        @SuppressWarnings("unused")
        //        JSONObject tmp = rst;

        Object a = "adf";
        System.out.println(a.toString());
    }

    /**
     * @Description 将本地文件上传到cos对应文件夹，并删除本地文件夹
     * @param filePath
     *            本地文件位置
     * @param folderPath
     *            cos上文件路径
     * @param fileUrlKey
     *            返回结果中文件下载地址key值
     * @return 上传结果
     */
    public static JSONObject cosUploadAndDeleteLinux(String filePath, String folderPath, String fileUrlKey) {
        JSONObject res = cosUpload(filePath, folderPath, fileUrlKey);
        if (!UploadResCode.UPLOAD_SUCCESS.getCode().equals(res.optString("ret_code"))) {
            return res;
        }
        String processRes = "";
        BufferedReader br = null;
        Integer exitValue = DEFAULT_ERROR_CODE;
        String lineStr;
        try {
            String cmd = "rm " + filePath; // linux 删除文件命令
            LOG.info("cmd : " + cmd);
            Process p = null;
            Runtime run = Runtime.getRuntime();
            p = run.exec(cmd);
            exitValue = p.waitFor();
            if (exitValue != 0) {
                br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream())));
            }
            while ((lineStr = br.readLine()) != null) {
                processRes += lineStr;
            }
            LOG.info("process running result : " + processRes);
        } catch (Exception e) {
            LOG.error("delete file error", e);
            res.put("ret_code", UploadResCode.FILE_DELETE_FAILED.getCode());
            res.put("ret_info", UploadResCode.FILE_DELETE_FAILED.getDesc());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        if (0 != exitValue) {
            res.put("ret_code", UploadResCode.PROCESS_ERROR.getCode());
            res.put("ret_info", UploadResCode.PROCESS_ERROR.getDesc());
        }
        return res;
    }

    /**
     * COS分片上传， 默认分片大小
     * @param filePath  本地文件路径
     * @param folderPath COS文件保存路径，例：/ppt/
     * @return 上传结果
     * @author HT-LiChuanbin
     * @since2016年12月12日 上午11:13:02
     */
    public static JSONObject cosUploadSlice(String filePath, String folderPath) {
        return cosUploadSlice(filePath, folderPath, COS_SLICE_DEFAULT_SIZE);
    }

    /**
     * COS分片上传
     * @param filePath 本地文件路径
     * @param folderPath COS文件保存路径，例：/ppt/
     * @param sliceSize 分片大小
     * @return 上传结果
     * @author HT-LiChuanbin
     * @since 2016年12月12日 上午11:16:27
     */
    public static JSONObject cosUploadSlice(String filePath, String folderPath, Integer sliceSize) {
        JSONObject res = new JSONObject();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                LOG.warn("file is not exists.");
                res.put("ret_code", UploadResCode.FILE_NOT_EXISTS.getCode());
                res.put("ret_info", UploadResCode.FILE_NOT_EXISTS.getDesc());
                return res;
            }

            String sign = getSign();
            String url = getRestfulUrl(filePath, folderPath);

            JSONObject firstRes = cosUploadSliceFirst_v4(url, filePath, HMACSHA1.getFileSha1(filePath), file.length(), sliceSize, sign);
            /* 第一片，不成功或者秒传 */
            if (!UploadResCode.UPLOAD_SUCCESS.getCode().equals(firstRes.optString("ret_code")) || firstRes.has("file_url")) {
                return firstRes;
            }

            /* 后续片 */
            String session = firstRes.optString("session");
            Long offset = firstRes.optLong("offset");
            while (true) {
                JSONObject nextRes = cosUploadSliceNext_v4(url, filePath, session, offset, sliceSize, sign);
                if (!UploadResCode.UPLOAD_SUCCESS.getCode().equals(nextRes.optString("ret_code")) || nextRes.has("file_url")) {
                    return nextRes;
                }
                offset = nextRes.getLong("offset");
            }
        } catch (Exception e) {
            LOG.error("", e);
            res.put("ret_code", UploadResCode.COS_SLICE_UPLOAD_FAILED.getCode());
            res.put("ret_info", UploadResCode.COS_SLICE_UPLOAD_FAILED.getDesc());
            return res;
        }
    }

    /**
     * @Description 将文件转换成二进制数组
     * @param filePath
     *            本地文件绝对路径
     * @return 文件二进制数组
     * @throws Exception
     */
    public static byte[] getBytes(String filePath) throws Exception {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            LOG.error("file is not find", e);
            throw e;
        } catch (IOException e) {
            LOG.error("", e);
            throw e;
        }
        return buffer;
    }

    /**
     * @Description cos分片上传，第一页上传
     * @param url
     *            上传地址
     * @param filePath
     *            文件地址
     * @param sha1
     *            文件sha值
     * @param fileSize
     *            文件大小
     * @param sliceSize
     *            分片大小
     * @param sign
     *            鉴权标志
     * @return 上传结果（可能秒传）
     * @throws Exception
     *             上传异常
     */
    @SuppressWarnings("unused")
    private static JSONObject cosUploadSliceFirst(String url, String filePath, String sha1, Long fileSize, Integer sliceSize, String sign) throws Exception {
        JSONObject res = new JSONObject();
        res.put("ret_code", UploadResCode.COS_SLICE_UPLOAD_FAILED.getCode());
        res.put("ret_info", UploadResCode.COS_SLICE_UPLOAD_FAILED.getDesc());
        try {
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("op", "upload_slice");
            data.put("sha", sha1);
            data.put("filesize", fileSize);
            data.put("slice_size", sliceSize);
            HashMap<String, String> header = new HashMap<String, String>();
            header.put("Authorization", sign);
            String resStr = Request.sendRequest(url, data, "POST", header);
            resStr = new String(resStr.getBytes("ISO-8859-1"), "UTF-8");
            LOG.info("------------------------------first_slice cos upload res : " + resStr);
            JSONObject resJson = JSONObject.fromObject(resStr);

            if (UploadResCode.UPLOAD_SUCCESS.getCode().equals(resJson.optString("code"))) {
                JSONObject jsonData = resJson.getJSONObject("data");
                if (jsonData.has("session")) {
                    String session = jsonData.getString("session");
                    Long offset = jsonData.getLong("offset");
                    res.put("ret_code", UploadResCode.UPLOAD_SUCCESS.getCode());
                    res.put("ret_info", UploadResCode.UPLOAD_SUCCESS.getDesc());
                    res.put("session", session);
                    res.put("offset", offset);
                } else {
                    // 文件秒传
                    res.put("ret_code", UploadResCode.UPLOAD_SUCCESS.getCode());
                    res.put("ret_info", UploadResCode.UPLOAD_SUCCESS.getDesc());
                    res.put("file_url", jsonData.getString("access_url"));
                }
            } else {
                res.put("ret_code", resJson.optString("code"));
                res.put("ret_info", resJson.optString("message"));
            }
        } catch (Exception e) {
            LOG.error("first slice upload error", e);
            throw e;
        }
        return res;
    }

    /**
     * COS分片上传（v4），第一页上传
     * @param url 上传地址
     * @param filePath 文件地址
     * @param sha1 文件sha值
     * @param fileSize 文件大小
     * @param sliceSize  分片大小
     * @param sign 鉴权标志
     * @return 上传结果（可能秒传）
     * @author HT-LiChuanbin
     * @since 2016年12月12日 上午10:55:07
     */
    private static JSONObject cosUploadSliceFirst_v4(String url, String filePath, String sha1, Long fileSize, Integer sliceSize, String sign) {
        JSONObject res = new JSONObject();
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("op", "upload_slice");
            data.put("sha", sha1);
            data.put("filesize", fileSize);
            data.put("slice_size", sliceSize);

            Map<String, String> header = new HashMap<String, String>();
            header.put("Authorization", sign);

            String strResFromCOS = new String(Request.sendRequest(url, data, RequestMethod.POST.toString(), header).getBytes("ISO-8859-1"), "UTF-8");
            JSONObject jResFromCOS = JSONObject.fromObject(strResFromCOS);

            if (!UploadResCode.UPLOAD_SUCCESS.getCode().equals(jResFromCOS.optString("code"))) {
                res.put("ret_code", jResFromCOS.optString("code"));
                res.put("ret_info", jResFromCOS.optString("message"));
                return res;
            }

            JSONObject jDataFromCOS = jResFromCOS.getJSONObject("data");
            /* 文件秒传 */
            if (!jDataFromCOS.has("session")) {
                res.put("ret_code", UploadResCode.UPLOAD_SUCCESS.getCode());
                res.put("ret_info", UploadResCode.UPLOAD_SUCCESS.getDesc());
                res.put("file_url", jDataFromCOS.getString("access_url"));
            } else {
                String session = jDataFromCOS.getString("session");
                Long offset = jDataFromCOS.getLong("offset");
                res.put("ret_code", UploadResCode.UPLOAD_SUCCESS.getCode());
                res.put("ret_info", UploadResCode.UPLOAD_SUCCESS.getDesc());
                res.put("session", session);
                res.put("offset", offset);
            }
        } catch (Exception e) {
            LOG.error("", e);
            res.put("ret_code", UploadResCode.COS_SLICE_UPLOAD_FAILED.getCode());
            res.put("ret_info", UploadResCode.COS_SLICE_UPLOAD_FAILED.getDesc());
        }
        return res;
    }

    /**
     * @Description cos分片上传，后续片上传
     * @param url
     *            上传地址
     * @param filePath
     *            文件路径
     * @param session
     *            本次分页上传唯一标识
     * @param offset
     *            分片偏移值（本次分片开始位置与文件开头偏移值）
     * @param sliceSize
     *            分页大小
     * @param sign
     *            鉴权标志
     * @return 上传结果(完成或者下一片的offset)
     * @throws Exception
     *             上传异常
     */
    @SuppressWarnings("unused")
    private static JSONObject cosUploadSliceNext(String url, String filePath, String session, Long offset, Integer sliceSize, String sign) throws Exception {
        JSONObject res = new JSONObject();
        res.put("ret_code", UploadResCode.COS_SLICE_UPLOAD_FAILED.getCode());
        res.put("ret_info", UploadResCode.COS_SLICE_UPLOAD_FAILED.getDesc());
        try {
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("op", "upload_slice");
            data.put("slice_size", sliceSize);
            data.put("session", session);
            data.put("offset", offset);
            HashMap<String, String> header = new HashMap<String, String>();
            header.put("Authorization", sign);
            String resStr = Request.sendRequest(url, data, "POST", header, filePath, offset, sliceSize);
            resStr = new String(resStr.getBytes("ISO-8859-1"), "UTF-8");
            LOG.info("------------------------------next_slice cos upload res : " + resStr + ", offset : " + offset);
            JSONObject resJson = JSONObject.fromObject(resStr);

            if (UploadResCode.UPLOAD_SUCCESS.getCode().equals(resJson.optString("code"))) {
                JSONObject jsonData = resJson.getJSONObject("data");
                if (jsonData.has("offset")) {
                    Long nextOffset = jsonData.getLong("offset") + sliceSize;
                    res.put("ret_code", UploadResCode.UPLOAD_SUCCESS.getCode());
                    res.put("ret_info", UploadResCode.UPLOAD_SUCCESS.getDesc());
                    res.put("offset", nextOffset);
                } else {
                    res.put("ret_code", UploadResCode.UPLOAD_SUCCESS.getCode());
                    res.put("ret_info", UploadResCode.UPLOAD_SUCCESS.getDesc());
                    res.put("file_url", jsonData.getString("access_url"));
                }
            } else {
                res.put("ret_code", resJson.optString("code"));
                res.put("ret_info", resJson.optString("message"));
            }
        } catch (Exception e) {
            LOG.error("next slice upload error", e);
            throw e;
        }
        return res;
    }

    /**
     * COS分片上传，后续片上传
     * @param url 上传地址
     * @param filePath 文件路径
     * @param session 本次分页上传唯一标识
     * @param offset 分片偏移值（本次分片开始位置与文件开头偏移值）
     * @param sliceSize 分页大小
     * @param sign 鉴权标志
     * @return 上传结果(完成或者下一片的offset)
     * @throws Exception 上传异常
     * @author HT-LiChuanbin
     * @since 2016年12月12日 上午11:08:24
     */
    private static JSONObject cosUploadSliceNext_v4(String url, String filePath, String session, Long offset, Integer sliceSize, String sign) {
        JSONObject res = new JSONObject();
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("op", "upload_slice");
            data.put("slice_size", sliceSize);
            data.put("session", session);
            data.put("offset", offset);

            Map<String, String> header = new HashMap<String, String>();
            header.put("Authorization", sign);

            String strResFromCOS = new String(Request.sendRequest(url, data, "POST", header, filePath, offset, sliceSize).getBytes("ISO-8859-1"), "UTF-8");
            JSONObject jResFromCOS = JSONObject.fromObject(strResFromCOS);

            if (!UploadResCode.UPLOAD_SUCCESS.getCode().equals(jResFromCOS.optString("code"))) {
                res.put("ret_code", jResFromCOS.optString("code"));
                res.put("ret_info", jResFromCOS.optString("message"));
                return res;
            }

            JSONObject jsonData = jResFromCOS.getJSONObject("data");
            if (jsonData.has("offset")) {
                Long nextOffset = jsonData.getLong("offset") + sliceSize;
                res.put("ret_code", UploadResCode.UPLOAD_SUCCESS.getCode());
                res.put("ret_info", UploadResCode.UPLOAD_SUCCESS.getDesc());
                res.put("offset", nextOffset);
            } else {
                res.put("ret_code", UploadResCode.UPLOAD_SUCCESS.getCode());
                res.put("ret_info", UploadResCode.UPLOAD_SUCCESS.getDesc());
                res.put("file_url", jsonData.getString("access_url"));
            }
        } catch (Exception e) {
            LOG.error("", e);
            res.put("ret_code", UploadResCode.COS_SLICE_UPLOAD_FAILED.getCode());
            res.put("ret_info", UploadResCode.COS_SLICE_UPLOAD_FAILED.getDesc());
        }
        return res;
    }

    /** 获取签名 */
    private static String getSign() {
        Long expired = System.currentTimeMillis() / 1000 + Constants.VALID_TIME;
        return FileCloudSign.appSignature(Constants.COS_APP_ID, Constants.COS_SECRET_ID, Constants.COS_SECRET_KEY, expired, Constants.BUCKET_NAME);
    }

    /** 获取COS Restful请求地址 */
    private static String getRestfulUrl(String filePath, String folderPath) throws UnsupportedEncodingException {
        int pos = isWindowsFilePath(filePath) ? filePath.lastIndexOf("\\") : filePath.lastIndexOf("/");
        String filename = filePath.substring(pos + 1);
        return Constants.COSAPI_CGI_URL + Constants.COS_APP_ID + "/" + Constants.BUCKET_NAME + folderPath + URLEncoder.encode(filename, "UTF-8");
    }

    /** 是否是windows下面的文件目录 */
    private static boolean isWindowsFilePath(String filePath) {
        return -1 != filePath.lastIndexOf("\\");
    }

}