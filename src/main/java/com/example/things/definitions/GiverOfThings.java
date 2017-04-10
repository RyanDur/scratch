package com.example.things.definitions;

public enum GiverOfThings {
    BROOKS("Mell Brooks"), PAUL("Pelafas");

    private final String name;

    GiverOfThings(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
