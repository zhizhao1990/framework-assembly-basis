package com.cmc.demo.regularexpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 demo.
 * <p>
 * 参考：
 * http://www.jb51.net/tools/zhengze.html
 * http://www.runoob.com/regexp/regexp-tutorial.html
 * </p>
 * @author Thomas Lee
 * @version 2017年4月14日 下午3:10:37
 */
class RegExpDemo {

    public static void main(String[] args) {
        testRegExpress();
    }

    /**
     * 测试正则表达式.
     * @author Thomas Lee
     * @version 2017年4月14日 下午3:11:26
     */
    public static void testRegExpress() {
        String str = "北京市(朝阳区)(西城区)(海淀区)";
        Pattern p = Pattern.compile(".*?(?=\\()");
        Matcher m = p.matcher(str);
        if (m.find()) {
            System.out.println(m.group());
        }
    }

}