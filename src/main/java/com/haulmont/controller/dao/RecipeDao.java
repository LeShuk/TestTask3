package com.haulmont.controller.dao;

import com.haulmont.model.Patient;
import com.haulmont.model.Priority;
import com.haulmont.model.Recipe;

import java.util.List;

/**
 * The interface Recipe dao.
 */
public interface RecipeDao extends Dao<Recipe, Long> {
    /**
     * Find all with filters.
     *
     * @param description the description
     * @param patient     the patient
     * @param priority    the priority
     * @return the list
     */
    List<Recipe> findAll(String description, Patient patient, Priority priority);
}
