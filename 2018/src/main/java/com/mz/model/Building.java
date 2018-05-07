package com.mz.model;

public abstract class Building {

    public int id;

    public int rows;
    public int cols;

    public boolean[][] occupiedPlan;

    public abstract Type getType();

    public static Building ofType(int id, String type, int rows, int cols, int capacityOrType) {
        if ("R".equals(type)) {
            ResidentialBuilding building = new ResidentialBuilding();
            building.id = id;
            building.rows = rows;
            building.cols = cols;
            building.occupiedPlan = new boolean[rows][cols];
            building.capacity  = capacityOrType;
            return building;
        } else {
            UtilityBuilding building = new UtilityBuilding();
            building.id = id;
            building.rows = rows;
            building.cols = cols;
            building.occupiedPlan = new boolean[rows][cols];
            building.serviceType = capacityOrType;
            return building;
        }
    }

    public void addBuildingPlan(int i, String rowPlan) {
        occupiedPlan[i] = new boolean[rowPlan.length()];
        int next = 0;
        for (char c : rowPlan.toCharArray()) {
            occupiedPlan[i][next++] = '#' == c;
        }
    }


    public enum Type {
        RESIDENTIAL, UTILITY;
    }
}
