package com.company.models.arrangements;

import com.company.Generation;
import com.company.interfaces.Arrangement;

import java.util.ArrayList;
import java.util.List;

public class ContinuousRandom implements Arrangement {

    @Override
    public void arrangement(Generation generation, int numberOfNucleons) {
        //        int temp = 0;
//        int width = generation.getSizeX();
//        int height = generation.getSizeY();
//        java.util.Random random = new java.util.Random();
//        do {
//            int tempWidth = random.nextInt(width);
//            int tempHeight = random.nextInt(height);
//            if (generation.getSingleGrain(tempHeight, tempWidth) == null) {
//                generation.setSingleGrain(tempHeight, tempWidth);
//                temp++;
//                System.out.println("X: " + (tempWidth + 1) + ", Y: " + (tempHeight + 1) + " TEMP: " + temp);
//            }
//        } while (temp < numberOfNucleons);

        int temp = 0;
        int width = generation.getSizeX();
        int height = generation.getSizeY();

        System.out.println("W: " + width + ", H: " + height);

        List<Integer> list = new ArrayList<>();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < width * height; i++) {
            list.add(i);
        }

        System.out.println(16 % 6);

        while(temp < numberOfNucleons) {
            int coordinate = list.remove(random.nextInt(list.size()));
            int y = coordinate % width;
            int x = coordinate / width;
            System.out.println("COR: " + coordinate + "=> (" + x + ", " + y + ")");
            generation.setSingleGrain(y, x);
            temp++;
        }

        System.out.println("////////////////////////");

//        for (int i = 0; i < width * height; i++) {
//            list.add(i);
//        }
//        while(!list.isEmpty()) {
//            System.out.print(list.remove(random.nextInt(list.size())) + " ");
//        }

        System.out.println("\n\n\n\n----------------------------------");

    }
}
