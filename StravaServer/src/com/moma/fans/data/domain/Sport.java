package com.moma.fans.data.domain;

import javax.lang.model.element.Element;

public enum Sport {

    RUNNING("Running"), CYCLING("Ciclismo");

    private final String name;

    Sport(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return this.name;
    }

    public static Sport valueOfSport(String value) {

        for (Sport s: values()) {

            if (s.name.equalsIgnoreCase(value)) return s;
        }
        return null;
    }
}
