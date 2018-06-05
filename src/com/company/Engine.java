package com.company;

import com.company.interfaces.Arrangement;
import com.company.interfaces.Rule;


public class Engine {
    private Rule rule;
    private Generation generation;
    private boolean periodic;
    private Arrangement arrangement;
    private int nucleonsNumber;

    public Arrangement getArrangement() {
        return arrangement;
    }

    public Engine(Rule rule, Generation generation, boolean periodic, Arrangement arrangement, int nucleonsNumber) {
        this.rule = rule;
        this.generation = generation;
        this.periodic = periodic;
        this.arrangement = arrangement;
        this.nucleonsNumber = nucleonsNumber;
    }

    public synchronized void arrangementOfGeneration(){
        this.arrangement.arrangement(this.generation, nucleonsNumber);
    }

    public synchronized Generation nextGeneration() {
        return this.generation = this.rule.nextGeneration(this.generation, periodic);
    }

    public synchronized Generation getGeneration() {
        return this.generation;
    }

}
