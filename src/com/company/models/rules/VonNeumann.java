package com.company.models.rules;

import com.company.Grain;
import com.company.Neighborhood;

import java.util.HashMap;
import java.util.Map;

public class VonNeumann extends Neighborhood {

//    000
//    010
//    000
//     |
//     V
//
//    010
//    111
//    010

    @Override
    public Grain[] checkIfWillGrow(Grain current,
                                   Grain neighbour1, Grain neighbour2, Grain neighbour3,
                                   Grain neighbour4, Grain neighbour6,
                                   Grain neighbour7, Grain neighbour8, Grain neighbour9, boolean monteCarlo) {
        Grain[] grains;
        if(monteCarlo){
            grains = new Grain[9];
            //grains[0] = current;

            //grains[1] = neighbour1;
            grains[2] = neighbour2;
            //grains[3] = neighbour3;

            grains[4] = neighbour4;
            grains[5] = neighbour6;

            //grains[6] = neighbour7;
            grains[7] = neighbour8;
            //grains[8] = neighbour9;

            Map<Integer, Integer> tmp = new HashMap<>();
            if (current != null)
                if (tmp.containsKey(current.getID()))
                    tmp.put(current.getID(), tmp.get(current.getID()) + 1);
                else
                    tmp.put(current.getID(), 1);
//            if (neighbour1 != null)
//                if (tmp.containsKey(neighbour1.getID()))
//                    tmp.put(neighbour1.getID(), tmp.get(neighbour1.getID()) + 1);
//                else
//                    tmp.put(neighbour1.getID(), 1);
            ////////////////////////////////////
            if (neighbour2 != null)
                if (tmp.containsKey(neighbour2.getID()))
                    tmp.put(neighbour2.getID(), tmp.get(neighbour2.getID()) + 1);
                else
                    tmp.put(neighbour2.getID(), 1);
            ///////////////////////////////////
//            if (neighbour3 != null)
//                if (tmp.containsKey(neighbour3.getID()))
//                    tmp.put(neighbour3.getID(), tmp.get(neighbour3.getID()) + 1);
//                else
//                    tmp.put(neighbour3.getID(), 1);
                ///////////////////////////////
            if (neighbour4 != null)
                if (tmp.containsKey(neighbour4.getID()))
                    tmp.put(neighbour4.getID(), tmp.get(neighbour4.getID()) + 1);
                else
                    tmp.put(neighbour4.getID(), 1);
            ///////////////////////////////////
            if (neighbour6 != null)
                if (tmp.containsKey(neighbour6.getID()))
                    tmp.put(neighbour6.getID(), tmp.get(neighbour6.getID()) + 1);
                else
                    tmp.put(neighbour6.getID(), 1);
            ///////////////////////////////////
//            if (neighbour7 != null)
//                if (tmp.containsKey(neighbour7.getID()))
//                    tmp.put(neighbour7.getID(), tmp.get(neighbour7.getID()) + 1);
//                else
//                    tmp.put(neighbour7.getID(), 1);
            ///////////////////////////////////
            if (neighbour8 != null)
                if (tmp.containsKey(neighbour8.getID()))
                    tmp.put(neighbour8.getID(), tmp.get(neighbour8.getID()) + 1);
                else
                    tmp.put(neighbour8.getID(), 1);
            ///////////////////////////////////
//            if (neighbour9 != null)
//                if (tmp.containsKey(neighbour9.getID()))
//                    tmp.put(neighbour9.getID(), tmp.get(neighbour9.getID()) + 1);
//                else
//                    tmp.put(neighbour9.getID(), 1);

//            System.out.println(tmp.size());
//
//            for (Map.Entry<Integer, Integer> iterate : tmp.entrySet()) {
//                System.out.println(iterate.getKey() + " " + iterate.getValue());
//            }

            Integer minEnergyKey //= Collections.min(tmp.entrySet(), Map.Entry.comparingByValue()).getKey();
                    = tmp.entrySet()
                    .stream()
                    .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                    .get()
                    .getKey();
//            System.out.println(current.getID() + " " + minEnergyKey);

            for (int i = 1; i < grains.length; i++)
                if (grains[i] != null)
                    if (grains[i].getID() == minEnergyKey) {
//                        System.out.println("CURRENT: " + current + " " + grains[i]);
                        grains[0] = grains[i];
                        break;
                    }

            return grains;
        }
        else{
            grains = new Grain[9];
            if (current != null) {
//            if (neighbour1 == null) {
//                grains[0] = new Grain(current);
//            }
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
//            if (neighbour9 == null) {
//                grains[7] = new Grain(current);
//            }
                grains[8] = new Grain(current);
            }
            return grains;
        }
    }
}
