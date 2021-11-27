package com.moma.fans.gateway;

import com.moma.fans.remote.IGoogleService;

import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Servicio de cuentas de Google.
 * Implementa el patrón de diseño Gateway.
 * @author JonanC
 */
public class GoogleAccountService implements AccountServiceGateway {

    private IGoogleService googleService;

    // Conexión a servidor con RMI
    public GoogleAccountService() {
        try {
            String URL = "//127.0.0.1:1099/GoogleServer";
            googleService = (IGoogleService) Naming.lookup(URL);
        } catch (Exception e) {
            System.err.println("# Error contactando con servidor de Google: " + e.getMessage());
        }
    }

    @Override
    public boolean authenticate(String email, String password) {

        try {

            return googleService.authenticate(email, password);

        }

        catch (RemoteException e) {

            return false;
        }

    }
}
