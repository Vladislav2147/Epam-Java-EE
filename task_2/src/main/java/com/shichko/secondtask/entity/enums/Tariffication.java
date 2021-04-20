package com.shichko.secondtask.entity.enums;

import java.util.EnumSet;

public enum Tariffication {
    TWELVE_SECONDS("12 seconds"),
    MINUTE("1 minute"),
    HOUR("1 hour");

    private String value;

    Tariffication(String value) {
        this.value = value;
    }

    public static Tariffication findByValue(String value) {
        EnumSet<Tariffication> tariffications = EnumSet.allOf(Tariffication.class);
        Tariffication found = tariffications
                .stream()
                .filter(tariffication -> tariffication.value.equals(value))
                .findFirst()
                .orElse(null);
        return found;
    }
}
