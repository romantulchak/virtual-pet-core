package com.virtualpet.model.enums;

public enum EUniqueness {
    COMMON(0), UNCOMMON(1), RARE(2), MYTHICAL(3), LEGENDARY(4);

    private int value;

    EUniqueness(int value){
        this.value = value;
    }
}
