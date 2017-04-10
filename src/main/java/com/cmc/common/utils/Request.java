package com.cmc.common.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.ByteArrayBody;
//import org.apache.http.entity.mime.content.ContentBody;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @brief 请求调用类
 * @author robinslsun
 */
public class Request {
    private static final Logger LOG = Logger.getLogger(Request.class);

    public static String sendRequest(String url, Map<String, Object> data, String requestMethod, Map<String, String> header) {
        return sendRequest(url, data, requestMethod, header, null);
    }

    public static String sendRequest(String url, Map<String, Object> data, String requestMethod, Map<String, String> header, String fileName) {
        return sendRequest(url, data, requestMethod, header, fileName, -1, 0);
    }

    public static String sendRequest(String url, Map<String, Object> data, String requestMethod, Map<String, String> header, String fileName, long offset, int sliceSize) {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        if (requestMethod.equals("GET")) {
            try {
                String paramStr = "";
                for (String key : data.keySet()) {
                    if (!paramStr.isEmpty()) {
                        paramStr += '&';
                    }
                    paramStr += key + '=' + URLEncoder.encode(data.get(key).toString(), "utf-8");
                }
                if (url.indexOf('?') > 0) {
                    url += '&' + paramStr;
                } else {
                    url += '?' + paramStr;
                }
                HttpGet httpGet = new HttpGet(url);
                httpGet.setHeader("accept", "*/*");
                httpGet.setHeader("connection", "Keep-Alive");
                httpGet.setHeader("user-agent", "qcloud-java-sdk");
                if (header != null) {
                    for (String key : header.keySet()) {
                        httpGet.setHeader(key, header.get(key));
                    }
                }
                HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
                return EntityUtils.toString(httpResponse.getEntity());
            } catch (Exception e) {
                LOG.error("", e);
            }
        } else {
            try {
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("accept", "*/*");
                httpPost.setHeader("connection", "Keep-Alive");
                httpPost.setHeader("user-agent", "qcloud-java-sdk");
                if (header != null) {
                    for (String key : header.keySet()) {
                        httpPost.setHeader(key, header.get(key));
                    }
                }
                MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
                if (data != null) {
                    for (String key : data.keySet()) {
                        multipartEntity.addPart(key, new StringBody(data.get(key).toString(), ContentType.TEXT_PLAIN));
                    }
                }
                // 文件上传
                if (fileName != null) {
                    File file = new File(fileName);
                    if (offset == -1) {
                        // 单文件上传
                        FileBody fileBody = new FileBody(file);
                        multipartEntity.addPart("fileContent", fileBody);
                    } else {
                        // 分片上传
                        DataInputStream ins = new DataInputStream(new FileInputStream(file));
                        ins.skip(offset);
                        int len = (int) (offset + sliceSize > file.length() ? file.length() - offset : sliceSize);
                        byte[] bufferOut = new byte[len];
                        ins.read(bufferOut);
                        ContentBody contentBody = new ByteArrayBody(bufferOut, file.getName());// new
                                                                                               // ByteArrayBody(bytes,
                                                                                               // fileName);
                        multipartEntity.addPart("fileContent", contentBody);
                        ins.close();
                    }
                }
                httpPost.setEntity(multipartEntity.build());
                HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
                return EntityUtils.toString(httpResponse.getEntity());
            } catch (Exception e) {
                LOG.error("请求cos失败");
                return null;
            } finally {
            }
        }
        return "";
    }
}
