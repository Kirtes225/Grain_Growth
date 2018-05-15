package com.company;

import javafx.scene.paint.Color;

public class Utils {
    public static Color randomColor() {
        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);
        return Color.rgb(R, G, B);
    }
}
