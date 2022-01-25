package com.moma.fans.data.domain;

/**
 * Enumeración para tipos de
 * registro disponibles.
 * @author JonanC
 */
public enum RegisterType {

    LOCAL("Local") , GOOGLE("Google"), FACEBOOK("Facebook");

    private final String name;

    RegisterType(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return this.name;
    }

    public static RegisterType valueOfRegisterType(String value) {

        for (RegisterType rt: values()) {

            if (rt.name.equalsIgnoreCase(value)) return rt;
        }

        return null;
    }
}
