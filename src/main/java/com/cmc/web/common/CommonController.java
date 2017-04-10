package com.cmc.web.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmc.common.constants.AjaxGeneralResult;
import com.cmc.common.constants.RememberMe;
import com.cmc.common.utils.CaptchaUtil;
import com.cmc.common.utils.ExcelUtil;

/**
 * 公共控制器
 * <p>
 * 存放控制器用到的公共部分
 * </p>
 * @author Thomas Lee
 * @version 2017年3月15日 下午5:59:42
 * @version 2016年11月20日 下午8:12:08
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {

    private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    /** Cookie过期时间 */
    private static final int COOKIE_MAX_AGE = 7 * 24 * 60 * 60;

    /**
     * 文件下载
     * @author Thomas Lee
     * @since 2016年12月21日 下午2:40:00
     */
    @RequestMapping(value = "downloadfile.htm", method = RequestMethod.POST)
    public void downloadFile(HttpServletResponse res) throws IOException {
        String[][] data = new String[3][3];
        data[0][0] = "name";
        data[0][1] = "sex";
        data[0][2] = "age";
        for (int x = 1; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                data[x][y] = String.valueOf(x * y);
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = ExcelUtil.create(data);
        ServletOutputStream servletOutputStream = res.getOutputStream();

        res.reset();
        // 若类型为“attachment”，则是以附件的形式进行下载，若类型为“inline”，则系统会使用对应格式的默认程序打开文件
        res.addHeader("Content-Disposition", "attachment;filename=statistics.xls");
        res.addHeader("Content-Length", "");
        res.setContentType("application/octet-stream");
        servletOutputStream.write(byteArrayOutputStream.toByteArray());
        servletOutputStream.flush();
        servletOutputStream.close();
        byteArrayOutputStream.close();
    }

    /**
     * “记住我”功能
     * @author Thomas Lee
     * @since 2016年12月28日 下午3:52:41
     */
    @ResponseBody
    @RequestMapping(value = "rememberme.htm", method = RequestMethod.POST)
    public AjaxGeneralResult rememberMe(HttpServletRequest req, HttpServletResponse res) {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String rememberMeEnum = req.getParameter("rememberMeEnum");

            RememberMe rememberMe = RememberMe.parseCode(rememberMeEnum);
            if (null == rememberMe) {
                LOG.error("记住我功能的枚举数值超出范围！");
                return AjaxGeneralResult.getFailureRst();
            }

            Cookie cUsername = new Cookie("username", username);
            Cookie cPassword = new Cookie("password", password);
            if (RememberMe.YES.equals(rememberMe)) {
                cUsername.setMaxAge(COOKIE_MAX_AGE);
                cPassword.setMaxAge(COOKIE_MAX_AGE);
                cUsername.setPath(req.getContextPath() + "/");
                cPassword.setPath(req.getContextPath() + "/");
            } else {
                cUsername.setMaxAge(-1);
                cPassword.setMaxAge(-1);
            }
            res.addCookie(cUsername);
            res.addCookie(cPassword);

            return AjaxGeneralResult.getSuccessRst();
        } catch (Exception e) {
            LOG.error("", e);
            return AjaxGeneralResult.getFailureRst();
        }
    }

    /**
     * 获取图片验证码
     * @param request 请求信息
     * @param response 返回信息，包含图片信息
     * @author Thomas Lee
     * @version 2017年3月15日 下午5:55:55
     */
    @RequestMapping(value = "getcaptcha.htm", method = RequestMethod.GET)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        // 生成图片验证码图片，并返回给页面
        OutputStream os = null;
        try {
            // 获取验证码
            String code = CaptchaUtil.get4DigitsAndLetters();
            // 生成图片验证码图片
            BufferedImage image = CaptchaUtil.getCodeImage(code);
            // 设置content-type消息头，告诉浏览器返回的是图片（告诉浏览器以image/jpeg的形式、默认编码接收文件内容）
            response.setContentType("image/jpeg");
            os = response.getOutputStream();
            // 对原始图片image（BufferedImage）按照指定的压缩算法JPEG进行压缩，并且将压缩之后的数据输出到指定的流
            javax.imageio.ImageIO.write(image, "jpeg", os);
            os.flush();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

}