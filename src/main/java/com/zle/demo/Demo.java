package com.zle.demo;

public class Demo {
    public static void main(String[] args) {

        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println("1 end");
            }catch (Exception e){

            }
        }).start();

        new Thread(()->{
            try {
                System.out.println("2 end");
                Thread.sleep(3000);
                System.out.println("3 end");

            }catch (Exception e){

            }
        }).start();

        try {
            Thread.sleep(1000);
            System.out.println(3);
        }catch (Exception e){

        }
        System.out.println("end");
    }
}
