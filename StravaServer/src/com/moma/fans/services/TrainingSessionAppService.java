package com.moma.fans.services;

import java.util.List;

import com.moma.fans.data.dao.UserDAO;
import com.moma.fans.data.domain.TrainingSession;
import com.moma.fans.data.domain.User;

/**
 * Objeto que encapsula la lógica de negocio
 * relacionada con las sesiones de entrenamiento.
 * @author JonanC
 * @author Julen
 */
public class TrainingSessionAppService {

	private static final TrainingSessionAppService INSTANCE = new TrainingSessionAppService();

	private TrainingSessionAppService() { }

	// Eager initialization
	public static TrainingSessionAppService getInstance() {

		return INSTANCE;
	}
	
	public void createTrainingSession(User user, TrainingSession tr) {

		user.addTrainingSession(tr);
		UserDAO.getInstance().save(user);
	}
	
	public List<TrainingSession> getTrainingSessions(User user) {

		return user.getTrainingSessions();
	}
}
