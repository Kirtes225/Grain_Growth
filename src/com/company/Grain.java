package com.company;

import javafx.scene.paint.Color;

public class Grain {
    private static int counter = 0;
    private Color color;
    private int ID;

    public Grain(Grain grain) {
        this.color = grain.color;
        this.ID = grain.ID;
    }

    public Grain(Color color) {
        this.ID = ++counter;
        this.color = color;
    }

    public static void setCounter(int counter) {
        Grain.counter = counter;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Grain{" +
                "color=" + color +
                ", ID=" + ID +
                '}';
    }

    //    public boolean[][] getSpace() {
//        return space;
//    }
//
//    public void setSpace(boolean[][] space) {
//        this.space = space;
//    }
}
