package com.moma.fans.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moma.fans.data.domain.TrainingSession;
import com.moma.fans.data.domain.User;

/**
 * Objeto que encapsula la lógica de negocio
 * relacionada con las sesiones de entrenamiento.
 * @author JonanC
 * @author Julen
 */
public class TrainingSessionAppService {
	
	private Map<User, List<TrainingSession> > trainingSessions = new HashMap<>();
	
	public void createTrainingSession(User user, TrainingSession tr) {
		
		if (trainingSessions.get(user)==null) {
			trainingSessions.put(user, new ArrayList<>());
		}
		trainingSessions.get(user).add(tr);
	}
	
	public List getTrainingSessions(User user) {
		
		return trainingSessions.get(user);
	}
}
