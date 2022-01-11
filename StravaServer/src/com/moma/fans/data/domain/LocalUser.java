package com.moma.fans.data.domain;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que representa un usuario local, es decir,
 * aquel que se ha registrado con contraseña y
 * no con un servicio externo.
 * @see User
 * @author JonanC
 */

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class LocalUser extends User {

    private String password;

    public LocalUser(String email, String nickname, String password) {
        super(RegisterType.LOCAL, email, nickname);
        this.password = encrypt(password);
    }

    /**
     * Encripta una contraseña.
     * @param password contraseña en texto plano
     * @return contraseña encriptada
     */
    private String encrypt(String password) {

        String generatedPassword = null;

        // Lógica de encriptación
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte [] bytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();

            // Formato hexadecimal
            for(int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;

    }

    /**
     * Permite validar el acceso a una cuenta de usuario.
     * @param password contraseña introducida por el usuario
     * @return true si las contraseñas coinciden, false en caso contrario
     */
    public boolean isPasswordValid(String password) {

        return this.encrypt(password).equals(this.password);
    }

    public String getPassword() {

        return password;
    }

}
