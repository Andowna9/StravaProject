package com.moma.fans.gateway;

/**
 * Interfaz para servicios de cuentas.
 * @author JonanC
 */
public interface AccountServiceGateway {

    boolean authenticate(String email, String password);
}
