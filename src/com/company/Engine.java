package com.company;

import com.company.interfaces.Rule;


public class Engine {
    private Rule rule;
    private Generation generation;

    public Engine(Rule rule, Generation generation) {
        this.rule = rule;
        this.generation = generation;
    }

    public synchronized Generation nextGeneration() {
        return this.generation = this.rule.nextGeneration(this.generation);
    }

    public synchronized Generation getGeneration() {
        return this.generation;
    }

}
