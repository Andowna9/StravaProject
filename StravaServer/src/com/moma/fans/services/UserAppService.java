package com.moma.fans.services;

import java.util.HashMap;
import java.util.Map;

import com.moma.fans.data.dao.UserDAO;
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

    // Mapa de gateways correspondientes a servicios de cuentas
    private Map<RegisterType, AccountServiceGateway> gateways = new HashMap<>();
    
    // Eager initialization
    private static final UserAppService INSTANCE = new UserAppService();

    private UserAppService() {
    	 for (RegisterType type : RegisterType.values()) {
    		if (!(type == RegisterType.LOCAL)) {
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

        User user = UserDAO.getInstance().getByEmail(email);

        if (user != null) {

            // Local user check

            if (user.getRegisterType() == RegisterType.LOCAL) {

                try {

                    LocalUser localUser = (LocalUser) user;

                    if (localUser.isPasswordValid(password)) {

                        return localUser;
                    }
                }

                catch(Exception e) {

                    e.printStackTrace();
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

        if (UserDAO.getInstance().getByEmail(email) != null) {

            return null;
        }

        System.out.println(registerType);

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

        if (user != null) UserDAO.getInstance().save(user);

        return user;
    }

}
