package com.zle.prox;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CarHandler implements InvocationHandler {
    private Car car;

    public CarHandler(Car car) {
        this.car = car;
    }
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.err.println("before...");
        method.invoke(car, args);
        System.err.println("after...");
        return null;
    }
}
