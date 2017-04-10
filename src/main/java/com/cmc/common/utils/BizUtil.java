package com.cmc.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务工具类
 * <p>
 * 获取重播业务虚拟点击量算法
 * <li>指数算法 Index Algorithm，代码取名为IndexAlgo
 * <li>倍数算法 Multiple Algorithm, 代码取名为MultiAlgo
 * @author HT-LiChuanbin
 * @since 2016年12月12日 下午5:00:05
 */
public class BizUtil {

    private static final Logger LOG = LoggerFactory.getLogger(BizUtil.class);

    private static final int SIZE_TEST = 1000000;

    private BizUtil() {
    }

    /******************************************* Multiple Algorithm Start *******************************************************/

    /** 资源ID通过IdHandler加密后首字母对应的虚拟点击量上限 */
    private static final Map<String, Integer> NUMS = new HashMap<String, Integer>();

    private static int[] extremums = { 9000, 9500, 10000, 10500, 11000, 11500, 12000, 12500 };

    /** 共有64种，概率性均匀分布在extremums范围内 */
    static {
        NUMS.put("a", extremums[0]);
        NUMS.put("b", extremums[1]);
        NUMS.put("c", extremums[2]);
        NUMS.put("d", extremums[3]);
        NUMS.put("e", extremums[4]);
        NUMS.put("f", extremums[5]);
        NUMS.put("g", extremums[6]);
        NUMS.put("h", extremums[7]);
        NUMS.put("i", extremums[0]);
        NUMS.put("j", extremums[1]);
        NUMS.put("k", extremums[2]);
        NUMS.put("l", extremums[3]);
        NUMS.put("m", extremums[4]);
        NUMS.put("n", extremums[5]);
        NUMS.put("o", extremums[6]);
        NUMS.put("p", extremums[7]);
        NUMS.put("q", extremums[0]);
        NUMS.put("r", extremums[1]);
        NUMS.put("s", extremums[2]);
        NUMS.put("t", extremums[3]);
        NUMS.put("u", extremums[4]);
        NUMS.put("v", extremums[5]);
        NUMS.put("w", extremums[6]);
        NUMS.put("x", extremums[7]);
        NUMS.put("y", extremums[0]);
        NUMS.put("z", extremums[1]);
        NUMS.put("A", extremums[2]);
        NUMS.put("B", extremums[3]);
        NUMS.put("C", extremums[4]);
        NUMS.put("D", extremums[5]);
        NUMS.put("E", extremums[6]);
        NUMS.put("F", extremums[7]);
        NUMS.put("G", extremums[0]);
        NUMS.put("H", extremums[1]);
        NUMS.put("I", extremums[2]);
        NUMS.put("J", extremums[3]);
        NUMS.put("K", extremums[4]);
        NUMS.put("L", extremums[5]);
        NUMS.put("M", extremums[6]);
        NUMS.put("N", extremums[7]);
        NUMS.put("O", extremums[0]);
        NUMS.put("P", extremums[1]);
        NUMS.put("Q", extremums[2]);
        NUMS.put("R", extremums[3]);
        NUMS.put("S", extremums[4]);
        NUMS.put("T", extremums[5]);
        NUMS.put("U", extremums[6]);
        NUMS.put("V", extremums[7]);
        NUMS.put("W", extremums[0]);
        NUMS.put("X", extremums[1]);
        NUMS.put("Y", extremums[2]);
        NUMS.put("Z", extremums[3]);
        NUMS.put("0", extremums[4]);
        NUMS.put("1", extremums[5]);
        NUMS.put("2", extremums[6]);
        NUMS.put("3", extremums[7]);
        NUMS.put("4", extremums[0]);
        NUMS.put("5", extremums[1]);
        NUMS.put("6", extremums[2]);
        NUMS.put("7", extremums[3]);
        NUMS.put("8", extremums[4]);
        NUMS.put("9", extremums[5]);
        NUMS.put("_", extremums[6]);
        NUMS.put("-", extremums[7]);
    }

