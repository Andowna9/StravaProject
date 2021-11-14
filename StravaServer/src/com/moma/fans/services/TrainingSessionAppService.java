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
	
	public void createTrainingSession(User user, TrainingSession tr) {

		user.addTrainingSession(tr);
	}
	
	public List<TrainingSession> getTrainingSessions(User user) {
		
		return user.getTrainingSessions();
	}
}
