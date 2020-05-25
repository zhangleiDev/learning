package com.zle.java8;

import java.util.function.Consumer;
import java.util.function.Function;

public class Test1 {
    public static void main(String[] args) {
//        Consumer f = System.out::println;
//        Consumer f2 = n -> System.out.println(n + "-F2");
//        Consumer<String> f3 = System.out::println;
//        Consumer f4 = (n)->System.out.println(n + "F3");
////        f2.andThen(f4).accept("1");
//
//        StringBuilder s  = new StringBuilder();
//        s.append("");
//        Consumer cu1= new Consumer<String>() {
//                    @Override
//                    public void accept(String s) {
//                        System.out.println(s+"123");
//                    }
//            };
//        f3.andThen(cu1).accept("4");
        //执行完F后再执行F2的Accept方法
//        f2.andThen(f2).accept("acc");

        //连续执行F的Accept方法
//        f2.andThen(f).andThen(f).andThen(f).accept("test1");
        Test1 t1 = new Test1();
        t1.test2();
    }

    public void test2(){
        Function<Integer, Integer> f = (s) -> {
            s++;
            System.out.println(s);
            return s;
        };

        System.out.println(Function.identity().apply(f.apply(1)));;
    }
}
