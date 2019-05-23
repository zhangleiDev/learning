package com.zle.prox;

public class Audi implements Car {
    @Override
    public void drive(String driverName, String carName) {
        System.out.println(driverName+" - "+carName);
    }

    @Override
    public void stop(String driverName) {
        System.out.println(driverName+"刹车了!");
    }
}
