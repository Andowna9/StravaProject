package com.moma.fans.data.dao;

public interface IDataAccessObject<DomainObject> {

    public void save(DomainObject object);
    public void  delete(DomainObject object);
}
