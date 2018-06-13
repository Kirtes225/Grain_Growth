package com.company.models.arrangements;

import com.company.Generation;
import com.company.Utils;
import com.company.interfaces.Arrangement;

public class FillWholeSpace implements Arrangement {
    @Override
    public void arrangement(Generation generation, int numberOfNucleons) {
        int maxID = numberOfNucleons;
        int width = generation.getSizeX();
        int height = generation.getSizeY();

        Generation.generateColors(numberOfNucleons);

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                generation.setSingleGrain(x, y, Utils.randomInt(0, maxID));
            }
        }
    }
}
