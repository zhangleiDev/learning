package com.zle.guava;

import ch.qos.logback.core.FileAppender;
import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.io.*;
import io.swagger.models.auth.In;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Gua1 {

    public static void main(String[] args) {

        testBiMap();
       // OrderingTest();
    }
    public static void testBiMap() {
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("one",1);
        biMap.put("two",2);
        //默认key也不能重复，会报错
        //biMap.put("two",1);
        biMap.put("three",3);
        //强制覆盖
        biMap.forcePut("four",3);
        System.out.println(biMap.get("one"));
        System.out.println(biMap.inverse().get(1));//通过value获取key
        System.out.println(biMap.inverse().get(3));//key被覆盖


    }
    public static void testFile() {

        try {
            //按行读取文件
            List<String> content = Files.readLines(new File("/home/zhanglei/Desktop/db.redis.dubbo"), Charsets.UTF_8);
            // content.forEach(s-> System.out.println(s));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void testHashMultiset() {
        //可以重复的set集合，可用于计数
        HashMultiset<Object> multiset = HashMultiset.create();
        multiset.add("1");
        multiset.add("1");
        multiset.add("2");
        multiset.add("3");
        multiset.add("2");
        multiset.add("11");
        multiset.add("11");
        System.out.println(multiset.count("1"));
        //增加10个1
        multiset.add("1",10);
        //移除2个1
        multiset.remove("1",2);
        System.out.println("count "+multiset.count("1"));

    }
    public static void testString() {
        //Optional是一种用来标示null的友好方式
        String a=null;
        a=Optional.fromNullable(a).or("空1");
        System.out.println(a);
        //get()不能为null
        //System.out.println(Optional.fromNullable(a).get());
        //System.out.println(Optional.of(null).or("324"));

        //补前缀或者后缀
        String name1 = "aaa";
        System.out.println(Strings.padStart(name1,5,'0'));
        System.out.println(Strings.padEnd(name1,5,'0'));

        String name = null;

        System.out.println(Strings.isNullOrEmpty(name));
        System.out.println("end");

    }

    public static void OrderingTest() {

        //创建完直接初始化
        List<Integer> names = Lists.newArrayList(1,null,3,4,null,6);
        System.out.println(names.stream().filter(num -> num != null).mapToInt(num -> num+1).sum());


        List<Integer> myList = Lists.newArrayList();

        myList.add(new Integer(5));
        myList.add(new Integer(2));
        myList.add(new Integer(15));
        myList.add(new Integer(51));
        myList.add(new Integer(53));
        myList.add(new Integer(35));
        myList.add(new Integer(45));
        myList.add(new Integer(32));
        myList.add(null);
        myList.add(new Integer(43));


        Ordering ordering = Ordering.natural();
        System.out.println("max: "+ordering.nullsFirst().max(myList));
        System.out.println("min: "+ordering.nullsLast().min(myList));

        //从最大到最小的k个最大的元素,避免空指针需要指出null的位置nullsFirst()
        List list1 = ordering.nullsFirst().greatestOf(myList, 3);
        System.out.println(list1);
        //从小到最大的k个最小的元素
        List list2 = ordering.nullsLast().leastOf(myList, 3);
        System.out.println("从小到最大的k个最小的元素list2:"+list2);//输出[2, 5, 15]
        //倒序
        List list = ordering.reverse().nullsFirst().sortedCopy(myList);
        System.out.println(list);
        //顺序
        System.out.println(ordering.nullsFirst().sortedCopy(myList));


    }
}


class Foo {

    String sortedBy;
    int notSortedBy;

}
