package com.company.enums;

public enum NucleonsArrangementType {
    LOSOWE("Losowe"),
    ROWNOMIERNE("Równomierne"),
    LOSOWE_Z_PROMIENIEM_R("Losowe z promieniem R");

    private String inclusionMethod;

    NucleonsArrangementType(String method) {
        this.inclusionMethod = method;
    }

    @Override
    public String toString() {
        return this.inclusionMethod;
    }
}
