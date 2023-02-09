package com.flipkart.dao;

import java.util.List;
import java.util.Optional;

/**
 * DaoInterface
 * @param <T>
 */
public interface DaoInterface<T> {
    /**
     * Get object from id
     * @param id
     * @return object
     */
    T get(String id);

    /**
     * Get list of all object
     * @return list of object
     */
    List<T> getAll();

    /**
     * Insert object
     * @param t
     * @return list of object
     */
    int insert(T t);

    /**
     * Update id in the object
     * @param id
     * @param t
     * @return status
     */
    int update(String id, T t);

    /**
     * Delete a particular object
     * @param t
     * @return status
     */
    int delete(T t);

}
