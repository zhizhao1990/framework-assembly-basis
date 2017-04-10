package com.cmc.common.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

    private static final String PATH_UPLOAD = "C:\\Users\\ucmed\\Desktop\\images";
    private static final String PATH_TEMP = "C:\\Users\\ucmed\\Desktop\\images\\temp";
    private static final Integer BYTE = 1024;
    private static final Integer THRESHOLD = 4 * BYTE;
    private static final Integer FILE_SIZE_MAX = 40 * BYTE * BYTE;

    /**
     * Uploading file implemented via Apache Commons FileUpload project.
     * 
     * @param req HttpServletRequest
     * @return filename
     * @version 2016年09月18日 13:38
     */
    public static String upload(HttpServletRequest req) {
        File fileUpload = new File(PATH_UPLOAD);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        File fileTempPath = new File(PATH_TEMP);
        if (!fileTempPath.exists()) {
            fileTempPath.mkdirs();
        }

        try {
            new String("fa".getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD);
        factory.setRepository(fileTempPath);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(FILE_SIZE_MAX);
        String filename = null;
        try {
            for (FileItem fileItem : upload.parseRequest(req)) {
                String itemName = fileItem.getName();
                if (!StringUtils.isBlank(itemName)) {
                    fileItem.write(new File(PATH_UPLOAD, itemName));
                    filename = itemName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;
    }

    /**
     * Uploading file, implemented via Spring project (CommonsMultipartResolver, supporting Apache Commons FileUpload Project).
     * 
     * @param file
     * @return
     * @author Thomas
     * @since 2016年11月20日 下午7:15:02
     */
    public static String uploadViaSpring(MultipartFile file) {
        File fileUpload = new File(PATH_UPLOAD);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        return FileUtils.write(PATH_UPLOAD + "\\" + file.getOriginalFilename(), file) ? file.getOriginalFilename() : null;
        // return FileUtils.write(PATH_UPLOAD + "\\" + "1.file", file) ? file.getOriginalFilename() : null;
    }

}