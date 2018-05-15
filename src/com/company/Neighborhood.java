package com.company;

import com.company.interfaces.Rule;

public abstract class Neighborhood implements Rule {
    public abstract Grain[] checkIfWillGrow(Grain current,
                                            Grain neighbour1, Grain neighbour2, Grain neighbour3,
                                            Grain neighbour4, Grain neighbour6,
                                            Grain neighbour7, Grain neighbour8, Grain neighbour9);

    @Override
    public Generation nextGeneration(Generation generation) {
        Generation nextGeneration = new Generation(new Grain[generation.getSizeY()][generation.getSizeX()], generation.getUsedGrainColors());

        int sizeX = generation.getSizeX();
        int sizeY = generation.getSizeY();

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

        return new Generation(nextGeneration.getAllGrains());
    }
}
