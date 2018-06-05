package com.company.enums;

public enum NucleonsArrangementType {
    LOSOWE("Losowe"),
    ROWNOMIERNE("Równomierne"),
    LOSOWE_Z_PROMIENIEM_R("Losowe z promieniem R"),
    PRZEZ_KLIKANIE("Przez klikanie"),
    CIAGLE_LOSOWANIE("Ciągle losowanie zarodków");

    private String inclusionMethod;

    NucleonsArrangementType(String method) {
        this.inclusionMethod = method;
    }

    @Override
    public String toString() {
        return this.inclusionMethod;
    }
}
