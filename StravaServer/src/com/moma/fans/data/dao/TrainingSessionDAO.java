package com.moma.fans.data.dao;

/**
 * Patrón DAO para objetos de tipo sesión de entrenamiento.
 * @author JonanC
 */
public class TrainingSessionDAO extends DataAccessObjectBase implements IDataAccessObject<TrainingSessionDAO> {

    private TrainingSessionDAO() {}

    // Singleton on demand
    private static class InstanceHolder {
        private static final TrainingSessionDAO INSTANCE = new TrainingSessionDAO();
    }

    public static TrainingSessionDAO getInstance() {
        return TrainingSessionDAO.InstanceHolder.INSTANCE;
    }

    @Override
    public void save(TrainingSessionDAO object) {

        super.saveObject(object);
    }

    @Override
    public void delete(TrainingSessionDAO object) {

        super.deleteObject(object);
    }
}
