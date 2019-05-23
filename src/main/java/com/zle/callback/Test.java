package com.zle.callback;

/**
 * 测试接口回调
 */
public class Test {
    public static void main(String[] args) {
        new Vip().addSugar(new Drink() {
            @Override
            public void drink(String name) {
                System.out.println("我喝的是"+name);
            }
        },"茶水");
    }

}
