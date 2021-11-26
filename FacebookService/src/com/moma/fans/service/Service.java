package com.moma.fans.service;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author UnaiCL
 *
 */
public class Service {

	private static Map<String, String> users = new HashMap<>();
	
    public static void main(String [] args) {
    	users.put("alex@gmail.com", "alex");
    	users.put("julen@gmail.com", "julen");
    	users.put("jonan@gmail.com", "jonan");
    	users.put("capo@gmail.com", "capo");
    	users.put("unai@gmail.com", "unai");
    }
}
