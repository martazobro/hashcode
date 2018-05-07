package com.mz.model;

public class ResidentialBuilding extends Building {

    public int capacity;
    private boolean wide;

    public Type getType(){
        return Type.RESIDENTIAL;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isWide() {
        return wide;
    }
}
