/**
 * Alipay.com Inc. Copyright (c) 2004-2010 All Rights Reserved.
 */
package com.cmc.common.utils;

/**
 * @author youmeng
 * @version $Id: Constants.java,v 1.13 2012/02/09 05:36:46 chenc Exp $
 */
public class Constants {
    /**
     * 渠道标志 空 wap 1 wap
     */

    public static final String API_URL = "/api/x.htm";

    public static final String PARAMS = "params";

    /**
     * api constants
     */
    public static final String API_NAME = "api_name";
    public static final String SESSION_ID = "SessionID";
    public static final String API_SESSION_ID = "S";

    public static final String MSG_ERROR = "errorMsg";
    public static final String MSG_IS_SUCCESS = "isSuccess";
    public static final String MSG_RESULT = "Result";
    public static final String MSG_CONTENT = "Msg";
    public static final String MSG_DELIVER_URL = "/msgDeliver.htm";

    /**
     * 管理员Session
     */
    public static final String ADMIN_SESSION = "admin.session";
    public static final String VERIFY_CODE_SESSION = "verify.code.session";
    public static final String VERIFY_TOKEN = "verify.token";
    public static final int VERIFY_TOKEN_LEN = 16;

    /**
     * 软件版本
     */
    public static final String SITE_VSERSION = "1.0.0";

    /**
     * api flow
     */
    public static final String IP = "ip";
    public static final String CLIENT_MOBILE = "client_mobile";
    public static final String CLIENT_MOBILE_SDK = "client_mobile_sdk";
    public static final String CLIENT_CHANNEL = "api_Channel";
    public static final String TMP1 = "tmp1";
    public static final String TMP2 = "tmp2";
    public static final String TMP3 = "tmp3";
    public static final String TMP4 = "tmp4";
    public static final String TMP5 = "tmp5";

    /**
     * push message
     */
    public static final String PUSH_SENDER_ID = "sender_id";
    public static final String PUSH_ACCEPT_ID = "accept_id";
    public static final String PUSH_TYPE = "type";
    public static final String PUSH_CONTENT = "content";
    public static final String PUSH_TARGET_ID = "target_id";
    public static final String PUSH_TMP1 = "tmp1";
    public static final String PUSH_TMP2 = "tmp2";
    public static final String PUSH_TMP3 = "tmp3";
    public static final String PUSH_TMP4 = "tmp4";

    public static final Integer CODE_CACHED_TIME = 30 * 60;
    public static final String WECHAT = "wechat";
    public static final String PATIENT_WECHAT = "patient_wechat";
    // 缓存标识，为防止在同一缓存的不同项目间冲突
    public static final String CACHE_TAG = "lsyy.cache-";

    // 默认图片存放地址
    public static final String DEFAULT_PHOTO = "/images/default/";

    // 生产号
    // public static String APPID = "wx556cfe29e7bcbe8e";
    // public static String APPSECRET = "d9331d4d469ff6652a35b23f37f0da9c";
    // 测试号
    public static String APPID = "wx02c022526f28a6fc";
    public static String APPSECRET = "3e82f1850238a2be5b460ca486c04582";
    // 微信公众号绑定域名
    public static final String DOMAIN_URL = "http://test.yilian.zhuojianchina.com";
    // public static final String DOMAIN_URL =
    // "http://ys.yilian.weixin.zhuojianchina.com";
    // public static final String DOMAIN_URL =
    // "http://wx.yilian.zhuojianchina.com";

    public static final String OAUTH2 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=";
    public static final String WEIXIN = "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
    public static final String WEIXIN1 = "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
    public static final String QR_PACKAGE = "/upload";// 二维码图片存放路径
    public static final String PHOTO_UPLOAD_PACKAGE = "/home/image/";// 图片上传路径
    /**
     * 医生头像的地址、默认图像存储地址、视频图像存储地址前缀。
     */
    // public static final String PHOTO_URL_PREFIX =
    // "http://wx.yilian.zhuojianchina.com/image/"; //生产图片地址
    // public static final String PHOTO_URL_PREFIX =
    // "http://192.168.4.50/upload/";
    /**
     * 放入session中的微信用户信息
     */
    /**
     * 医生头像的地址、默认图像存储地址、视频图像存储地址前缀。
     */
    public static final String PHOTO_URL_PREFIX = "http://image.yxbj.zwjk.com";
    /**
     * cos服务器中用于查看的地址前缀
     */
    public static final String COS_LOOK_PREFIX = "http://cos.yilian.zwjk.com";
    // public static final String PHOTO_URL_PREFIX =
    // "http://wx.yilian.zhuojianchina.com/image/"; //生产图片地址
    // public static final String PHOTO_URL_PREFIX =
    // "http://192.168.4.50/upload/";
    /**
     * 放入session中的微信用户信息
     */
    public static final String WEIXIN_USER = "weixin_user";

