package com.moma.fans;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements ServerAPI {

    public Server() throws RemoteException {

        super();
    }

    public static void main(String [] args) {

        try {

            ServerAPI serviceObject = new Server();
            Naming.rebind("//localhost:1099/StravaServer", serviceObject);
            System.out.println("* Server active *");

        }

        catch (Exception e) {

            System.err.println("Exception running server: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public String test() throws RemoteException {

        return "Test";
    }
}
