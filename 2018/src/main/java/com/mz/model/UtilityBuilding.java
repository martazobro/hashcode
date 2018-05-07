package com.mz.model;

public class UtilityBuilding extends Building {

    public int serviceType;

    public Type getType(){
        return Type.UTILITY;
    }

    public int getServiceType() {
        return serviceType;
    }
}
