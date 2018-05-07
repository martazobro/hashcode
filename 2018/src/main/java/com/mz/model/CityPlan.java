package com.mz.model;


import java.util.*;

public class CityPlan {
    public int rows;
    public int cols;
    public int maxWalkingDistance;
    public int numberOfProjects;
    private int[][] projectsLocation;
    private int lastRow = 0;
    private int lastCol = 0;

    public CityPlan(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        projectsLocation = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(projectsLocation[i], -1);
        }
    }

    public boolean tryAdd(Set<ResidentialBuilding> residentialBuildings, Map<Integer, Set<UtilityBuilding>> utilityByService) {
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
        Iterator<ResidentialBuilding> it = residentialBuildings.iterator();
        List<LivingBlock> testBlocks = new ArrayList<>();
        while (it.hasNext() && testBlocks.size() <= 6 * utilityByService.size()) {
            LivingBlock block = new LivingBlock();
            testBlocks.add(block);
            ResidentialBuilding residentialBuilding = it.next();

            if (!block.addResidential(this, residentialBuilding)) {
                continue;
            }
            for (Integer serviceType : utilityByService.keySet()) {
                Set<UtilityBuilding> utilityBuildings = utilityByService.get(serviceType);
                Iterator<UtilityBuilding> uit = utilityBuildings.iterator();

                if (uit.hasNext()) {
                    UtilityBuilding utilityBuilding = uit.next();
                    block.addUtility(utilityBuilding);
                }
            }

        }
        return getBest(testBlocks);
    }

    private LivingBlock getBest(List<LivingBlock> testBlocks) {

        return null;
    }


    private boolean isEmpty() {
        return false;
    }

    private class LivingBlock {
        private ResidentialBuilding residentialBuilding;
        private List<UtilityBuilding> utilityBuildings = new ArrayList<>();

        private int[][] projectsLocation;
        private CityPlan cityPlan;
        private int residentalStartRow;
        private int residentalStartCol;

        boolean addResidential(CityPlan cityPlan, ResidentialBuilding residentialBuilding) {
            this.projectsLocation = new int[cityPlan.rows][cityPlan.cols];
            this.cityPlan = cityPlan;
            for (int i = 0; i < cityPlan.rows; i++) {
                this.projectsLocation[i] = Arrays.copyOf(cityPlan.projectsLocation[i], cols);
            }
            int col = lastCol;
            int row = lastRow;
            do {
                if (row + residentialBuilding.rows > cityPlan.rows) {
                    return false;
                } else if (col + residentialBuilding.cols > cityPlan.cols) {
                    col = 0;
                    row++;
                }
                if (tryPlace(residentialBuilding, col, row)) {
                    this.residentialBuilding = residentialBuilding;
                    residentalStartRow = row;
                    residentalStartCol = col;
                    addBuilding(this.projectsLocation, residentialBuilding, row, col);
                    return true;
                }
                col++;
            } while (true);
        }

        private void addBuilding(int[][] projectsLocation, ResidentialBuilding residentialBuilding, int row, int col) {
            for (int i = 0; i < residentialBuilding.rows; i++) {
                for (int j = 0; j < residentialBuilding.cols; j++) {
                    if (residentialBuilding.occupiedPlan[i][j]) {
                        projectsLocation[row + i][col + j] = residentialBuilding.id;
                    }
                }
            }
        }

        private boolean tryPlace(Building building, int col, int row) {
            for (int i = 0; i < building.rows; i++) {
                for (int j = 0; j < building.cols; j++) {
                    if (row + i >= cityPlan.rows || col + j >= cityPlan.cols
                            || (this.projectsLocation[row + i][col + j] >= 0 && building.occupiedPlan[i][j])) {
                        return false;
                    }

                }
            }
            return true;
        }

        boolean addUtility(UtilityBuilding utilityBuilding) {
            for (int i = lastCol; i < cols; i++) {

            }
            return false;
            utilityBuildings.add(utilityBuilding);
        }

        boolean canFit(Building building) {

        }
    }
}

