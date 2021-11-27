package com.moma.fans;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Servidor de Facebook.
 * @author UnaiCL
 * @author AlexNitu
 * @author JonanC
 */
public class Server {

	private static int numClients = 0;

	public static void main(String [] args) {


		if (args.length < 1) {
			System.out.println(" # Uso: FacebookServer [PORT]");
			System.exit(1);
		}

		int serverPort = Integer.parseInt(args[0]);

		try (ServerSocket serverSocket = new ServerSocket(serverPort)) {

			System.out.println("* Servidor activo en '" + serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort() + "'");

			while (true) {
				new FacebookService(serverSocket.accept());
				System.out.println("- Nuevo cliente aceptado. Número de cliente: " + ++numClients);
			}
		}

		catch (IOException e) {
			System.out.println("# Error:" + e.getMessage());
		}


	}
}