    /**
     * 放入session中的PC用户信息
     */
    public static final String PLATFORM_USER = "platform_user";

    public static final Integer ACCESS_TOKEN_TIME = 5 * 60;
    /*
     * public static final String TEMPLATE_ID =
     * "H0bVROGajBbT82Pvdgp0W5kU8haI57jKZnnmsJAt3_U"; // 原模板id
     */
    public static final String TEMPLATE_ID = "f8V5XN214D-UwSSw47Uh22PsUS9F1_ZwzA1ZCUo_v1o";
    // public static final String TEMPLATE_ID =
    // "gCDFnFfiCkXKw6Ze1FdOg09L449o79PIixFWeakSUck"; // 测试消息模板

    // 医学编辑资料图片上传路径
    public static final String WILL_UPLOAD = "/home/image/will_upload/";

    // 是否有病例、课件
    public static String HAVE_CASE = "1";
    public static String NO_CASE = "0";
    public static String HAVE_COURSE = "1";
    public static String NO_COURSE = "0";

    // 是否删除
    public static String NOT_DELETE = "1";
    public static String YES_DELETE = "0";

    // 算术
    public static final double EPS = 1e-7; // 偏移值，避免浮点误差

    // 资源类型（积分）
    public static final String CASES = "1";
    public static final String LIVE = "2";

    /**
     * @Description 请求参数中的 action 的 key 值.
     */
    public static final String REQUEST_PARM_ACTION = "action";

    // 上传类型
    public static final String WEIXIN_UPLOAD = "0";
    public static final String HOUTAI_UPLOAD = "1";
    /**
     * Api 中 session id 的失效时间.
     */
    public static final long API_SESSION_TIMEOUT = 24 * 60 * 60;
    /**
     * Api 中参数 user_id.
     */
    public static final Object API_PARAMS_USER_ID = "user_id";
    /**
     * Api 中参数 user_type.
     */
    public static final Object USER_TYPE = "user_type";

    /**
     * Api 中的传入参数包装key.
     */
    public static final String API_REQUEST_DATA = "requestData";
    /**
     * Api 传入参数 用户id key值
     */
    public static final String API_USER_ID = "user_id";
    /**
     * Api 传入参数 用户手机号码 key值
     */
    public static final String API_USER_PHONE = "phone";
    /**
     * Api 传入参数 用户密码 key值
     */
    public static final String API_USER_PASSWORD = "password";
    /**
     * Api 传入参数 用户登陆渠道标记 key值
     */
    public static final String API_LOGIN_FLAG = "login_flag";
    /**
     * Api 传入参数 用户微信openid key值
     */
    public static final String API_USER_OPENID = "openid";
    /**
     * Api 传入参数 用户校验码 key值
     */
    public static final String API_USER_VALIDATE_CODE = "user_validate_code";
    /**
     * Api 传入参数 用户邀请码 key值
     */
    public static final String API_INVITE_CODE = "user_invite_code";

    /**
     * 提交远程申请后，发送提醒的邦泰手机号
     */
    // 测试
    public static final String BT_PHONE = "18305750375";
    public static final String OTHER_PHONE = "15067190130"; // 其他收到会诊的手机号
    public static final String OTHER_PHONE_SECOND = "18305750375"; // 其他收到会诊的手机号，第二个，运营
    public static final String DEFAULT_ERROR_PHONE = "18305750375"; // 默认错误信息发送手机号
    // 生产
    // public static final String BT_PHONE = "15925640909";
    // public static final String OTHER_PHONE = "18868815663"; //其他收到会诊的手机号
    // public static final String OTHER_PHONE_SECOND = "18657135711";
    // public static final String DEFAULT_ERROR_PHONE = "18305750375"; //
    // 默认错误信息发送手机号

    public static final String FORGET_PASSWORD_SEND_CODE_PREFIX = "user_forget_password";

