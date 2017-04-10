package com.cmc.demo.javase.newfeature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmc.common.utils.FileUtils;

/**
 * Java SE 新特性
 * <p>
 * 待办：Java8新特性 尤其是Lambda表达式
 * </p>
 * @author Thomas Lee
 * @version 2017年4月3日 下午7:16:01
 */
public class JavaSENewFeature {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    /**
     * try-with-resource语句
     * <p>
     * ARM（Automatic Resource Management，自动资源管理）;
     * 大多数情况下，当忘记关闭资源或因资源耗尽出现运行时异常时，我们只是用finally子句来关闭资源。
     * 这些异常很难调试，我们需要深入到资源使用 的每一步来确定是否已关闭。
     * 因此，Java 7用try-with-resources进行了改进：在try子句中能创建一个资源对象，
     * 当程序的执行完try-catch之后，运行环境自动关闭资源。
     * </p>
     * @param fromFile 源文件
     * @param toFile 目标文件
     * @return 是否复制成功
     * @author Thomas Lee
     * @version 2017年3月28日 下午2:59:41
     */
    public static boolean copyWithARM(File fromFile, File toFile) {
        try (FileInputStream fileInputStream = new FileInputStream(fromFile); FileOutputStream fileOutputStream = new FileOutputStream(toFile)) {
            byte[] buffer = new byte[1024];
            int length = 0;
            while (-1 != (length = fileInputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, length);
            }
            fileOutputStream.flush();
            return true;
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
            return false;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

}