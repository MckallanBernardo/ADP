package org.assignment1.repository;

import java.util.ArrayList;
import java.util.Optional;

/* IRepository.java
IRepository interface
Author: Mckallan Bernardo (219018243)
Date: 27 March 2025
*/

public interface IRepository<T, ID> {
    T create(T entity);          // Create or Update
    Optional<T> findById(ID id); // Read
    ArrayList<T> findAll();      // Read all records
    T update(T entity);        // Update
    void delete(ID id);        // Delete
}
