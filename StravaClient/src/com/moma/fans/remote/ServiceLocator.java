package com.moma.fans.remote;

import java.rmi.Naming;

/**
 * Clase para establecer la comunicación con el servidor,
 * en este caso utilizando tecnología RMI.
 * Implementa el patrón de diseño ServiceLocator.
 * @author JonanC
 */
public class ServiceLocator {

    IRemoteFacade service;

    /**
     * Inicializa el servicio remoto.
     * @param ip Dirección IP del servidor
     * @param port Puerto de comunicación del servidor
     * @param serviceName Nombre del servicio remoto
     */
    public void setService(String ip, String port, String serviceName) {

        // Administrador de seguridad requerido por RMI
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {

            String URL = "//" + ip + ":" + port + "/" + serviceName;
            this.service = (IRemoteFacade) Naming.lookup(URL);

        } catch (Exception e) {

            System.err.println("Error al localizar fachada remota: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Punto de acceso al servicio ofrecido por el servidor.
     * @return interfaz de acceso remoto a funcionalidades
     */
    public IRemoteFacade getService() {

        return this.service;
    }
}
