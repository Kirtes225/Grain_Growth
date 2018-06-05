package com.company.enums;

public enum ModelsType {
    MOORE("Moore"),
    VON_NEUMANN("Von Neumann"),
    HEXAGONAL_LEFT("Hexagonal Left"),
    HEXAGONAL_RIGHT("Hexagonal Right"),
    HEXAGONAL_RANDOM("Hexagonal Random"),
    PENTAGONAL_RANDOM("Pentagonal Random");

    private String modelsMethod;

    ModelsType(String method) {
        this.modelsMethod = method;
    }

    @Override
    public String toString() {
        return this.modelsMethod;
    }
}
