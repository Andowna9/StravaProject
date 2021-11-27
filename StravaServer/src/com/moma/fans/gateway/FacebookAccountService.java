package com.moma.fans.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Servicio de cuentas de Facebook.
 * Implementa el patrón de diseño Gateway.
 * @author JonanC
 */
public class FacebookAccountService implements AccountServiceGateway {

    @Override
    public boolean authenticate(String email, String password) {

        try(Socket clientSocket = new Socket("localhost", 3069)) {

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());

            out.writeUTF(email + "," + password);
            return in.readBoolean();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }
}
