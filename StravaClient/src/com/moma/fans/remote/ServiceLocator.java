package com.moma.fans.remote;

import java.rmi.Naming;

public class ServiceLocator {

    IRemoteFacade service;

    public void setService(String ip, String port, String serviceName) {

        // Administrador de seguridad requerido por RMI
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {

            String URL = "//" + ip + ":" + port + "/" + serviceName;
            this.service = (IRemoteFacade) Naming.lookup(URL);
            System.out.println("Server says: " + service.test());

        } catch (Exception e) {

            System.err.println("Error al localizar fachada remota: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public IRemoteFacade getService() {

        return this.service;
    }
}
