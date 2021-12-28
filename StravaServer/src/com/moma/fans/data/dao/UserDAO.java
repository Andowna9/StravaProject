package com.moma.fans.data.dao;

import com.moma.fans.data.domain.User;

import java.util.List;

public class UserDAO extends DataAccessObjectBase implements IDataAccessObject<User> {
    @Override
    public void save(User object) {

        super.saveObject(object);
    }

    @Override
    public void delete(User object) {

        super.deleteObject(object);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User find(String param) {
        return null;
    }
}
