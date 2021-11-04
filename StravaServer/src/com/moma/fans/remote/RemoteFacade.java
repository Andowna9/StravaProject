package com.moma.fans.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade{

    public RemoteFacade() throws RemoteException {
        super();
    }

    @Override
    public String test() throws RemoteException {

        return "TEST";
    }
}
