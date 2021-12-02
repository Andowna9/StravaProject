package com.moma.fans.services;

import java.util.HashMap;
import java.util.Map;

import com.moma.fans.data.domain.LocalUser;
import com.moma.fans.data.domain.RegisterType;
import com.moma.fans.data.domain.User;
import com.moma.fans.gateway.AccountServiceFactory;
import com.moma.fans.gateway.AccountServiceGateway;

/**
 * Objeto que encapsula la lógica de negocio
 * relacionada con los usuarios.
 * @author JonanC
 */
public class UserAppService {

    // Mapa de usuarios con email como clave
    private Map<String, User> users = new HashMap<>();
    
    private Map<RegisterType, AccountServiceGateway> gateways = new HashMap<>();
    
    // Eager initialization
    private static final UserAppService INSTANCE = new UserAppService();

    private UserAppService() {
    	for (RegisterType type : RegisterType.values()) {
    		if (!type.equals(RegisterType.LOCAL)) {
    			gateways.put(type, AccountServiceFactory.createAccountService(type));
    		}
		}
    }

    public static UserAppService getInstance() {

        return INSTANCE;
    }

    /**
     * @param email correo electrónico
     * @param password contraseña
     * @return usuario, null si el email o contraseña son incorrectos
     */
    public User login(String email, String password) {

        User user = users.get(email);

        if (user != null) {

            // Local user check

            if (user.getRegisterType() == RegisterType.LOCAL) {

                LocalUser localUser = (LocalUser) user;

                if (localUser.isPasswordValid(password)) {

                    return localUser;
                }
            }

            // External user check

            else {

                AccountServiceGateway gateway = gateways.get(user.getRegisterType());

                if (gateway.authenticate(user.getEmail(), password)) {

                    return user;
                }
            }

        }

        return null;

    }

    /**
     * @param email correo electrónico
     * @param nickname nombre de usuario
     * @param password contraseña
     * @param registerType tipo de registro
     * @return usuario registrado, null si no ha sido posible el registro
     */
    public User registerUser(String email, String nickname, String password, RegisterType registerType) {

        // El usuario existe, registro inválido

        if (users.containsKey(email)) {

            return null;
        }

        User user = null;

        if (registerType == RegisterType.LOCAL) {

             user = new LocalUser(email, nickname, password);
        }

        // Conectar con servicio externo

        else {

            AccountServiceGateway gateway = gateways.get(registerType);

            if (gateway.authenticate(email, password)) {

                user = new User(registerType, email, nickname);
            }
        }

        if (user != null) users.put(user.getEmail(), user);

        return user;
    }

}
