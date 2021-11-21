package com.moma.fans;

import com.moma.fans.remote.IRemoteFacade;
import com.moma.fans.remote.RemoteFacade;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String [] args) {

        // Administrador de seguridad requerido por RMI
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        // Ligar la instancia de RemoteFacade al servicio de nombres de RMI

        try {

            int port = Integer.parseInt(args[0]);
            String name = args[1];

            // Creación del registro RMI en puerte por defecto
            // Para detenerlo explícitamente: UnicastRemoteObject.unexportObject(registry, true);

            Registry registry = LocateRegistry.createRegistry(port);

            // Ligar instancia de fachada remota al registro creado

            IRemoteFacade remoteFacade = new RemoteFacade();
            registry.bind(name, remoteFacade);
            System.out.println("* Servidor " + name + " activo!");

        } catch (Exception e) {
            System.err.println("Excepción en servidor Strava: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
