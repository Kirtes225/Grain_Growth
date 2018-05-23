package com.company;

import com.company.interfaces.Rule;

public abstract class Neighborhood implements Rule {
    public abstract Grain[] checkIfWillGrow(Grain current,
                                            Grain neighbour1, Grain neighbour2, Grain neighbour3,
                                            Grain neighbour4, Grain neighbour6,
                                            Grain neighbour7, Grain neighbour8, Grain neighbour9);

    @Override
    public Generation nextGeneration(Generation generation, boolean periodic) {
        Generation nextGeneration = new Generation(new Grain[generation.getSizeY()][generation.getSizeX()], generation.getUsedGrainColors());

        final int sizeX = generation.getSizeX();
        final int sizeY = generation.getSizeY();


        if (periodic) {
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    if (x == 0 && y == 0) {
                        Grain[] grains = checkIfWillGrow(generation.getSingleGrain(x, y),
                                generation.getSingleGrain(sizeX - 1, sizeY - 1),
                                generation.getSingleGrain(x, sizeY - 1),
                                generation.getSingleGrain(x + 1, sizeY - 1),

                                generation.getSingleGrain(sizeX - 1, y),
                                generation.getSingleGrain(x + 1, y),

                                generation.getSingleGrain(sizeX - 1, y + 1),
                                generation.getSingleGrain(x, y + 1),
                                generation.getSingleGrain(x + 1, y + 1));

                        nextGeneration.setSingleGrain(sizeX - 1, sizeY - 1, grains[0]);
                        nextGeneration.setSingleGrain(x, sizeY - 1, grains[1]);
                        nextGeneration.setSingleGrain(x + 1, sizeY - 1, grains[2]);

                        nextGeneration.setSingleGrain(sizeX - 1, y, grains[3]);
                        nextGeneration.setSingleGrain(x + 1, y, grains[4]);

                        nextGeneration.setSingleGrain(sizeX - 1, y + 1, grains[5]);
                        nextGeneration.setSingleGrain(x, y + 1, grains[6]);
                        nextGeneration.setSingleGrain(x + 1, y + 1, grains[7]);

                        nextGeneration.setSingleGrain(x, y, grains[8]);
                    } else if (x == sizeX - 1 && y == 0) {
                        Grain[] grains = checkIfWillGrow(generation.getSingleGrain(x, y),
                                generation.getSingleGrain(x - 1, sizeY - 1),
                                generation.getSingleGrain(x, sizeY - 1),
                                generation.getSingleGrain(0, sizeY - 1),

                                generation.getSingleGrain(x - 1, y),
                                generation.getSingleGrain(0, y),

                                generation.getSingleGrain(x - 1, y + 1),
                                generation.getSingleGrain(x, y + 1),
                                generation.getSingleGrain(0, y + 1));

                        nextGeneration.setSingleGrain(x - 1, sizeY - 1, grains[0]);
                        nextGeneration.setSingleGrain(x, sizeY - 1, grains[1]);
                        nextGeneration.setSingleGrain(0, sizeY - 1, grains[2]);

                        nextGeneration.setSingleGrain(x - 1, y, grains[3]);
                        nextGeneration.setSingleGrain(0, y, grains[4]);

                        nextGeneration.setSingleGrain(x - 1, y + 1, grains[5]);
                        nextGeneration.setSingleGrain(x, y + 1, grains[6]);
                        nextGeneration.setSingleGrain(0, y + 1, grains[7]);

                        nextGeneration.setSingleGrain(x, y, grains[8]);
                    } else if (x == 0 && y == sizeY - 1) {
                        Grain[] grains = checkIfWillGrow(generation.getSingleGrain(x, y),
                                generation.getSingleGrain(sizeX - 1, y - 1),
                                generation.getSingleGrain(x, y - 1),
                                generation.getSingleGrain(x + 1, y - 1),

                                generation.getSingleGrain(sizeX - 1, y),
                                generation.getSingleGrain(x + 1, y),

                                generation.getSingleGrain(sizeX - 1, 0),
                                generation.getSingleGrain(x, 0),
                                generation.getSingleGrain(x + 1, 0));

                        nextGeneration.setSingleGrain(sizeX - 1, y - 1, grains[0]);
                        nextGeneration.setSingleGrain(x, y - 1, grains[1]);
                        nextGeneration.setSingleGrain(x + 1, y - 1, grains[2]);

                        nextGeneration.setSingleGrain(sizeX - 1, y, grains[3]);
                        nextGeneration.setSingleGrain(x + 1, y, grains[4]);

                        nextGeneration.setSingleGrain(sizeX - 1, 0, grains[5]);
                        nextGeneration.setSingleGrain(x, 0, grains[6]);
                        nextGeneration.setSingleGrain(x + 1, 0, grains[7]);

                        nextGeneration.setSingleGrain(x, y, grains[8]);
                    } else if (x == sizeX - 1 && y == sizeY - 1) {
                        Grain[] grains = checkIfWillGrow(generation.getSingleGrain(x, y),
                                generation.getSingleGrain(x - 1, y - 1),
                                generation.getSingleGrain(x, y - 1),
                                generation.getSingleGrain(0, y - 1),

                                generation.getSingleGrain(x - 1, y),
                                generation.getSingleGrain(0, y),

                                generation.getSingleGrain(x - 1, 0),
                                generation.getSingleGrain(x, 0),
                                generation.getSingleGrain(0, 0));

                        nextGeneration.setSingleGrain(x - 1, y - 1, grains[0]);
                        nextGeneration.setSingleGrain(x, y - 1, grains[1]);
                        nextGeneration.setSingleGrain(0, y - 1, grains[2]);

                        nextGeneration.setSingleGrain(x - 1, y, grains[3]);
                        nextGeneration.setSingleGrain(0, y, grains[4]);

                        nextGeneration.setSingleGrain(x - 1, 0, grains[5]);
                        nextGeneration.setSingleGrain(x, 0, grains[6]);
                        nextGeneration.setSingleGrain(0, 0, grains[7]);

                        nextGeneration.setSingleGrain(x, y, grains[8]);
                    } else if (x == 0) {
                        Grain[] grains = checkIfWillGrow(generation.getSingleGrain(x, y),
                                generation.getSingleGrain(sizeX - 1, y - 1),
                                generation.getSingleGrain(x, y - 1),
                                generation.getSingleGrain(x + 1, y - 1),

                                generation.getSingleGrain(sizeX - 1, y),
                                generation.getSingleGrain(x + 1, y),

                                generation.getSingleGrain(sizeX - 1, y + 1),
                                generation.getSingleGrain(x, y + 1),
                                generation.getSingleGrain(x + 1, y + 1));

                        nextGeneration.setSingleGrain(sizeX - 1, y - 1, grains[0]);
                        nextGeneration.setSingleGrain(x, y - 1, grains[1]);
                        nextGeneration.setSingleGrain(x + 1, y - 1, grains[2]);

                        nextGeneration.setSingleGrain(sizeX - 1, y, grains[3]);
                        nextGeneration.setSingleGrain(x + 1, y, grains[4]);

                        nextGeneration.setSingleGrain(sizeX - 1, y + 1, grains[5]);
                        nextGeneration.setSingleGrain(x, y + 1, grains[6]);
                        nextGeneration.setSingleGrain(x + 1, y + 1, grains[7]);

                        nextGeneration.setSingleGrain(x, y, grains[8]);
                    } else if (x == sizeX - 1) {
                        Grain[] grains = checkIfWillGrow(generation.getSingleGrain(x, y),
                                generation.getSingleGrain(x - 1, y - 1),
                                generation.getSingleGrain(x, y - 1),
                                generation.getSingleGrain(0, y - 1),

                                generation.getSingleGrain(x - 1, y),
                                generation.getSingleGrain(0, y),

                                generation.getSingleGrain(x - 1, y + 1),
                                generation.getSingleGrain(x, y + 1),
                                generation.getSingleGrain(0, y + 1));

                        nextGeneration.setSingleGrain(x - 1, y - 1, grains[0]);
                        nextGeneration.setSingleGrain(x, y - 1, grains[1]);
                        nextGeneration.setSingleGrain(0, y - 1, grains[2]);

                        nextGeneration.setSingleGrain(x - 1, y, grains[3]);
                        nextGeneration.setSingleGrain(0, y, grains[4]);

                        nextGeneration.setSingleGrain(x - 1, y + 1, grains[5]);
                        nextGeneration.setSingleGrain(x, y + 1, grains[6]);
                        nextGeneration.setSingleGrain(0, y + 1, grains[7]);

                        nextGeneration.setSingleGrain(x, y, grains[8]);
                    } else if (y == 0) {
                        Grain[] grains = checkIfWillGrow(generation.getSingleGrain(x, y),
                                generation.getSingleGrain(x - 1, sizeY - 1),
                                generation.getSingleGrain(x, sizeY - 1),
                                generation.getSingleGrain(x + 1, sizeY - 1),

                                generation.getSingleGrain(x - 1, y),
                                generation.getSingleGrain(x + 1, y),

                                generation.getSingleGrain(x - 1, y + 1),
                                generation.getSingleGrain(x, y + 1),
                                generation.getSingleGrain(x + 1, y + 1));

                        nextGeneration.setSingleGrain(x - 1, sizeY - 1, grains[0]);
                        nextGeneration.setSingleGrain(x, sizeY - 1, grains[1]);
                        nextGeneration.setSingleGrain(x + 1, sizeY - 1, grains[2]);

                        nextGeneration.setSingleGrain(x - 1, y, grains[3]);
                        nextGeneration.setSingleGrain(x + 1, y, grains[4]);

                        nextGeneration.setSingleGrain(x - 1, y + 1, grains[5]);
                        nextGeneration.setSingleGrain(x, y + 1, grains[6]);
                        nextGeneration.setSingleGrain(x + 1, y + 1, grains[7]);

                        nextGeneration.setSingleGrain(x, y, grains[8]);
                    } else if (y == sizeY - 1) {
                        Grain[] grains = checkIfWillGrow(generation.getSingleGrain(x, y),
                                generation.getSingleGrain(x - 1, y - 1),
                                generation.getSingleGrain(x, y - 1),
                                generation.getSingleGrain(x + 1, y - 1),

                                generation.getSingleGrain(x - 1, y),
                                generation.getSingleGrain(x + 1, y),

                                generation.getSingleGrain(x - 1, 0),
                                generation.getSingleGrain(x, 0),
                                generation.getSingleGrain(x + 1, 0));

                        nextGeneration.setSingleGrain(x - 1, y - 1, grains[0]);
                        nextGeneration.setSingleGrain(x, y - 1, grains[1]);
                        nextGeneration.setSingleGrain(x + 1, y - 1, grains[2]);

                        nextGeneration.setSingleGrain(x - 1, y, grains[3]);
                        nextGeneration.setSingleGrain(x + 1, y, grains[4]);

                        nextGeneration.setSingleGrain(x - 1, 0, grains[5]);
                        nextGeneration.setSingleGrain(x, 0, grains[6]);
                        nextGeneration.setSingleGrain(x + 1, 0, grains[7]);

                        nextGeneration.setSingleGrain(x, y, grains[8]);
                    } else {
                        Grain[] grains = checkIfWillGrow(generation.getSingleGrain(x, y),
                                generation.getSingleGrain(x - 1, y - 1),
                                generation.getSingleGrain(x, y - 1),
                                generation.getSingleGrain(x + 1, y - 1),

                                generation.getSingleGrain(x - 1, y),
                                generation.getSingleGrain(x + 1, y),

                                generation.getSingleGrain(x - 1, y + 1),
                                generation.getSingleGrain(x, y + 1),
                                generation.getSingleGrain(x + 1, y + 1));

                        nextGeneration.setSingleGrain(x - 1, y - 1, grains[0]);
                        nextGeneration.setSingleGrain(x, y - 1, grains[1]);
                        nextGeneration.setSingleGrain(x + 1, y - 1, grains[2]);

                        nextGeneration.setSingleGrain(x - 1, y, grains[3]);
                        nextGeneration.setSingleGrain(x + 1, y, grains[4]);

                        nextGeneration.setSingleGrain(x - 1, y + 1, grains[5]);
                        nextGeneration.setSingleGrain(x, y + 1, grains[6]);
                        nextGeneration.setSingleGrain(x + 1, y + 1, grains[7]);

                        nextGeneration.setSingleGrain(x, y, grains[8]);
                    }
                }
            }
        } else {
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    Grain[] grains = checkIfWillGrow(generation.getSingleGrain(x, y),
                            generation.getSingleGrain(x - 1, y - 1),
                            generation.getSingleGrain(x, y - 1),
                            generation.getSingleGrain(x + 1, y - 1),

                            generation.getSingleGrain(x - 1, y),
                            generation.getSingleGrain(x + 1, y),

                            generation.getSingleGrain(x - 1, y + 1),
                            generation.getSingleGrain(x, y + 1),
                            generation.getSingleGrain(x + 1, y + 1));

                    nextGeneration.setSingleGrain(x - 1, y - 1, grains[0]);
                    nextGeneration.setSingleGrain(x, y - 1, grains[1]);
                    nextGeneration.setSingleGrain(x + 1, y - 1, grains[2]);

                    nextGeneration.setSingleGrain(x - 1, y, grains[3]);
                    nextGeneration.setSingleGrain(x + 1, y, grains[4]);

                    nextGeneration.setSingleGrain(x - 1, y + 1, grains[5]);
                    nextGeneration.setSingleGrain(x, y + 1, grains[6]);
                    nextGeneration.setSingleGrain(x + 1, y + 1, grains[7]);

                    nextGeneration.setSingleGrain(x, y, grains[8]);
                }
            }
        }

        return new Generation(nextGeneration.getAllGrains());
    }
}
