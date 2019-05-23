package com.zle.prox;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 基于JDK代理
 */
public class JdkCarHandler implements InvocationHandler {
    private Car car;

    public JdkCarHandler(Car car) {
        this.car = car;
    }
    /**
     * 会拦截到所有的接口方法,实现类中的其它非接口方法不会被拦截
     */
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {

        if(method.getName().equals("drive")){
            System.err.println("before...");
            method.invoke(car, args);
            System.err.println("after...");
        }else {
            method.invoke(car, args);
        }

        return null;
    }
}
