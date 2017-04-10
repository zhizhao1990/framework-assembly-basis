package com.cmc.j2se;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmc.common.utils.FileUtils;
import com.cmc.demo.interview.InterviewProgramming;

/**
 * 杂项测试
 * @author Thomas Lee
 * @version 2017年3月23日 上午9:44:07
 */
public class SundryTest {

    private static final Logger LOG = LoggerFactory.getLogger(SundryTest.class);

    /**
     * 测试UUID.
     * <p>
     * UUID长度为36. 
     * </p>
     * @author Thomas Lee
     * @version 2017年3月27日 下午1:52:52
     */
    @Test
    public void testUUID() {
        String uuid = UUID.randomUUID().toString();
        int length = uuid.length();
        LOG.info("UUID为：" + uuid);
        LOG.info("UUID的长度是：" + String.valueOf(length));
        uuid = uuid.replace("-", "");
        length = uuid.length();
        LOG.info(uuid);
        LOG.info("UUID的长度是：" + String.valueOf(length));
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
    @Test
    public void testBreak() {
        A: for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(j);
                if (i > 5) {
                    break A;
                }
            }
        }
    }

    /**
     * 测试java.utils.Collections，对集合进行查找、排序和线程安全等操作。
     * @author Thomas Lee
     * @version 2017年3月27日 下午9:39:08
     */
    @Test
    public void testCollections() {
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        Collections.binarySearch(list, "key");
        Collections.sort(list);
        Collections.synchronizedList(list);
    }

    /**
     * 测试文件复制
     * @author Thomas Lee
     * @version 2017年3月28日 上午11:12:59
     */
    @Test
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
    @Test
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
    @Test
    public void testGetAllFilenames() {
        File file = new File("F:/individual/workspace/framework-assembly-basis/file");
        List<String> filenames = InterviewProgramming.getAllFilenames(file, new ArrayList<String>());
        Iterator<String> iFilenames = filenames.iterator();
        while (iFilenames.hasNext()) {
            LOG.info(iFilenames.next());
        }
    }

    @Test
    public void testTMP() {
        int a = 10;
        this.tmp(a);
        System.out.println(a);
    }
    
    private void tmp(int a){
        a = 6;
    }
    
}