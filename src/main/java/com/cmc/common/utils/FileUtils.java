package com.cmc.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cmc.demo.interview.InterviewProgramming;

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

    /**
     * 测试文件复制
     * @author Thomas Lee
     * @version 2017年3月28日 上午11:12:59
     */
    public void testFileCopy() {
        // 特别注意，SundryTest.class.getClassLoader()获取的是运行时的类，那.getResource("fromFile.file").getFile()获取的也是相应的运行时候的路径，也就是maven target目录下面的路径
        // File fromFile = new File(SundryTest.class.getClassLoader().getResource("fromFile.file").getFile());
        // File toFile = new File(SundryTest.class.getClassLoader().getResource("toFile.file").getFile());
        File fromFile = new File("C:\\Users\\ucmed\\Desktop\\fromFile.txt");
        File toFile = new File("C:\\Users\\ucmed\\Desktop\\toFile.txt");
        boolean rst = FileUtils.copyWithNIO(fromFile, toFile);
        LOG.info(rst + "");
    }

    /**
     * 测试字符串在文件中出现的次数.
     * @author Thomas Lee
     * @version 2017年3月28日 下午4:15:07
     */
    public void testCountWordInFile() {
        File file = new File("F:/individual/workspace/framework-assembly-basis/file/fromFile.txt");
        String word = "hello";
        LOG.info(String.valueOf(InterviewProgramming.countWordInFile(file, word)));
    }

    /**
     * 测试列出指定目录下所有的文件名称
     * @author Thomas Lee
     * @version 2017年3月28日 下午4:56:45
     */
    public void testGetAllFilenames() {
        File file = new File("F:/individual/workspace/framework-assembly-basis/file");
        List<String> filenames = InterviewProgramming.getAllFilenames(file, new ArrayList<String>());
        Iterator<String> iFilenames = filenames.iterator();
        while (iFilenames.hasNext()) {
            LOG.info(iFilenames.next());
        }
    }

}