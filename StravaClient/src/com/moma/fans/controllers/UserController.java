package com.moma.fans.controllers;

import com.moma.fans.remote.ServiceLocator;

/**
 * Controlador de operaciones de acceso
 * a nivel de usuario de la aplicación.
 * @author JonanC
 */
public class UserController {

    private ServiceLocator serviceLocator;

    public UserController(ServiceLocator serviceLocator) {

        this.serviceLocator = serviceLocator;
    }
}