    /**
     * Api 传入参数 短信验证码 key值
     */
    public static final String API_MESSAGE_CODE = "msg_code";

    public static final String REGIST_SEND_CODE_PREFIX = "user_regist";
    public static final String LOGIN_SEND_CODE_PREFIX = "login_sms";

    public static final String API_USER_INVITE_CODE = "invite_code";
    /**
     * 用户昵称.
     */
    public static final String API_USER_NICK = "user_nick";

    /**
     * 用户头像地址.
     */
    public static final String API_USER_PHOTO_URL = "user_photo_url";

    /**
     * 用户性别 1男 2女 0未知
     */
    public static final String API_USER_SEX = "sex";

    public static final Long HOSPITAL_ID_YILIAN = 0L;

    // 文件表文件类型
    public static final String FILE_TYPE_BL = "1"; // 病理切片
    public static final String FILE_TYPE_PACS = "2"; // 影像图片
    public static final String FILE_TYPE_BL_DIAGNOSIS = "3"; // 病理诊断照片
    public static final String FILE_TYPE_PACS_DIAGNOSIS = "4"; // 影像诊断照片

    /**
     * 微信关注过渡页地址
     */
    // http://mp.weixin.qq.com/s?__biz=MjM5NTk4NDkxNw==&mid=205641343&idx=1&sn=2eef72f82691f85e4cfcae428c267342#rd
    public static final String WEIXIN_FOCUS_URL = "http://mp.weixin.qq.com/s?__biz=MjM5NTk4NDkxNw==&mid=208849732&idx=1&sn=e4e4dd66081de17e6cf8b34f1d69a04c#rd";
    public static final String WEIXIN_FOCUS_URL_1 = "http://mp.weixin.qq.com/s?__biz=MjM5NTk4NDkxNw==&mid=205641343&idx=1&sn=2eef72f82691f85e4cfcae428c267342&scene=1&srcid=1217ypF5QoeUX6sTH1nDH7bH#rd";
    public static final String USER_SEX_UNKNOWN = "0";
    // 远程病理api访问
    // public static final String API_YCBL_URL =
    // "http://192.168.0.13:9000/api/exec/1.htm"; // ip地址
    public static final String API_YCBL_URL = "http://test.bl.zwjk.com/api/exec/1.htm";
    // public static final String API_YCBL_URL =
    // "http://ys.bl.ylxz.zwjk.com/api/exec/1.htm";//演示
    public static final String API_YCBL_INSERT_STATUS = "1"; // 诊断病例已经插入
    // public static final String SLIDE_PRE_URL =
    // "http://service.slide.zwjk.com/viewer2/HTML5/SeadragonViewer.aspx?kfbpath=";
    public static final String SLIDE_PRE_URL = "http://service.zjslide.zwjk.com/slide/viewer.htm?kfbpath=";
    // public static final String THUMBNAIL_PRE_URL =
    // "http://service.slide.zwjk.com/viewer2/ThumnailHandler.ashx?kfbpath=";
    public static final String THUMBNAIL_PRE_URL = "http://service.zjslide.zwjk.com/slide/thumbnail.htm?kfbpath=";
    // public static final String SLIDE_SHOT_PER_URL =
    // "http://service.slide.zwjk.com/viewer2/ScreenShot/"; // 切片截图地址
    // public static final String SLIDE_SHOT_LIST_PER_URL =
    // "http://service.slide.zwjk.com/viewer2/ShotListHandler.ashx?ZJSlideID=";
    // // 切片截图列表地址
    public static final String SLIDE_SHOT_LIST_PER_URL = "http://service.zjslide.zwjk.com/slide/screenshotList.htm?ZJSlideID=";
    public static final String BL_DES_KEY = "kfbucmed88851766kfbucmed88851766";
    // 医链0.6版
    public static final String FACULTY_IS_NEXT = "1"; // 科室存在子级

    public static final String USER_SEX_FEMALE = "女";

    public static final String USER_SEX_MALE = "男";

    // api使用参数
    public static final String ATTENTION_WHERE_APP = "2"; // 关注途径
    // public static final String API_URL_ZHE_YI =
    // "http://ucmed.7766.org:10022/api/exec.htm"; // 浙一的api地址，测试
    public static final String API_URL_ZHE_YI = "http://zyyyapi.ucmed.cn/api/exec.htm"; // 浙一的api地址，生产

