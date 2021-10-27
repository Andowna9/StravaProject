package com.moma.fans;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerAPI extends Remote {

    String test() throws RemoteException;

    // TODO Add interface offered by server to the client

}