    /**
     * 获取业务点击量（即通过后台算法运算之后的虚拟点击量）
     * <p>
     * y=(x*x + 110*x)/100 eg. 实际点击量 虚拟点击量 498 3028 499 3039 947 100010
     * </p>
     * @param id
     *            IdHandler加密过后的id（UUID和整数都需要加密）
     * @param viewCount
     *            实际点击量
     * @return 虚拟点击量
     * @author HT-LiChuanbin
     * @since 2016年12月12日 下午3:56:25
     */
    /*
     * public static long getBizViewCount(String id, int viewCount) { long
     * bizViewCount = (long) (viewCount * (viewCount + 110) / 100.0); String
     * initial = id.substring(0, 1); // default value int criticalBizViewCount =
     * 10000; Iterator<Entry<String, Integer>> ieNums = nums.entrySet()
     * .iterator(); while(ieNums.hasNext()) { Entry<String, Integer> eNum =
     * ieNums.next(); if(StringUtils.equals(initial, eNum.getKey())) {
     * criticalBizViewCount = eNum.getValue(); } } long criticalX =
     * getCriticalViewCount(criticalBizViewCount); return bizViewCount >
     * criticalBizViewCount ? criticalBizViewCount + (viewCount - criticalX) :
     * bizViewCount; }
     */

    /** 获取合适的“波动”（围绕函数模型的波动）范围 @deprecated 如果需要下版本更新 */
    @SuppressWarnings("unused")
    private static long getProperRandomDigit(int viewCount) {
        // 函数模型求导计算出增长速率
        long criticalRange = (viewCount + 55) / 50;
        Random random = new Random();
        return random.nextInt((int) (criticalRange));
    }

    /**
     * 获取对应虚拟点击量在数据库对应的实际点击量的值 eg.10000点击量（虚拟）对应（946.xxx，取947）947（实际点击量）
     * @param bizViewCount
     *            虚拟点击量
     * @return
     * @author HT-LiChuanbin
     * @since 2016年12月12日 下午3:58:23
     */
    @SuppressWarnings("unused")
    private static long getCriticalViewCount(long bizViewCount) {
        return (long) Math.sqrt(100 * bizViewCount + 55 * 55) - 55 + 1;
    }

    public static void main(String[] args) {
        // System.out.println(getBizViewCount("0t3AQBZ4W7OLrkeNZ8s5Ng", 2725));
        // getNumsLetterFrequencyEncyptedByIdHandler();
        System.out.println(Integer.MAX_VALUE);
    }

    private class KeyValue implements Comparable<KeyValue> {
        private String key;
        private int value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(KeyValue obj) {
            return this.key.toCharArray()[0] - obj.key.toCharArray()[0];
        }

    }

    @SuppressWarnings("unused")
    private static void getNumsLetterFrequencyEncyptedByIdHandler() {
        List<BizUtil.KeyValue> lData = new ArrayList<BizUtil.KeyValue>();

        for (int i = 0; i < SIZE_TEST; i++) {
            String initial = IdHandler.idEncrypt(i).substring(0, 1);
            KeyValue keyValue = new BizUtil().new KeyValue();
            keyValue.setKey(initial);
            keyValue.setValue(0);
            lData.add(keyValue);
        }

        Set<BizUtil.KeyValue> rstData = new TreeSet<BizUtil.KeyValue>();
        for (int i = 0; i < lData.size(); i++) {
            String key = lData.get(i).getKey();
            if (has(rstData, key)) {
                continue;
            }
            for (int j = i; j < lData.size(); j++) {
                int count = lData.get(i).getValue();
                if (StringUtils.equals(key, lData.get(j).getKey())) {
                    count = count + 1;
                    KeyValue keyValue = getByKey(rstData, key);
                    if (null == keyValue) {
                        KeyValue keyValueTmp = new BizUtil().new KeyValue();
                        keyValueTmp.setKey(key);
                        keyValueTmp.setValue(count);
                        rstData.add(keyValueTmp);
                    } else {
                        keyValue.setValue(keyValue.getValue() + 1);
                        rstData.add(keyValue);
                    }
                }
            }
        }

        Iterator<KeyValue> iRstData = rstData.iterator();
        int dataSize = rstData.size();
        System.out.println("非重复字符总数：" + dataSize + "，测试基数：" + SIZE_TEST);
        while (iRstData.hasNext()) {
            KeyValue keyValue = iRstData.next();
            System.out.println(keyValue.getKey() + ": " + keyValue.getValue() * 100.0 / SIZE_TEST + "%" + ",期望总比例：" + dataSize * keyValue.getValue() * 100.0 / SIZE_TEST + "%");
        }
    }