    // 患者微信端
    // 测试号
    public static String PATIENT_APPID = "wx2c8549ac364cd43b";
    public static String PATIENT_APPSECRET = "d69611d192d50be96c39497418f02fb3";
    // public static String PATIENT_APPID = "wx4e80ae822c81bb7f";
    // public static String PATIENT_APPSECRET =
    // "d4624c36b6795d1d99dcf0547af5443d";wx2c8549ac364cd43b
    // 生产号
    // public static String PATIENT_APPID = "wxe4cb0d9bfbff2fcd";
    // public static String PATIENT_APPSECRET =
    // "2ffdc6250b3046d829c4e5e8ac6a69f8";
    public static String TEST = "1";// 1测试
    // public static String TEST_OPENID = "o-3ipuH04YMV76RYyeLDzjqaWpLM";// 1测试
    public static String TEST_OPENID = "o-3ipuHJEtlqVSRa-1z9CmZKr8X4";// 1测试
    public static final String PATIENT_DOMAIN_URL = "http://test.yilianhzwx.zhuojianchina.com";
    public static final String PATIENT_SOURCE = "patient_source";
    public static final String MEDICAL_URL = "medical_url";
    public static final String MEDICAL_APPLY = "medical_apply";

    public static final String MEDICAL_APPLY_ID_LIST = "medical_apply_id_list";
    public static final String MEDICAL_APPLY_SIZE = "medical_apply_size";
    public static final String MEDICAL_APPLY_DESCRIPTION = "medical_apply_description";
    public static final String MEDICAL_APPLY_FACULTY_ID = "medical_apply_faculty_id";
    public static final String MEDICAL_APPLY_FACULTY_NAME = "medical_apply_faculty_name";
    public static final String MEDICAL_APPLY_IMG_LIST = "medical_apply_img_list";
    public static final Integer PATIENT_USER_INFO_TIME = 60 * 60;
    // 万象优图参数
    public static final String TX_UPLOAD_IMG_URL = "http://image.cloud.zwjk.com/Api/PicCloud";
    public static final String BUCKETTOKEN = "9043bc70e73005498e95f0e487b467decg";
    public static final String SECRET_KEY = "6zzTNtEFFAj0YmHW1Iu7UESBMSkODreA";
    public static final String CREATOR = "yilian-patient";
    public static final String CREATOR_WEIXIN = "yilian-weixin";
    public static final String IMAGE_QUALITY = "100"; // 图片质量，范围1~100
    public static final String IMAGE_WIDTH = ""; // 固定宽度
    public static final String IMAGE_HEIGHT = "200"; // 固定高度

    public static final String DATE_FORMAT_DELETE = "yyyy-MM-dd HH:mm:ss";

    public static final String HUOTUO_URL = "http://api.ucmed.cn/api/huatuo3.htm";

    // 影像url拼接
    public static final String PACS_URL = "http://pacs.yilian.zhuojianchina.com/Gallery/Index/";
    public static final String PACS_THUMBNAIL_URL = "http://pacs.yilian.zhuojianchina.com/Gallery/StudyThumb/";
    public static final String YCYX_PACS_URL = "http://webpacs.zwjk.com/index.aspx?accno=";

    // app下载地址信息
    // public static final String APP_SHARE_URL =
    // "http://www.zhuojianchina.com/appdownload/515.jhtml?plg_nld=1&plg_uin=1&plg_auth=1&plg_nld=1&plg_usr=1&plg_vkey=1&plg_dev=1";
    // // app下载地址
    public static final String APP_SHARE_URL = "http://test.yilian.zhuojianchina.com/api/showdownloadpage.htm"; // app下载地址

    public static final String APP_SHARE_TITLE = "医链，链接有理想和情怀的医生"; // app下载标题
    public static final String APP_SHARE_CONTENT = "医路漫漫，从未有人更懂你"; // app下载分享内容

    // 玄武短信
    public static final String TORTOISE_SEND_IP = "211.147.239.62";
    public static final String TORTOISE_SEND_PORT = "9080";
    public static final String TORTOISE_GET_PORT = "9070";
    public static final String TORTOISE_USERNAME = "yilian@hzzjkj";
    public static final String TORTOISE_PASSWORD = "Tpx7!p&2oKtMc4!L8";

