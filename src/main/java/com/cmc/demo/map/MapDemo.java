package com.cmc.demo.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Map 实例
 * @author Thomas Lee
 * @version 2017年2月9日 下午3:40:48
 * @version 2017年4月1日 下午1:41:09
 */
public class MapDemo {

    private static final Logger LOG = LoggerFactory.getLogger(MapDemo.class);

    /**
     * 迭代Map
     * @author Thomas Lee
     * @version 2017年2月9日 下午3:44:20
     */
    public static void demoMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");

        /* 一般的遍历方式 */
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        /* 优先通过这个方式进行遍历 */
        for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            System.out.println(key + " = " + map.get(key));
        }
    }

    /**
     * 比较Map
     * @author Thomas Lee
     * @version 2017年3月29日 下午3:54:32
     */
    public static void compareMap() {
        List<Map<String, Integer>> maps = new ArrayList<Map<String, Integer>>();
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("order", 1);
        maps.add(map1);
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("order", 2);
        maps.add(map2);

        // 调用Java集合比较器，实现匿名类
        Collections.sort(maps, new Comparator<Map<String, Integer>>() {
            @Override
            public int compare(Map<String, Integer> map1, Map<String, Integer> map2) {
                return map2.get("order") - map1.get("order");
            }

        });
        // 调用自定义的比较器
        // Collections.sort(maps, new MyComparator<Map<String, Integer>>());
        Iterator<Map<String, Integer>> iMaps = maps.iterator();
        while (iMaps.hasNext()) {
            Map<String, Integer> map = iMaps.next();
            LOG.info(map.get("order").toString());
        }
    }

    /**
     * 测试HashMap、TreeMap和LinkedHashMap的输出顺序
     * @author Thomas Lee
     * @version 2017年4月1日 下午2:00:32
     */
    public static void testMapOutputOrder() {
        // HashMap按照随机顺序进行遍历（随机遍历）
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("aa", "1");
        hashMap.put("b", "1");
        hashMap.put("ke", "1");
        hashMap.put("da", "1");
        hashMap.put("c", "1");
        Iterator<Entry<String, String>> ieHashMap = hashMap.entrySet().iterator();
        while (ieHashMap.hasNext()) {
            Entry<String, String> eHashMap = ieHashMap.next();
            LOG.info("HashMap输出值：" + eHashMap.getKey() + " " + eHashMap.getValue());
        }

        System.out.println("\n");

        // TreeMap按照自然顺序或者自定顺序遍历（public TreeMap(Comparator<? super K> comparator);）（自然顺序或者自定顺序遍历）
        Map<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("aa", "1");
        treeMap.put("b", "1");
        treeMap.put("ke", "1");
        treeMap.put("da", "1");
        treeMap.put("c", "1");
        Iterator<Entry<String, String>> ieTreeMap = treeMap.entrySet().iterator();
        while (ieTreeMap.hasNext()) {
            Entry<String, String> eTreeMap = ieTreeMap.next();
            LOG.info("TreeMap输出值：" + eTreeMap.getKey() + " " + eTreeMap.getValue());
        }

        System.out.println("\n");

        // LinkedHashMap按照输入的顺序进行遍历（输入顺序遍历）
        Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.put("aa", "1");
        linkedHashMap.put("b", "1");
        linkedHashMap.put("ke", "1");
        linkedHashMap.put("da", "1");
        linkedHashMap.put("c", "1");
        Iterator<Entry<String, String>> ieLinkedHashMap = linkedHashMap.entrySet().iterator();
        while (ieLinkedHashMap.hasNext()) {
            Entry<String, String> eLinkedHashMap = ieLinkedHashMap.next();
            LOG.info("LinkedHashMap输出值：" + eLinkedHashMap.getKey() + " " + eLinkedHashMap.getValue());
        }
    }

    public static void main(String[] args) {
        MapDemo.testMapOutputOrder();
    }

}

/**
 * 自定义比较器
 * @param <T>
 * @author Thomas Lee
 * @version 2017年3月29日 下午5:01:31
 */
@SuppressWarnings({ "unchecked" })
class MyComparator<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        if (o1 instanceof Map && o2 instanceof Map) {
            Map<String, Integer> map1 = (Map<String, Integer>) o1;
            Map<String, Integer> map2 = (Map<String, Integer>) o2;
            return map2.get("order") - map1.get("order");
        } else {
            return 0;
        }
    }
}