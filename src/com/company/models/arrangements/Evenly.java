package com.company.models.arrangements;

import com.company.Generation;
import com.company.interfaces.Arrangement;

public class Evenly implements Arrangement {
    @Override
    public void arrangement(Generation generation, int numberOfNucleons) {
        int tmp = 0;

        int square = (int) Math.sqrt(numberOfNucleons);

        double jumpX =  (generation.getSizeX() / square);
        double jumpY =  (generation.getSizeY() / square);


//       while (tmp < numberOfNucleons) {
            for (double i = jumpX/2; i < generation.getSizeX(); i += jumpX) {
                for (double j = jumpY/2; j < generation.getSizeY(); j += jumpY) {
                    generation.setSingleGrain((int)i, (int)j);
//                    tmp++;
//                    if (tmp < numberOfNucleons)
//                        break;
                }
            }
//        }
    }
}
