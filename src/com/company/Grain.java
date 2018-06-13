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

    public Grain(Color color, int ID) {
        this.color = color;
        this.ID = ID;
    }

    public static void setCounter(int counter) {
        Grain.counter = counter;
    }

    public int getID() {
        return ID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grain grain = (Grain) o;

        if (ID != grain.ID) return false;
        return color.equals(grain.color);
    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + ID;
        return result;
    }

    //    public boolean[][] getSpace() {
//        return space;
//    }
//
//    public void setSpace(boolean[][] space) {
//        this.space = space;
//    }
}
