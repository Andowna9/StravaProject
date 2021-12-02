package com.moma.fans.gateway;

import com.moma.fans.data.domain.RegisterType;

/**
 * Clase que permite instanciar servicios de cuentas.
 * Implementa el patrón de diseño Factory.
 * @author JonanC
 */
public class AccountServiceFactory {

    /**
     * @param service nombre de servicio
     * @return servicio de cuentas para comprobar credenciales
     */
    public static AccountServiceGateway createAccountService (RegisterType service) {

      switch (service) {

          case GOOGLE:
              return new GoogleAccountService();

          case FACEBOOK:
              return new FacebookAccountService();

          default:
              return null;
      }

    }
}
