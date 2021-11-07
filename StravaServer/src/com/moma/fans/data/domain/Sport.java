package com.moma.fans.data.domain;

public enum Sport {

    RUNNING("Running"), CYCLING("Ciclismo");

    private String name;
    private Sport(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return this.name;
    }
}
