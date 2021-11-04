package com.moma.fans;

import com.moma.fans.remote.IRemoteFacade;
import com.moma.fans.remote.RemoteFacade;

import java.rmi.Naming;

public class Server {

    public static void main(String [] args) {

        // Administrador de seguridad requerido por RMI
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        // Ligar la instancia de RemoteFacade al servicio de nombres de RMI

        try {

            String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
            IRemoteFacade remoteFacade = new RemoteFacade();
            Naming.rebind(name, remoteFacade);
            System.out.println("* Servidor " + name + " activo!");

        } catch (Exception e) {
            System.err.println("Excepción en servidor Strava: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
