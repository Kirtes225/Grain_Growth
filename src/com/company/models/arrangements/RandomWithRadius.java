package com.company.models.arrangements;

import com.company.Generation;
import com.company.interfaces.Arrangement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomWithRadius implements Arrangement {
    private int radius;
    private int circleNumber;

    public RandomWithRadius(int radius, int circleNumber) {
        this.radius = radius;
        this.circleNumber = circleNumber;
    }

    @Override
    public void arrangement(Generation generation, int numberOfNucleons) {
        int width = generation.getSizeX();
        int height = generation.getSizeY();

        int[] circlesCoordinates = ThreadLocalRandom
                .current()
                .ints(0, width*height)
                .distinct()
                .limit(circleNumber)
                .toArray();

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < width * height; i++){
            list.add(i);
        }

        for(int circleCoordinate : circlesCoordinates) {
            int circleY = circleCoordinate % width;
            int circleX = circleCoordinate / width;
            System.out.println("CIRCLE: " + circleX + ", " + circleY + " r = " + radius);
        }

        int temp = 0;
        java.util.Random random = new java.util.Random();

        while (temp < numberOfNucleons){
            int grainCoordinate = list.remove(random.nextInt(list.size()));
            int grainY = grainCoordinate % width;
            int grainX = grainCoordinate / width;

            boolean willBeOutsideCircles = true;
            for(int circleCoordinate : circlesCoordinates){
                int circleY = circleCoordinate % width;
                int circleX = circleCoordinate / width;
                //wzór na długość odcinka = pierwiastek[(x2-x1)^2 + (y2-y1)^2]
                if(Math.sqrt((grainX - circleX)*(grainX - circleX) + (grainY - circleY)*(grainY - circleY)) < radius){
                    willBeOutsideCircles = false;
                    break;
                }
            }

            if(willBeOutsideCircles){
                generation.setSingleGrain(grainY, grainX);
            }

            temp++;
        }

//        System.out.println("W: " + width + ", H: " + height);
//
//        List<Integer> list = new ArrayList<>();
//        java.util.Random random = new java.util.Random();
//        for (int i = 0; i < width * height; i++) {
//            list.add(i);
//        }
//
//        System.out.println(16 % 6);
//
//        while(temp < numberOfNucleons) {
//            int coordinate = list.remove(random.nextInt(list.size()));
//            int y = coordinate % width;
//            int x = coordinate / width;
//            System.out.println("COR: " + coordinate + "=> (" + x + ", " + y + ")");
//            generation.setSingleGrain(y, x);
//            temp++;
//        }
//
//        System.out.println("////////////////////////");
//
//        for (int i = 0; i < width * height; i++) {
//            list.add(i);
//        }
//        while(!list.isEmpty()) {
//            System.out.print(list.remove(random.nextInt(list.size())) + " ");
//        }
    }
}
