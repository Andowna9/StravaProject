package com.moma.fans.data.dao;

/**
 * Interfaz con métodos comunes a las clases DAO que permiten persistir
 * objetos de un tipo de terminado.
 * @author JonanC
 * @param <DomainObject> Objeto de dominio que implementa el acceso a la BD
 */
public interface IDataAccessObject<DomainObject> {

    /**
     * Guarda un objeto de dominio en la BD.
     * @param object objeto de dominio
     */
    public void save(DomainObject object);

    /**
     * Elimina un objeto de dominio de la BD.
     * @param object objeto de dominio
     */
    public void  delete(DomainObject object);
}
