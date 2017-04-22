package com.cmc.demo.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 面试编程题目汇总
 * @author Thomas Lee
 * @version 2017年3月28日 下午3:36:32
 * @version 2017年4月3日 下午6:26:23
 */
public class InterviewProgramming {

    private static final Logger LOG = LoggerFactory.getLogger(InterviewProgramming.class);

    // 大多都是工具方法，所以InterviewProgramming类也当做一个工具类使用.
    private InterviewProgramming() {
    }

    public static void main(String[] args) {
        List<Integer> nums = new LinkedList<Integer>();
        nums.add(0, 1);
        nums.add(1, 101);
        nums.add(2, 2);
        nums.add(3, 3);
        nums.add(4, 100);
        nums.add(5, 4);
        nums.add(6, 98);
        int rst = maxIncreasingSubsequenceSum(nums);
        System.out.println(rst);
    }

    /**
     * 文件复制
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
     * 统计文件中字符串的次数.
     * @param file 目标文件.
     * @param word 目标字符串.
     * @return 目标字符串在目标文件中出现的次数，-1则代表发生异常.
     * @author Thomas Lee
     * @version 2017年3月28日 下午3:47:42
     */
    public static int countWordInFile(File file, String word) {
        int counter = 0;
        try (FileReader fileReader = new FileReader(file)) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line;
                while (null != (line = bufferedReader.readLine())) {
                    int index;
                    while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
                        counter++;
                        line = line.substring(index + word.length());
                    }
                }
            }
            return counter;
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
            return 0;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 获取指定文件下的所有文件名称.
     * <p>
     * 使用到了递归思想.
     * </p>
     * @param targetFile 指定文件
     * @return List<String> 文件列表
     * @author Thomas Lee
     * @version 2017年3月28日 下午4:43:51
     */
    public static List<String> getAllFilenames(File targetFile, List<String> filenames) {
        File[] files = targetFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getAllFilenames(file, filenames);
            }
            filenames.add(file.getName());
        }
        return filenames;
    }

    /**
     * 最大递增子序列的和.
     * <p>
     * 问题描述：
     * 已知一个含有n个正整数的数组，写一个程序能让其找到已知数组递增子序列的最大和。
     * 例如：
     * 如果输入是：{1, 101, 2, 3, 100, 4, 5}，那么输出应该就是106 (1 + 2 + 3 + 100)，
     * 如果输入是：{3, 4, 5, 10}，那么输出是22 (3 + 4 + 5 + 10)，
     * 如果输入是 {10, 5, 4, 3}，那么输出就是10。
     * </p>
     * <p>
     * 解决思路：
     * 计算出所有的递增子序列，然后取最大值.
     * PS：
     * 使用到递归算法.
     * </p>
     * <p>
     * 问题扩展：
     * </p>
     * @author Thomas Lee
     * @version 2017年4月4日 上午12:05:16
     * @param nums 序列.
     * @return 最大递增子序列的和.
     */
    public static int maxIncreasingSubsequenceSum(List<Integer> nums) {
        int sum = 0;
        int lastCompared = 0;
        int index = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) > lastCompared) {
                lastCompared = nums.get(i);
                sum += lastCompared;
                index = i;
            }
        }

        if (!CollectionUtils.isEmpty(nums)) {
            nums.remove(index);
            int rst = maxIncreasingSubsequenceSum(nums);
            return sum > rst ? sum : rst;
        } else {
            return 0;
        }
    }

    /**
     * 排序算法
     * 参考:
     * 简单排序:
     *     SimpleSelectionSorter
     *     StraightInsertSorter
     *     BubbleSorter
     *     
     * 复杂排序：
     *     QuickSorter
     *     BinarySortNode
     *    （ShellSorter和HeapSorter可以知道思想就行）
     */

    /**
     * 数据结构
     * 参考：
     * Collection: 
     *     CollectionDemo
     *     
     * Map: 
     *     MapDemo
     */

    /**
     * 查找算法
     * 二分法：
     *     BinarySearcher 
     */

    /**
     * Socket
     * 参考：
     * TCP：
     *     TCPServer
     *     TCPClient
     *    （MultiThreadTCPServer是一个多线程TCP Socket）
     *    
     * UDP：
     *     UDPServer
     *     UDPClient
     */

    /**
     * 线程
     * 参考：
     * Thread方式：
     *     MyThread
     *     
     * Runnable方式：
     *     MyRunner
     */

}