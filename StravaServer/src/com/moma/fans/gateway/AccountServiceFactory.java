package com.moma.fans.gateway;

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
    public static AccountServiceGateway createAccountService (String service) {

       if (service == null) {

           return null;
       }

       if (service.equalsIgnoreCase("GOOGLE")) {

           return new GoogleAccountService();
       }

       else if (service.equalsIgnoreCase("FACEBOOK")) {

           return new FacebookAccountService();
       }

       return null;

    }
}
