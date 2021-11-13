package com.moma.fans.controllers;

import java.rmi.RemoteException;

import com.moma.fans.data.dto.user.ProfileCreationDTO;
import com.moma.fans.remote.ServiceLocator;

/**
 * Controlador de operaciones de acceso
 * a nivel de usuario de la aplicación.
 * @author JonanC
 * @author UnaiCL
 */
public class UserController {

    private ServiceLocator serviceLocator;

	// Este atributo guarda el token en caso de éxito
	private long token = -1;
    
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
	
	public void register(String email, String nickname, String password) throws RemoteException {
		try {
			this.token = this.serviceLocator.getService().register(email, nickname, password);
		}

		catch (RemoteException e) {
			this.token = -1;
			throw e;
		}
	}

	public void createProfile(ProfileCreationDTO userDTO) throws RemoteException {

		this.serviceLocator.getService().createProfile(this.token, userDTO);
	}

	public long getToken() {

		return token;
	}
}