    public static final String TORTOISE_USERNAME_ZHUOJIAN = "hzzjkj@hzzjkj";
    public static final String TORTOISE_PASSWORD_ZHUOJIAN = "6t!pC6x9eY%sKyi6Z4";

    // pdf转h5以及ppt转图片,cos上传
    // windows系统office上传目录
    // public static final String OFFICE_UPLOAD_PACKAGE = "";
    public static final String OFFICE_UPLOAD_PACKAGE = "/home/office/"; // office上传目录
    public static final String PDF_UPLOAD_PACKAGE = "/home/pdf/"; // office上传目录
    public static final Integer COS_APP_ID = 10001356; // 腾讯云对象存储app_id
    public static final String COS_SECRET_ID = "AKIDFsVM8Q4LGh7qhAzCXn09ND8HnTuzOP5L"; // 腾讯云对象存储secret_id
    public static final String COS_SECRET_KEY = "Ctcm0Gd8sR3LdfVttbiVQon1cE6Th1G6"; // 腾讯云对象存储secret_key
    public static final String BUCKET_NAME = "yilian"; // 腾讯云对象存储bucket
    public static final Integer VALID_TIME = 86400; // sign有效时间，单位秒
    // public static final String COSAPI_CGI_URL = "http://web.file.myqcloud.com/files/v1/"; // cos上传地址
    public static final String COSAPI_CGI_URL = "http://web.file.myqcloud.com/files/v2/"; // COS v4上传地址
    public static final String COS_FOLDER_PATH_PDF = "/pdf/"; // 腾讯云pdf文件保存地址
    public static final String COS_FOLDER_PATH_HTML = "/html/"; // 腾讯云html文件保存地址
    public static final String COS_FOLDER_PATH_PPT = "/ppt/"; // 腾讯云ppt文件保存地址
    public static final String COS_FOLDER_PATH_PLATFORM_FILE = "/platformfile/"; // 腾讯云胃肠平台文件保存地址
    // public static final String PATH_CONVERT_IMAGES = "/images.jpg"; //
    // office转换图片存储位置，images文件夹下，jpg格式图片，名称

    public static final String PHOTO_URL = "photo_url";
    public static final String PHOTO_API_NAME = "api.ylpt.set.bl.qrcode.photo.upload";

    // 远程影像api地址
    // public static final String API_YCYX_URL =
    // "http://yx.ylxz.zwjk.com/api/exec/1.htm"; // 远程影像api生产地址
    public static final String API_YCYX_URL = "http://test.yx.zwjk.com/api/exec/1.htm"; // 远程影像api测试地址

    // app分享标题
    public static final String TITLE_SHARE_WILL_PLAY = "医链即将直播";
    public static final String TITLE_SHARE_LIVE = "医链正在直播";
    public static final String TITLE_SHARE_COURSE = "医链名医讲堂";
    public static final String TITLE_SHARE_CASE = "医链会诊病例";
    public static final String TITLE_SHARE_PATHOLOGY = "医链病理";
    public static final String TITLE_SHARE_PACS = "典型影像";
    public static final String PICTURE_SHARE_CASE = "/weixin/case-cover-pic.jpg"; // 病例分享图标

    // windows api url
    public static final String API_URL_YLPT_WINDOWS = "http://ylian.ppt.zhuojianchina.com/api/exec/ylpt.htm";

    public static final String OCR_DATA = "ocr_data";

    // HttpConnection配置
    public static final String MAX_TOTAL_CONNECTIONS = "400";// 最大连接数
    public static final String WAIT_TIMEOUT = "60000";// 获取连接的最大等待时间
    public static final String MAX_ROUTE_CONNECTIONS = "400";// 每个路由最大连接数
    public static final String CONNECT_TIMEOUT = "10000";// 连接超时时间
    public static final String READ_TIMEOUT = "40000";// 读取超时时间
    // 短信请求配置

    public static final String MSG_URL = "http://msg.zwjk.com:8888/api/exec/1.htm";
    public static final String MSG_KEY = "ZW5sNWVWOWhibVJ5YjJsaw==";

