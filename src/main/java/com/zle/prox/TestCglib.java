package com.zle.prox;

import java.lang.reflect.Proxy;

/**
 * jdk代理测试
 *
 * 会拦截到所有的接口方法,实现类中的其它非接口方法不会被拦截
 * 代理对象不需要实现接口,但是被代理目标对象必须实现接口,也叫接口代理
 */
public class TestCglib {
    public static void main(String[] args) {
        //cglib代理的类不能为final,否则报错
        //方法可以是final

        //代理普通类
        BenChi car = (BenChi)new CglibCarHandler(new BenChi()).getProxyInstance();
        car.drive("haha","001");
        car.stop("haha");
        car.clean("haha");
        //代理接口实现
        Car audi= (Car)new CglibCarHandler(new Audi()).getProxyInstance();
        car.drive("hehe","001");
        car.stop("hehe");

    }
}
