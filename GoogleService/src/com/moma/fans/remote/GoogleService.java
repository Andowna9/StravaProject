package com.moma.fans.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author UnaiCL
 *
 */
public class GoogleService extends UnicastRemoteObject implements IGoogleService{

	Map<String, String> users = new HashMap<>();
	
	private static final long serialVersionUID = 1L;

	public GoogleService() throws RemoteException {
		super();
		users.put("roberto@gmail.com", "diseñosoftware");
		users.put("josuKa@gmail.com", "cyc");
		users.put("puma@gmail.com", "aco");
		users.put("joselu@gmail.com", "profe");
		users.put("mikel@gmail.com", "sistemasoperativos");
		users.put("bujan@gmail.com", "ingenieriaweb");
	}
	
	@Override
	public boolean checkUser(String email) throws RemoteException {
		return users.containsKey(email);
	}

	@Override
	public boolean checkPassword(String email, String password) throws RemoteException {
		return users.get(email).equals(password);
	}

}