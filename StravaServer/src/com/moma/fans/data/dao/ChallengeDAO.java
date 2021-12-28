package com.moma.fans.data.dao;

import com.moma.fans.data.domain.Challenge;

import java.util.List;

public class ChallengeDAO extends DataAccessObjectBase implements IDataAccessObject<Challenge> {
    @Override
    public void save(Challenge object) {

        super.saveObject(object);
    }

    @Override
    public void delete(Challenge object) {

        super.deleteObject(object);
    }

    @Override
    public List<Challenge> getAll() {
        return null;
    }

    @Override
    public Challenge find(String param) {
        return null;
    }
}
