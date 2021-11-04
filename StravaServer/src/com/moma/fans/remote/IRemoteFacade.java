package com.moma.fans.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteFacade extends Remote {

    String test() throws RemoteException;

    // TODO Add interface offered by server to the client

}
