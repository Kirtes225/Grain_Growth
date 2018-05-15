package com.company;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Generation {
    private Grain data[][];
    private List<Color> usedGrainColors;

    public List<Color> getUsedGrainColors() {
        return usedGrainColors;
    }

    public Generation(Grain[][] grainStructure) {
        this.data = grainStructure;
        this.usedGrainColors = new ArrayList<>();
    }

    public Generation(Grain[][] grainStructure, List<Color> colorList) {
        this.data = grainStructure;
        this.usedGrainColors = colorList;
    }

    public Grain[][] getAllGrains() {
        return data;
    }

    public void setAllGrains(Grain[][] data) {
        this.data = data;
    }

    public Grain getSingleGrain(int x, int y) {
        if (x >= 0 && y >= 0 && x < data[0].length && y < data.length)
            return data[y][x];

        return null;
    }

    public void setSingleGrain(int x, int y, Grain grain) {
        if (x >= 0 && x < this.getSizeX() && y >= 0 && y < this.getSizeY()) {
            if (grain != null)
                this.data[y][x] = new Grain(grain);
        }
    }

    public void setSingleGrain(int x, int y) {
        if (x >= 0 && x < this.getSizeX() && y >= 0 && y < this.getSizeY()) {
            Color color;
            do {
                color = Utils.randomColor();
            } while (usedGrainColors.contains(color));
            usedGrainColors.add(color);
            this.data[y][x] = new Grain(color);
        }
    }

    public int getSizeX() {
        return this.data[0].length;
    }

    public int getSizeY() {
        return this.data.length;
    }
}
