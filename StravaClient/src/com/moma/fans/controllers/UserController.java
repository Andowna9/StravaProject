package com.moma.fans.controllers;

import java.rmi.RemoteException;

import com.moma.fans.data.dto.UserCreationDTO;
import com.moma.fans.remote.ServiceLocator;

/**
 * Controlador de operaciones de acceso
 * a nivel de usuario de la aplicación.
 * @author JonanC
 * @author UnaiCL
 */
public class UserController {

    private ServiceLocator serviceLocator;
	//This attibute stores the token when login success
	private long token = -1; //-1 = login has not been done or fails
    
    
    public UserController(ServiceLocator serviceLocator) {

        this.serviceLocator = serviceLocator;
    }
    
	public boolean login(String email, String password) throws RemoteException {
		try {
			this.token = this.serviceLocator.getService().login(email, password);
			return true;
		} catch (RemoteException e) {
			this.token = -1;
			throw e;
		}
		
	}
	
	public void logout() throws RemoteException {
		try {
			this.serviceLocator.getService().logout(this.token);
			this.token = -1;
		} catch (RemoteException e) {
			throw new RemoteException("Las sesión proporcionada no existe!");
		}
	}
	
	public void register(String email, String password) throws RemoteException {
		UserCreationDTO uDTO = new UserCreationDTO();
		uDTO.setEmail(email);
		uDTO.setPassword(password);
		
		try {
			this.serviceLocator.getService().register(uDTO);
		} catch (RemoteException e) {
			throw new RemoteException("Error al registrar un nuevo usuario");
		}
	}
}