    // 费用中心地址
    public static final String FINANCEURL = "http://182.254.220.236:9092";
    public static final String UNION_ID = "991";
    // 对应费用中心appid
    public static final String APP_ID = "ea12d1b8-5687-456e-b5dc-0c8f7815e7d5";
    // public static final String PLATFORM_URL =
    // "http://192.168.2.169:8080/platform/consultationApi.htm";
    public static final String PLATFORM_PACS_URL = "http://webpacs.zwjk.com:808";// 测试
    // public static final String PLATFORM_PACS_URL =
    // "http://115.159.124.168:808";

    public static final Integer PLATFORM_ID = 51;
    // 拍照上传地址
    public static final String PHOTOUPLOAD_URL = DOMAIN_URL
            + "/weixin/zkhzphoto.htm?params=";
    // public static final String PHOTOUPLOAD_URL = DOMAIN_URL +
    // "/weixin/zkhzphoto.htm?params=";
    public static final String PATHOLOGY_PLATFORM_URL = "http://web.file.myqcloud.com/files/v1/10001356/pathologyplatform/";
    // public static final String PHOTOUPLOAD_Image_URL =
    // "http://test.platform.yilian.zhuojianchina.com/platform/sr.htm?key=";
    public static final String PHOTOUPLOAD_Image_URL = "http://test.platform.yilian.zhuojianchina.com/platform/sr.htm?key=";
    public static final String PATHOLOGY_PLATFORM_FILE = "pathologyplatform";
    public static final String CREATSIGN_URL = "http://test.bl.zwjk.com/cos/cos.htm";
    // 短信模版匹配占位关键字
    public static final String IS_URGENT = "IS_URGENT";
    public static final String APPLYDOC_NAME = "APPLYDOC_NAME";
    public static final String EXPERT_NAME = "EXPERT_NAME";
    public static final String APPLYDOC_PHONE = "APPLYDOC_PHONE";
    public static final String SUFFER_NAME = "SUFFER_NAME";
    public static final String CONSULT_ARRANGE = "CONSULT_ARRANGE";
    // 运营手机号码
    // public static final String OPERATIONS_PHONE = "13777421818";//运营
    public static final String OPERATIONS_PHONE = "15058119106";// 欢欢
    public static final String OPERATIONS_PHONE_SXL = "18868815663";// 沈鑫丽
    // public static final String PLATFORM_URL =
    // "http://ys.platform.yilian.zhuojianchina.com/platform/consultationApi.htm";
    public static final String PLATFORM_URL = "http://test.platform.yilian.zhuojianchina.com/platform/consultationApi.htm";
    // public static final String PLATFORM_URL =
    // "http://platform.yilian.zhuojianchina.com/platform/consultationApi.htm";
    public static final String PLATFORM_USERCENTER_URL = "http://test.yilian120.com";
    // public static final String PLATFORM_USERCENTER_URL =
    // "http://yilian120.com";
    public static final String PLATFORM_APPLY_URL = "http://test.platform.yilian.zhuojianchina.com";
    // public static final String PLATFORM_APPLY_URL =
    // "http://platform.yilian.zhuojianchina.com";
    public static final String ORG_CODE = "yilian120";
    // public static final String PLATFORM_USERCENTER_URL =
    // "http://localhost:13247";
    // public static final String PLATFORM_USERCENTER_URL =
    // "http://localhost:17171"; //演示
    // public static final String PLATFORM_USERCENTER_URL =
    // "http://ys.yilian120.com";//演示

    // 微信开发平台
    public static final String OPEN_APP_ID = "wx410d5811553cc4e3";
    public static final String OPEN_APP_SECRET = "bb7926212c328a387ca9edf65b2fb4bf";
    // 玄武语音短信，请勿用于普通短信
    public static final String VOICE_SEND_IP = "211.147.239.62";
    public static final int VOICE_SEND_PORT = 8450;
    public static final int VOICE_GET_PORT = 8460;
    public static final String VOICE_USERNAME = "zjkjyy@zjkjyy";
    public static final String VOICE_PASSWORD = "aDEuZH2X";

    /* 云学院API地址 */
    public static final String TEST_URL_API_SOUTH = "http://192.168.0.34:8080/api/exec.htm"; // 测试服务器API请求地址，暂时缺少对应的生产地址

    public static final String API_DOMAIN_URL = "http://test.api.yilian.zhuojianchina.com";// 测试
    // public static final String API_DOMAIN_URL =
    // "http://api.yilian.zhuojianchina.com";//生产
    // public static final String API_DOMAIN_URL = "http://localhost:12345/";//
}
