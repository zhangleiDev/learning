package com.zle.prox;

import java.lang.reflect.Proxy;

/**
 * jdk代理测试
 */
public class TestJdk {
    public static void main(String[] args) {
        Car audi = (Car) Proxy.newProxyInstance(Car.class.getClassLoader(),
                new Class<?>[] {Car.class}, new CarHandler(new Audi()));
        audi.drive("name1", "audi");
        audi.stop("name1");
    }
}
