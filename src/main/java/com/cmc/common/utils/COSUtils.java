/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.cmc.common.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.request.UploadFileRequest;

/**
 * @author chengwu cos Demo代码
 */
public class COSUtils {

    public static void main(String[] args) throws Exception {
        // 设置用户属性, 包括appid, secretId和SecretKey
        // 这些属性可以通过cos控制台获取(https://console.qcloud.com/cos)
        int appId = 10001356;
        String secretId = "AKIDFsVM8Q4LGh7qhAzCXn09ND8HnTuzOP5L";
        String secretKey = "Ctcm0Gd8sR3LdfVttbiVQon1cE6Th1G6";
        // 初始化cosClient
        COSClient cosClient = new COSClient(appId, secretId, secretKey);
        // 设置要操作的bucket
        String bucketName = "yilian";

        ///////////////////////////////////////////////////////////////
        // 文件操作                                                      //
        ///////////////////////////////////////////////////////////////
        // 1. 上传文件(默认不覆盖)
        // 将本地的local_file_1.txt上传到bucket下的根分区下,并命名为sample_file.txt
        // 默认不覆盖, 如果cos上已有文件, 则返回错误
        String cosFilePath = "/sample_test_lcb.jpg";
        String localFilePath1 = "src/test/resources/632154.jpg";
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, cosFilePath, localFilePath1);
        String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
        System.out.println("upload file ret:" + uploadFileRet);
        cosClient.shutdown();
    }

}