package com.moma.fans.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 
 * @author UnaiCL
 * @author 	Julen396
 * 
 */
public interface IGoogleService extends Remote {

	/** 
	 * 
	 * @param email Email de google
	 * @param password Contraseña de google
	 * @return True --> Existe, False --> No existe
	 * @throws RemoteException
	 */
	public boolean authenticate(String email, String password) throws RemoteException;
}
