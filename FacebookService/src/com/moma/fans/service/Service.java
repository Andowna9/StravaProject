package com.moma.fans.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author UnaiCL
 *
 */
public class Service {

	private static Map<String, String> users = new HashMap<>();

	private static int numClients = 0;



	public static void main(String [] args) {
    	users.put("alex@gmail.com", "alex");
    	users.put("julen@gmail.com", "julen");
    	users.put("jonan@gmail.com", "jonan");
    	users.put("capo@gmail.com", "capo");
    	users.put("unai@gmail.com", "unai");

		if (args.length < 1) {
			System.out.println(" # Usage: TranslationServer [PORT]");
			System.exit(1);
		}

		int serverPort = Integer.parseInt(args[0]);

		try (
				ServerSocket tcpServerSocket = new ServerSocket(serverPort);) {
			System.out.println(" - TranslationServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");

			while (true) {
				new FacebookService(tcpServerSocket.accept());
				System.out.println(" - TranslationServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (
				IOException e) {
			System.out.println("# TranslationServer: IO error:" + e.getMessage());
		}


	}
}
