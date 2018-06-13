package com.company.models.arrangements;

import com.company.Generation;
import com.company.interfaces.Arrangement;

public class OnlyByClicking implements Arrangement {
    @Override
    public void arrangement(Generation generation, int numberOfNucleons) {
        //nothing - places are chosen by user
    }
}
