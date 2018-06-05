package com.company.models.arrangements;

import com.company.Generation;
import com.company.interfaces.Arrangement;

public class Evenly implements Arrangement {
    @Override
    public void arrangement(Generation generation, int numberOfNucleons) {
        int tmp = 0;

        int square = (int) Math.sqrt(numberOfNucleons);

        int jump = (generation.getSizeX() / square);

        while (tmp < numberOfNucleons) {
            for (int i = 0; i < generation.getSizeX(); i += jump) {
                for (int j = 0; j < generation.getSizeY(); j += jump) {
                    generation.setSingleGrain(i, j);
                    tmp++;
                    if (tmp < numberOfNucleons)
                        break;
                }
            }
        }
    }
}
