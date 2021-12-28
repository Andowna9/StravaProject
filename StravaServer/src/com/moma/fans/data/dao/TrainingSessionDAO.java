package com.moma.fans.data.dao;

import java.util.List;

public class TrainingSessionDAO extends DataAccessObjectBase implements IDataAccessObject<TrainingSessionDAO> {

    @Override
    public void save(TrainingSessionDAO object) {

        super.saveObject(object);
    }

    @Override
    public void delete(TrainingSessionDAO object) {

        super.deleteObject(object);
    }

    @Override
    public List<TrainingSessionDAO> getAll() {
        return null;
    }

    @Override
    public TrainingSessionDAO find(String param) {
        return null;
    }
}
