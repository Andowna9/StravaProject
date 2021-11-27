package com.moma.fans;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Hilo que atiende a cada cliente
 * que se conecta.
 * @author AlexNitu
 * @author JonanC
 */
public class FacebookService extends Thread {

    // Mapa estático de usuarios
    // Sin problemas de concurrencia al usar varios hilos, ya que solo es de lectura
    private static final Map<String, String> users = Collections.unmodifiableMap(new HashMap<>() {{

        put("alex@gmail.com", "alex");
        put("julen@gmail.com", "julen");
        put("jonan@gmail.com", "jonan");
        put("capo@gmail.com", "capo");
        put("unai@gmail.com", "unai");

    }});

    private DataInputStream in;
    private DataOutputStream out;
    private Socket tcpSocket;

    public FacebookService(Socket socket){

        try {

            this.tcpSocket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.start();

        } catch (Exception e) {
            System.err.println("# FacebookService - TCPConnection IO error:" + e.getMessage());
        }
    }

    public void run() {

        try {

            String data = in.readUTF();
            System.out.println("- FacebookService - Datos recibidos de '" +
                    tcpSocket.getInetAddress().getHostAddress() + ":"
                    + tcpSocket.getPort() + "' -> '" + data + "'");

            String [] parts = data.split(",");

            String email = parts[0];
            String password = parts[1];

            out.writeBoolean(users.containsKey(email) && users.get(email).equals(password));

            System.out.println("# FacebookService - Enviando respuesta a '" +
                    tcpSocket.getInetAddress().getHostAddress() + ":" +
                    tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");

        } catch (Exception e) {
            System.out.println("# FacebookService - TCPConnection error" + e.getMessage());

        } finally {
            try {
                tcpSocket.close();
            } catch (IOException e){
                System.out.println(" # FacebookService - TCPConnection IO error:" + e.getMessage());
            }
        }
    }


}
