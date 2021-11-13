package com.moma.fans.services;

import com.moma.fans.data.domain.User;
import java.util.HashMap;
import java.util.Map;

/**
 * Objeto que encapsula la lógica de negocio
 * relacionada con los usuarios.
 * @author JonanC
 */
public class UserAppService {

    // Mapa de usuarios con email como clave
    private Map<String, User> users = new HashMap<>();

    public User login(String email, String password) {

        User user = users.get(email);

        if (user != null && user.isPasswordValid(password)) {

            return user;
        }

        return null;

    }

    public User registerUser(String email, String nickname, String password) {

        User user = new User(nickname, email, password);

        // El usuario no existe, registro válido
        if (!users.containsValue(user)) {

            users.put(user.getEmail(), user);
            return user;
        }

        return null;
    }

}
