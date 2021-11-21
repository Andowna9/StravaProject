package com.moma.fans.data.domain;


/**
 * Enumeración para deportes
 * disponibles.
 * @author JonanC
 */
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
