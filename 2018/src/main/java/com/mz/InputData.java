package com.mz;


import com.mz.model.Building;
import com.mz.model.CityPlan;
import com.mz.model.ResidentialBuilding;
import com.mz.model.UtilityBuilding;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class InputData {

    public static final String SPACE_REGEXP = "\\s";
    public CityPlan cityPlan;
    public Set<Building> utilityBuildings = new HashSet<>();
    public Set<ResidentialBuilding> residentialBuildings = new TreeSet<>(Comparator.comparing(ResidentialBuilding::getCapacity));
    public Map<Integer, Set<UtilityBuilding>> utilityByService = new HashMap<>();

    public static InputData readFromFile(InputStream file) throws IOException {
        Scanner scanner = new Scanner(file);
        InputData inputData = new InputData();
        inputData.initCityPlan(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        int projectIndex = 0;
        while (projectIndex < inputData.cityPlan.numberOfProjects) {
            Building building = inputData.initBuilding(projectIndex++, scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            scanner.nextLine();
            for (int i = 0; i < building.rows; i++) {
                building.addBuildingPlan(i, scanner.nextLine());
            }
            if (Building.Type.RESIDENTIAL.equals(building.getType())){
                inputData.residentialBuildings.add((ResidentialBuilding) building);
            } else {
                inputData.utilityBuildings.add(building);
                inputData.utilityByService.computeIfAbsent(((UtilityBuilding)building).serviceType
                        ,(k) -> new TreeSet<>(Comparator.comparing(UtilityBuilding::getServiceType)));
                inputData.utilityByService.get(((UtilityBuilding)building).serviceType).add((UtilityBuilding) building);
            }
        }
        return inputData;
    }

    private Building initBuilding(int id, String type, int rows, int cols, int capacityOrType) {
        return Building.ofType(
                id,
                type,
                rows,
                cols,
                capacityOrType);
    }

    private void initCityPlan(int rows, int cols, int walkingDist, int projects) {
        cityPlan = new CityPlan();
        cityPlan.rows = rows;
        cityPlan.cols = cols;
        cityPlan.maxWalkingDistance = walkingDist;
        cityPlan.numberOfProjects = projects;
    }

}
