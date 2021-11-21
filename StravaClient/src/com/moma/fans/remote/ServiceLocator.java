package com.moma.fans.remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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


        try {

            Registry registry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            this.service = (IRemoteFacade) registry.lookup(serviceName);

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