    private static KeyValue getByKey(Set<KeyValue> rstData, String key) {
        Iterator<KeyValue> iRstData = rstData.iterator();
        while (iRstData.hasNext()) {
            KeyValue keyValue = iRstData.next();
            if (StringUtils.equals(key, keyValue.getKey())) {
                return keyValue;
            }
        }
        return null;
    }

    private static boolean has(Set<KeyValue> rstData, String key) {
        Iterator<KeyValue> iData = rstData.iterator();
        while (iData.hasNext()) {
            KeyValue keyValue = iData.next();
            if (StringUtils.equals(key, keyValue.getKey())) {
                return true;
            }
        }
        return false;
    }

    /******************************************* Multiple Algorithm End *******************************************************/

    /******************************************* Index Algorithm Start *******************************************************/

    /** 区间临界值数组 */
    private static final int[] MULTIALGO_CRITICAL = { 0, 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 150, 200, 250, 300, 400, 500, 700, 1000, 1500, 2000, 3000, 4000, 5000, Integer.MAX_VALUE };

    private static final String COMMON_ERROR = "！出现BUG，请尽快修复";
    private static final List<BizUtil.MultiAlgoData> MULTIALGO_DATA = new ArrayList<BizUtil.MultiAlgoData>();
    static {
        for (int i = 0; i < MULTIALGO_CRITICAL.length - 1; i++) {
            MULTIALGO_DATA.add(new BizUtil().new MultiAlgoData(MULTIALGO_CRITICAL[i], MULTIALGO_CRITICAL[i + 1], i < 2 ? 2 : 3));
        }
    }

    /**
     * 倍数算法数据类
     * @author HT-LiChuanbin
     * @since 2016年12月22日 上午11:55:08
     */
    private class MultiAlgoData {

        /** 区间左值 */
        private int lNum;
        /** 区间右值 */
        private int rNum;
        /** 区间倍率 */
        private int multi;

        public MultiAlgoData(int lNum, int rNum, int multi) {
            this.lNum = lNum;
            this.rNum = rNum;
            this.multi = multi;
        }

        public boolean has(int viewCount) {
            return viewCount >= this.lNum && viewCount < this.rNum;
        }

        public int getMidValue() {
            return 5000 == this.lNum ? this.lNum : (this.lNum + this.rNum) / 2;
        }

        public int getMulti() {
            return this.multi;
        }

    }

    /**
     * 获取（虚拟）业务点击量
     * <p>
     * 业务算法：虚拟点击量 = 实际点击量 + 虚拟点击量基数
     * @param viewCount
     *            实际点击量
     * @param virtualBase
     *            虚拟点击量基数
     * @return 虚拟点击量
     * @author HT-LiChuanbin
     * @since 2016年12月22日 上午11:14:09
     */
    public static int getBizViewCount(int viewCount, String virtualBase) {
        return viewCount + (StringUtils.isBlank(virtualBase) ? 0 : Integer.parseInt(virtualBase));
    }

    /**
     * 获取虚拟点击量基数
     * @param viewCount
     *            实际点击量
     * @return 虚拟点击量基数
     * @author HT-LiChuanbin
     * @since 2016年12月22日 上午11:27:18
     */
    public static int getVirtualBase(int viewCount) {
        int virtualBase = 0;
        Iterator<BizUtil.MultiAlgoData> iMultiAlgoData = MULTIALGO_DATA.iterator();
        while (iMultiAlgoData.hasNext()) {
            BizUtil.MultiAlgoData multiAlgoData = iMultiAlgoData.next();
            if (multiAlgoData.has(viewCount)) {
                virtualBase = multiAlgoData.getMidValue() * multiAlgoData.getMulti();
                break;
            }
        }
        if (0 == virtualBase) {
            LOG.error(COMMON_ERROR);
        }
        return virtualBase;
    }

    /******************************************* Index Algorithm End *******************************************************/

}