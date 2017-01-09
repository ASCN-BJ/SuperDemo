package jxcsuper.cxb.com.textlibra;

import android.support.v4.util.SimpleArrayMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bj on 2017-1-9.
 * Description：
 */

public class Demo1 {

    public static void main(String[] args) {
//        testInteger();
//        testDouble();
//        testChar();
//        testSimpleMap();
//        testMap();
//        toFin();
        testSort();
    }

    /*
    * */
    private static void testSort() {
        List<Map<String, Integer>> mList = new ArrayList<>();
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("productId", 1);
        map1.put("productNum", 11);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("productId", 2);
        map2.put("productNum", 22);

        Map<String, Integer> map3 = new HashMap<>();
        map3.put("productId", 3);
        map3.put("productNum", 33);

        mList.add(map1);
        mList.add(map2);
        mList.add(map3);

        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < mList.size() - 1; i++) {
            for (int x = 1; x < mList.size(); x++) {
                if (mList.get(i).get("productId") == mList.get(x).get ("productId")) {//判断是否相等
                    ids.add(mList.get(i).get("productId"));
                }
            }

        }

//
//        List<Integer> mListInteger = new ArrayList<>();
//
//        for (int x = 0; x < mListInteger.size() - 1; x++) {
//            for (int y = 1; y < mListInteger.size(); x++) {
//                if (mListInteger.get(x) == mListInteger.get(y)) {
//
//                }
//            }
//        }

    }

    /*二分查找*/
    private static void toFin() {

    }

    /*测试Map*/
    private static void testMap() {
        long start;
        long end;
        Map<String, String> simpleMap = new HashMap<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            simpleMap.put(i + "", i + 1 + "");
        }
        for (int i = 0; i < 100000; i++) {
            System.out.println(simpleMap.get(i + ""));
        }
        end = System.currentTimeMillis();
        System.out.println((end - start));//550
    }

    /*测试simpleMap*/
    private static void testSimpleMap() {
        long start;
        long end;
        SimpleArrayMap<String, String> simpleMap = new SimpleArrayMap<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            simpleMap.put(i + "", i + 1 + "");
        }
        for (int i = 0; i < 100000; i++) {
            System.out.println(simpleMap.get(i + ""));
        }
        end = System.currentTimeMillis();
        System.out.println((end - start));//550
    }

    private static void testChar() {
        Character c0 = 1233;
        char c7 = 97;
        Character c1 = 'a';
        Character c2 = 'a';
        Character c3 = 'c';
        Character c4 = 'c';
        Character c5 = 'c';
        Character c6 = 'd';
        System.out.println(c1 == c2);
        System.out.println(c1);
        System.out.println(c3);
        System.out.println(Integer.valueOf(c1));
        System.out.println(c0);
        System.out.println(c7);
    }

    /*
    *double类型不一样的原因是一定范围内的整数是已知的,但是小数却是无限的
    * */
    private static void testDouble() {
        Double i0 = new Double(100d);
        Double i1 = 100d;
        Double i2 = 100d;

        Double i3 = 100d;
        Double i4 = 200d;

        Double i5 = 200d;
        Double i6 = 200d;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(i5 == i6);
    }

    /*
     * 自动装箱[-127,128]之间的数字会存数数组，直接读取，超过则不是
     *
     * */
    private static void testInteger() {
        Integer i0 = new Integer(100);
        Integer i1 = 100;
        Integer i2 = 100;

        Integer i3 = 100;
        Integer i4 = 200;

        Integer i5 = 200;
        Integer i6 = 200;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(i5 == i6);
    }
}
