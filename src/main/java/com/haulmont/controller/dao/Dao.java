package com.haulmont.controller.dao;

import java.util.List;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 * @param <K> the key type parameter
 */
public interface Dao<T, K> {
    /**
     * Create object in db. Return true if ok.
     *
     * @param model the model
     * @return the boolean
     */
    boolean create(T model);

    /**
     * Update object in db. Return true if ok.
     *
     * @param model the model
     * @return the boolean
     */
    boolean update(T model);

    /**
     * Delete by id object in db. Return true if ok.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteByID(K id);

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    T findByID(K id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();
}
