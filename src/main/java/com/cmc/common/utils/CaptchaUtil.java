package com.cmc.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码工具
 * 
 * <p>目前只提供两种验证码生成方式，有需要继续迭代。
 * 
 * @author Thomas Lee
 * @since 2016年12月28日 上午11:48:02
 * @reference 图形验证码 http://www.cnblogs.com/Leo_wl/p/6002128.html
 */
public class CaptchaUtil {

    /** 验证码字符集（大写小字母 + 数字） */
    private static final char[] CAPTCHA_SETS = { 
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /**
     * 获取位数为四的数字验证码
     * 
     * @author Thomas Lee
     * @since 2016年12月28日 上午11:51:21
     */
    public static String get4Digits() {
        String rst = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            rst += random.nextInt(10);
        }
        return rst;
    }

    /**
     * 获取位数为四的数字和字母验证码
     * 
     * @author Thomas Lee
     * @since 2016年12月28日 下午3:31:30
     */
    public static String get4DigitsAndLetters() {
        String rst = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            rst += CAPTCHA_SETS[random.nextInt(62)];
        }
        return rst;
    }

    public static void main(String[] args) {
    }

    /**
     * 生成图片验证码
     * <p>使用AWT</p>
     * @param code 验证码内容
     * @return 生成的图片验证码图片
     */
    public static BufferedImage getCodeImage(String code) {
        // step1, 创建一个内存映像对象(画布) BufferedImage(宽度,高度,类型)
        BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
        // step2, 获得一个画笔
        Graphics graphics = image.getGraphics();
        // step3, 给笔设置颜色
        Random random = new Random();
        graphics.setColor(new Color(239, 248, 255));
        // step4, 给画布设置背景颜色 
        graphics.fillRect(0, 0, 80, 30);
        // step5, 绘图
        graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        // Font(字体,风格,大小)
        graphics.setFont(new Font(null, Font.ITALIC, 22));
        // drawString(String,x,y) x,y是左下角的坐标
        graphics.drawString(code, 5, 23);
        // step6, 加一些干扰线
        for (int i = 0; i < 6; i++) {
            graphics.drawLine(random.nextInt(80), random.nextInt(30), random.nextInt(80), random.nextInt(30));
        }
        return image;
    }

}