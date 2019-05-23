package com.zle.prox;

import java.lang.reflect.Proxy;

/**
 * jdk代理测试
 *
 * 会拦截到所有的接口方法,实现类中的其它非接口方法不会被拦截
 * 代理对象不需要实现接口,但是被代理目标对象必须实现接口,也叫接口代理
 */
public class TestJdk {
    public static void main(String[] args) {
        Car audi = (Car) Proxy.newProxyInstance(Car.class.getClassLoader(),
                new Class<?>[] {Car.class}, new JdkCarHandler(new Audi()));
        audi.drive("name1", "audi");
        audi.stop("name1");
    }
}
