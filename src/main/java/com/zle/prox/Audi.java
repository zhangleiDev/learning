package com.zle.prox;

public class Audi implements Car {
    @Override
    public void drive(String driverName, String carName) {
        System.out.println(driverName+" - "+carName);
    }
}
