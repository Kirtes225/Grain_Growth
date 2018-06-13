package com.company;

import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static Color randomColor() {
        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);
        return Color.rgb(R, G, B);
    }
    public static int randomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
