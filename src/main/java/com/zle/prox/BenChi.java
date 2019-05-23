package com.zle.prox;

public class BenChi{

    public void drive(String driverName, String carName) {
        System.out.println(driverName+" - "+carName);
    }


    public void stop(String driverName) {
        System.out.println(driverName+"刹车了!");
    }

    public final void clean(String driverName) {
        System.out.println(driverName+"洗车!");
    }
}
