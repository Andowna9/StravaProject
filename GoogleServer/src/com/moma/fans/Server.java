package com.moma.fans;

import com.moma.fans.remote.GoogleService;
import com.moma.fans.remote.IGoogleService;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author UnaiCL
 *
 */
public class Server {

    public static void main(String [] args) {

        if (System.getSecurityManager() == null) {

            System.setSecurityManager(new SecurityManager());
        }

        String ip = args[0];
        String port = args[1];
        String name = args[2];

        try {
            IGoogleService googleService = new GoogleService();
            Registry registry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            registry.rebind(name, (Remote) googleService);
            System.out.println("* Servidor de Google activo");
        } catch (Exception e) {
            System.err.println("Error al realizar la conexión para google: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
