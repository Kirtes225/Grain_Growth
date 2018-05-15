package com.company.interfaces;

import com.company.Generation;

public interface Rule {
    Generation nextGeneration(Generation generation);
}
