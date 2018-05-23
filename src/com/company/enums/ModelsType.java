package com.company.enums;

public enum ModelsType {
    MOORE("Moore"),
    VON_NEUMANN("Von Neumann");

    private String modelsMethod;

    ModelsType(String method) {
        this.modelsMethod = method;
    }

    @Override
    public String toString() {
        return this.modelsMethod;
    }
}
