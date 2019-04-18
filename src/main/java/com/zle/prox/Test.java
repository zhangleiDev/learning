package com.zle.prox;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        Car audi = (Car) Proxy.newProxyInstance(Car.class.getClassLoader(),
                new Class<?>[] {Car.class}, new CarHandler(new Audi()));
        audi.drive("name1", "audi");
    }
}
