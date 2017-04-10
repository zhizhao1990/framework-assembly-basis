package com.cmc.j2se;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * parse java.util.Map to net.sf.json.JSONObject
 * 
 * @author HT.LCB
 * @since 2016年11月21日 下午3:25:07
 */
public class Map2JSONObject {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        JSONObject obj = new JSONObject();
        obj.put("map", map);
        // System.out.println(obj);

        System.out.println(new Date().toString());

        // new Thread(new MyThread()).start();

        System.out.println(new Date().toString());
    }

}