package com.cmc.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * File utilities
 * @author Thomas Lee
 * @version 2016/08/12 20:35
 * @version 2017年3月28日 上午9:59:20
 */
public class FileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

    /**
     * 写入内容到指定文件
     * @param file 输出文件
     * @param content 内容
     */
    public static void write(File file, String content) {
        FileOutputStream fileOutputStream = null;
        PrintStream printStream = null;
        try {
            fileOutputStream = new FileOutputStream(file, true);
            printStream = new PrintStream(fileOutputStream, true);
            System.setOut(printStream);
            System.out.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                printStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 存储MultipartFile文件
     * @param path
     * @param file
     * @return
     */
    public static boolean write(String path, MultipartFile file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(path), true);
            fos.write(file.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                fos = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] arsgs) {
        String filePath = "C:\\Users\\ucmed\\Desktop\\text.tmp";

        File file = new File(filePath);
        String content = "content for testing.";
        write(file, content);
    }

    /**
     * 文件复制普通方法
     * @param fromFile 源文件
     * @param toFile 目标文件
     * @return 是否复制成功
     * @author Thomas Lee
     * @version 2017年3月28日 上午10:03:29
     */
    public static boolean copy(File fromFile, File toFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(fromFile);
            fileOutputStream = new FileOutputStream(toFile);

            byte[] buffer = new byte[1024];
            int length = 0;
            while (-1 != (length = fileInputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, length);
            }

            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
            return false;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return false;
        } finally {
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return true;
    }

    /**
     * 使用ARM（Automatic Resource Management，自动资源管理）语句，使用try-with-resources语句进行文件复制
     * @param fromFile
     * @param toFile
     * @return
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

    /**
     * 通过NIO进行复制文件
     * @param fromFile
     * @param toFile
     * @return
     * @author Thomas Lee
     * @version 2017年3月28日 下午3:16:21
     */
    public static boolean copyWithNIO(File fromFile, File toFile) {
        // 可以把下面的两个ARM语句合并为一个语句块：try (FileInputStream fileInputStream = new FileInputStream(fromFile); FileOutputStream fileOutputStream = new FileOutputStream(toFile)) {
        try (FileInputStream fileInputStream = new FileInputStream(fromFile)) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(toFile)) {
                FileChannel inFileChannel = fileInputStream.getChannel();
                FileChannel outFileChannel = fileOutputStream.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (-1 != inFileChannel.read(buffer)) {
                    buffer.flip();
                    outFileChannel.write(buffer);
                    buffer.clear();
                }
                return true;
            }
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
            return false;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

}