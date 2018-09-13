package com.sun.common.Map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapTest {
    /**
     * map的四种遍历方式
     */
//    @Test
    public void test() {
        Map<Integer, String> map = new HashMap<>();
        //map集合存入数据
        map.put(1, "第一个value");
        map.put(2, "第二个value");
        map.put(3, "第三个value");

        //通过keySet取出map数据[for-each循环]
        System.out.println("-------[for-each循环遍历]通过keySet取出map数据-------");
        Set<Integer> keys = map.keySet();   //此行可省略，直接将map.keySet()写在for-each循环的条件中
        for (Integer key : keys) {
            System.out.println("key值：" + key + " value值：" + map.get(key));
        }

        //通过EntrySet取出map数据[for-each循环]
        System.out.println("-------[for-each循环遍历]通过EntrySet取出map数据-------");
        Set<Entry<Integer, String>> entrys = map.entrySet();  //此行可省略，直接将map.entrySet()写在for-each循环的条件中
        for (Entry<Integer, String> entry : entrys) {
            System.out.println("key值：" + entry.getKey() + " value值：" + entry.getValue());
        }

        //通过keySet取出map数据[Iterator遍历]
        System.out.println("-------[Iterator循环遍历]通过keySet取出map数据---------");
        Iterator<Integer> it = map.keySet().iterator();  //map.keySet()得到的是set集合，可以使用迭代器遍历
        while (it.hasNext()) {
            Integer key = it.next();
            System.out.println("key值：" + key + " value值：" + map.get(key));
        }

        //通过EntrySet取出map数据[Iterator遍历]
        System.out.println("-------[Iterator循环遍历]通过EntrySet取出map数据---------");
        Iterator<Entry<Integer, String>> iterator = map.entrySet().iterator();  //map.entrySet()得到的是set集合，可以使用迭代器遍历
        while (iterator.hasNext()) {
            Entry<Integer, String> entry = iterator.next();
            System.out.println("key值：" + entry.getKey() + " value值：" + entry.getValue());
        }
    }

    /**
     *匿名new map
     */
    @Test
    public  void test2() {
        Map<String, Object> map = new HashMap<String, Object>() {{
        put("name", "June");
        put("age", 12);
    }};
        System.out.println(new HashMap<String, Object>() {{
            put("name", "June");
            put("age", 12);
        }});
    }


}
