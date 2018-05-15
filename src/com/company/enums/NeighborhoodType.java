package com.company.enums;

public enum NeighborhoodType {
    MOORE("Moore"),
    VON_NEUMANN("Von Neumann");

    private String neighborhoodMethod;

    NeighborhoodType(String method) {
        this.neighborhoodMethod = method;
    }

    @Override
    public String toString() {
        return this.neighborhoodMethod;
    }
}
