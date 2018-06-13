package com.company;

import com.company.interfaces.Arrangement;
import com.company.interfaces.Rule;
import com.company.models.arrangements.FillWholeSpace;


public class Engine {
    private Rule rule;
    private Generation generation;
    private boolean periodic;
    private Arrangement arrangement;
    private int nucleonsNumber;
    private boolean monteCarlo;

    public Engine(Rule rule, Generation generation, boolean periodic, Arrangement arrangement, int nucleonsNumber) {
        this.rule = rule;
        this.generation = generation;
        this.periodic = periodic;
        this.arrangement = arrangement;
        this.nucleonsNumber = nucleonsNumber;
        this.monteCarlo = false;
    }


    public Engine(Rule rule, Generation generation, boolean periodic, int nucleonsNumber, boolean monteCarlo) {
        this.rule = rule;
        this.generation = generation;
        this.periodic = periodic;
        this.arrangement = new FillWholeSpace();
        this.nucleonsNumber = nucleonsNumber;
        this.monteCarlo = monteCarlo;
    }

    public boolean isMonteCarlo() {
        return monteCarlo;
    }

    public void setMonteCarlo(boolean monteCarlo) {
        this.monteCarlo = monteCarlo;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public synchronized void arrangementOfGeneration() {
        this.arrangement.arrangement(this.generation, nucleonsNumber);
    }

    public synchronized Generation nextGeneration() {
        return this.generation = this.rule.nextGeneration(this.generation, periodic, monteCarlo);
    }

    public synchronized Generation getGeneration() {
        return this.generation;
    }

}
