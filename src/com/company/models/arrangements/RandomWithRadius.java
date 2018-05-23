package com.company.models.arrangements;

import com.company.Generation;
import com.company.interfaces.Arrangement;

public class RandomWithRadius implements Arrangement {
    private int radius;

    public RandomWithRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void arrangement(Generation generation, int width, int height, int numberOfNucleons) {
    }
}
