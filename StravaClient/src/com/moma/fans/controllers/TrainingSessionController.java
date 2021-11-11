package com.moma.fans.controllers;

import com.moma.fans.remote.ServiceLocator;

/**
 * Controlador de operaciones con sesiones
 * (creación, recuperación, etc.).
 * @author JonanC
 */
public class TrainingSessionController {

    private ServiceLocator serviceLocator;

    public TrainingSessionController(ServiceLocator serviceLocator) {

        this.serviceLocator = serviceLocator;
    }
}
