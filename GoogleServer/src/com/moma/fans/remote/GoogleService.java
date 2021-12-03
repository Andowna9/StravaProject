package com.moma.fans.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author UnaiCL
 * @author Julen396
 * @author JonanC
 *
 */
public class GoogleService extends UnicastRemoteObject implements IGoogleService {

	Map<String, String> users = Collections.unmodifiableMap(new HashMap<>() {
		
		private static final long serialVersionUID = 1L;

	{

		put("roberto@gmail.com", "diseñosoftware");
		put("josuKa@gmail.com", "cyc");
		put("puma@gmail.com", "aco");
		put("joselu@gmail.com", "profe");
		put("mikel@gmail.com", "sistemasoperativos");
		put("bujan@gmail.com", "ingenieriaweb");

	}});
	
	private static final long serialVersionUID = 1L;

	public GoogleService() throws RemoteException {
		super();
	}

	@Override
	public boolean authenticate(String email, String password) throws RemoteException {

		System.out.println("# Google Service - Comprobando validez de usuario: " + email + "/" + password);
		return (users.containsKey(email) && users.get(email).equals(password));
	}

}