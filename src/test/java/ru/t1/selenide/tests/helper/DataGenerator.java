package ru.t1.selenide.tests.helper;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@UtilityClass
public class DataGenerator {
    public static List<Integer> generateTestData(int size) {
        List<Integer> result = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int randomNumber = random.nextInt(10000) + 1;
            result.add(randomNumber);
        }
        return result;
    }
}
