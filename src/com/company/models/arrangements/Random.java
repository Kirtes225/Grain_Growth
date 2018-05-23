package com.company.models.arrangements;

import com.company.Generation;
import com.company.interfaces.Arrangement;

public class Random implements Arrangement {
    @Override
    public void arrangement(Generation generation, int width, int height, int numberOfNucleons) {
        int temp = 0;
        java.util.Random random = new java.util.Random();
        do {
            int tempWidth = random.nextInt(width);
            int tempHeight = random.nextInt(height);
            if (generation.getSingleGrain(tempHeight, tempWidth) == null) {
                generation.setSingleGrain(tempHeight, tempWidth);
                temp++;
                System.out.println("X: " + (tempWidth + 1) + ", Y: " + (tempHeight + 1) + " TEMP: " + temp);
            }
        } while (temp < numberOfNucleons);
    }
}
