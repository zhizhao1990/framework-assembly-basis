package com.cmc.demo.breakandcontinue;

/**
 * break and continue demo.
 * @author Thomas Lee
 * @version 2017年4月14日 下午2:11:10
 */
public class BreakAndContinueDemo {

    public static void main(String[] args) {
        testBreak();
    }

    /**
     * 测试带标签的break和continue语句.
     * <p>
     * Java中支持带标签的break和continue语句，
     * 作用有点类似于C和C++中的goto语句，但是就像要避免使用goto一样，
     * 应该避免使用带标签的break和continue，因为它不会让你的程序变得更优雅，
     * 很多时候甚至有相反的作用，所以这种语法其实不知道更好
     * </p>
     * @author Thomas Lee
     * @version 2017年3月27日 下午1:52:49
     */
    public static void testBreak() {
        A: for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(j);
                if (i > 5) {
                    break A;
                }
            }
        }
    }

}