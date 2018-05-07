package com.mz.model;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CityPlan {
    public int rows;
    public int cols;
    public int maxWalkingDistance;
    public int numberOfProjects;

    public boolean tryAdd(Set<ResidentialBuilding> residentialBuildings ,Map<Integer, Set<UtilityBuilding>> utilityByService) {
        LivingBlock livingBlock = createLivingBlock(residentialBuildings, utilityByService);
        if (livingBlock != null) {
            reserve(livingBlock);
            return true;
        }
        return false;
    }

    private void reserve(LivingBlock livingBlock) {

    }

    private LivingBlock createLivingBlock(Set<ResidentialBuilding> residentialBuildings, Map<Integer, Set<UtilityBuilding>> utilityByService) {
        return null;
    }

    private boolean canPlace(ResidentialBuilding residential) {
        return false;
    }

    private boolean isEmpty() {
        return false;
    }

    private class LivingBlock {
    }
}

