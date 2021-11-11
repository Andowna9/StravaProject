package com.moma.fans.controllers;

import com.moma.fans.remote.ServiceLocator;

/**
 * Controlador de operaciones con retos
 * (creación, recuperación, apuntarse, etc.).
 * @author JonanC
 */
public class ChallengeController {

    private ServiceLocator serviceLocator;

    public ChallengeController(ServiceLocator serviceLocator) {

        this.serviceLocator = serviceLocator;
    }
}
