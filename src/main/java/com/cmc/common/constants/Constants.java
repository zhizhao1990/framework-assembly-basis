package com.cmc.common.constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    private static final Logger LOG = LoggerFactory.getLogger(Constants.class);

    /** 用户session的key */
    public static final String USER_SESSION = "user_session";
    public static final String PHOTO_UPLOAD_PACKAGE = null;
    public static final String BUCKETTOKEN = null;
    public static final String CREATOR_WEIXIN = null;
    public static final String SLIDE_SHOT_LIST_PER_URL = null;
    public static final String BL_DES_KEY = null;
    public static final String SLIDE_PRE_URL = null;
    public static final String THUMBNAIL_PRE_URL = null;
    public static final String PHOTO_URL_PREFIX = null;

    public static String TMP;

    static {
        TMP = Constants.getConfigProperties("tmp");
        LOG.info(TMP);
    }

    public static String getConfigProperties(String property) {
        InputStream in = null;
        try {
            // in = new FileInputStream(new
            // File(req.getServletContext().getRealPath("WEB-INF\\classes\\config.properties")));
            in = Constants.class.getClassLoader().getResourceAsStream("config.properties");
            // Constants.class.getClassLoader().getResource("").getFile();
            Properties pros = new Properties();
            pros.load(in);
            return pros.getProperty(property);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

}