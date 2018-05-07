package com.mz;

import com.mz.model.CityPlan;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String in = "a_example.in";

        try (InputStream inputStream = Runner.class.getClassLoader().getResourceAsStream(in)) {
            InputData inputData = InputData.readFromFile(inputStream);
            CityPlan cityPlan = inputData.cityPlan;
            while (cityPlan.tryAdd(inputData.residentialBuildings, inputData.utilityByService)) {
            }
            writeResults(cityPlan, Paths.get(in + ".out"));
        }
    }

    private static void writeResults(CityPlan cityPlan, Path out) {

    }
}
