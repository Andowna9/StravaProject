package com.moma.fans.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class FacebookService extends Thread{
    private DataInputStream in;
    private DataOutputStream out;
    private Socket tcpSocket;

    private static String DELIMITER = ",";


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
            String data = this.in.readUTF();
            System.out.println("   - FacebookService - Datos recibidos de '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
            this.out.writeUTF(data);
            System.out.println("   - FacebookService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
        } catch (Exception e) {
            System.out.println("   # FacebookService - TCPConnection error" + e.getMessage());
        } finally {
            try {
                tcpSocket.close();
            } catch (Exception e) {
                System.out.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
            }
        }
    }


}
