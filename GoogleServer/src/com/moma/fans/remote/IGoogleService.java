package com.moma.fans.remote;

import java.rmi.RemoteException;
/**
 * 
 * @author UnaiCL
 * @author 	Julen396
 * 
 */
public interface IGoogleService {

	/** 
	 * 
	 * @param email Email de google
	 * @param password Contraseña de google
	 * @return True --> Existe, False --> No existe
	 * @throws RemoteException
	 */
	public boolean authenticate(String email, String password) throws RemoteException;
}
