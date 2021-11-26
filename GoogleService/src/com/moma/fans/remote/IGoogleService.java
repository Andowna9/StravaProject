package com.moma.fans.remote;

import java.rmi.RemoteException;
/**
 * 
 * @author UnaiCL
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
	public boolean checkUser(String email) throws RemoteException;
	public boolean checkPassword(String email, String password) throws RemoteException;
}
