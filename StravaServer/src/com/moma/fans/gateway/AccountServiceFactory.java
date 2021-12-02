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
    public static AccountServiceGateway createAccountService (Enum<RegisterType> service) {

       if (service == null) {

           return null;
       }

       if (service.equals(RegisterType.GOOGLE)) {

           return new GoogleAccountService();
       }

       else if (service.equals(RegisterType.FACEBOOK)) {

           return new FacebookAccountService();
       }

       return null;

    }
}
