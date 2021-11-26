package com.moma.fans.service;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.moma.fans.remote.GoogleService;
import com.moma.fans.remote.IGoogleService;
/**
 * 
 * @author UnaiCL
 *
 */
public class Service {

    public static void main(String [] args) {
    	
    	String ip = args[0];
		String port = args[1];
		String name = args[2];
		
		try {
			IGoogleService googleService = new GoogleService();
			Registry registry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
			registry.bind(name, (Remote) googleService);
			System.out.println("*Conexión para el servidor google realizada");
		} catch (Exception e) {
            System.err.println("Error al realizar la conexión para google: " + e.getMessage());
            e.printStackTrace();
		}

    }
}
