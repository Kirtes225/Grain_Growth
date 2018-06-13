package com.company.models.rules;

import com.company.Grain;
import com.company.Neighborhood;

import java.util.Random;

public class HexagonalRandom extends Neighborhood {

//    000
//    010
//    000
//
//     |
//     V
//
//    011
//    111
//    110
//
//    OR
//
//    000
//    010
//    000
//
//     |
//     V
//
//    110
//    111
//    011

    private boolean randomNumber;

    public HexagonalRandom() {
        Random random = new Random();
        this.randomNumber = random.nextBoolean();
    }

    @Override
    public Grain[] checkIfWillGrow(Grain current,
                                   Grain neighbour1, Grain neighbour2, Grain neighbour3,
                                   Grain neighbour4, Grain neighbour6,
                                   Grain neighbour7, Grain neighbour8, Grain neighbour9, boolean monteCarlo){
        Grain[] grains = new Grain[9];
        if(randomNumber) {
            if (current != null) {
//                if (neighbour1 == null) {
//                    grains[0] = new Grain(current);
//                }
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
//                if (neighbour9 == null) {
//                    grains[7] = new Grain(current);
//                }
                grains[8] = new Grain(current);
            }
        }
        else {
            if (current != null) {
                if (neighbour1 == null) {
                    grains[0] = new Grain(current);
                }
                if (neighbour2 == null) {
                    grains[1] = new Grain(current);
                }
//            if (neighbour3 == null) {
//                grains[2] = new Grain(current);
//            }

                if (neighbour4 == null) {
                    grains[3] = new Grain(current);
                }
                if (neighbour6 == null) {
                    grains[4] = new Grain(current);
                }

//            if (neighbour7 == null) {
//                grains[5] = new Grain(current);
//            }
                if (neighbour8 == null) {
                    grains[6] = new Grain(current);
                }
                if (neighbour9 == null) {
                    grains[7] = new Grain(current);
                }
                grains[8] = new Grain(current);
            }
        }
        return grains;
    }
}
