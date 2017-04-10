package com.cmc.demo.httpcomponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * HttpComponents Demo
 * <p>参阅Apache HttpComponents项目</p>
 * @author Thomas Lee
 * @version 2016/04/25 16:25
 */
public class HttpComponentsDemo {

    private static final int CONNECTION_TIMEOUT = 20000;

    /**
     * HTTP GET requests using the HttpClient native API
     * 
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * <br>LINK链接{@link com.cmc.common.utils.HttpClientUtils}
     * <br>{@linkplain com.cmc.common.utils.HttpClientUtils LINK PLAIN DEMO.}
     */
    public static String sendGet(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            response = httpclient.execute(httpGet);
            /*
            The underlying HTTP connection is still held by the response object
            to allow the response content to be streamed directly from the network socket.
            In order to ensure correct deallocation of system resources
            the user MUST call CloseableHttpResponse#close() from a finally clause.
            Please note that if response content is not fully consumed the underlying
            connection cannot be safely re-used and will be shut down and discarded
            by the connection manager. 
            */
            entity = response.getEntity();
            // do something useful with the response body and ensure it is fully consumed
            return (String) EntityUtils.toString(entity);
        } finally {
            response.close();
        }
    }

    /**
     * HTTP POST requests using the HttpClient native API
     * @param url
     * @param nvps
     * @return
     * @throws IOException
     */
    public static String sendPost(String url, List<NameValuePair> nvps) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(CONNECTION_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).setSocketTimeout(CONNECTION_TIMEOUT).build();
        httpPost.setConfig(config);

        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        try {
            HttpEntity entity = response.getEntity();
            // do something useful with the response body and ensure it is fully consumed.
            // EntityUtils.consume(entity);
            return (String) EntityUtils.toString(entity);
        } finally {
            response.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "https://www.baidu.com";
        String rst = sendGet(url);
        System.out.println(rst);
    }

    /**
     * simpler HTTP GET requests using the HttpClient native API
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String quickGet(String url) throws ClientProtocolException, IOException {
        return Request.Get(url).execute().returnContent().toString();
    }

    /**
     * simpler HTTP POST requests using the HttpClient native API
     * @param url
     * @param nvps
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String quickPost(String url, List<NameValuePair> nvps) throws ClientProtocolException, IOException {
        Form form = Form.form();
        for (NameValuePair pair : nvps) {
            form.add(pair.getName(), pair.getValue());
        }
        return Request.Post(url).bodyForm(form.build()).execute().returnContent().toString();
    }

}