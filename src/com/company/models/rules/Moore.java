package com.company.models.rules;

import com.company.Grain;
import com.company.Neighborhood;

public class Moore extends Neighborhood {

//    000
//    010
//    000
//     |
//     V
//
//    111
//    111
//    111

    @Override
    public Grain[] checkIfWillGrow(Grain current,
                                 Grain neighbour1, Grain neighbour2, Grain neighbour3,
                                 Grain neighbour4, Grain neighbour6,
                                 Grain neighbour7, Grain neighbour8, Grain neighbour9){

        Grain[] grains = new Grain[9];
        if(current != null) {
            if (neighbour1 == null) {
                grains[0] = new Grain(current);
            }
            if (neighbour2 == null) {
                grains[1] = new Grain(current);
            }
            if (neighbour3 == null) {
                grains[2] = new Grain(current);
            }

            if (neighbour4 == null) {
                grains[3] = new Grain(current);
            }
            if (neighbour6 == null) {
                grains[4] = new Grain(current);
            }

            if (neighbour7 == null) {
                grains[5] = new Grain(current);
            }
            if (neighbour8 == null) {
                grains[6] = new Grain(current);
            }
            if (neighbour9 == null) {
                grains[7] = new Grain(current);
            }
            grains[8] = new Grain(current);
        }
        return grains;
    }
}
