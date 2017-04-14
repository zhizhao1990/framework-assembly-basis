package com.cmc.demo.uuid;

import java.util.UUID;

/**
 * UUID Demo.
 * @author Thomas Lee
 * @version 2017年4月14日 下午2:09:27
 */
public class UUIDDemo {

    public static void main(String[] args) {
        UUIDDemo.testUUIDLength();
    }

    /**
     * 测试UUID长度
     * @author Thomas Lee
     * @version 2017年4月14日 下午2:09:05
     */
    public static void testUUIDLength() {
        String uuid = UUID.randomUUID().toString();
        int length = uuid.length();

        out("UUID为：" + uuid);
        out("UUID的长度是：" + String.valueOf(length));
        uuid = uuid.replace("-", "");
        length = uuid.length();
        out(uuid);
        out("UUID的长度是：" + String.valueOf(length));
    }

    /**
     * 打印到控制台
     * @param content 内容
     * @author Thomas Lee
     * @version 2017年4月14日 下午2:09:14
     */
    public static void out(String content) {
        System.out.println(content);
    }

}