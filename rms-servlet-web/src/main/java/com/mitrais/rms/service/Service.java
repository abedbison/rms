package com.mitrais.rms.service;

import java.util.List;
import java.util.Optional;

public interface Service<T, ID> {
    /**
     * Find single record by id.
     * @param id id
     * @return record wrapped by {@link Optional}
     */
    Optional<T> find(ID id);

    /**
     * Find all records
     * @return list of records
     */
    List<T> findAll();

    /**
     * Persist record to database
     * @param o record
     * @return message of the operation
     */
    String save(T o);

    /**
     * Persist existing record to database
     * @param o record
     * @return message of the operation
     */
    String update(T o);

    /**
     * Remove existing record from database
     * @param o record
     * @return message of the operation
     */
    String delete(T o);
    

}
