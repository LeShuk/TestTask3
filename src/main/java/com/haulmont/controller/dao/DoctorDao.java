package com.haulmont.controller.dao;

import com.haulmont.model.Doctor;
import com.haulmont.model.reports.WrittenRecipes;

import java.util.List;

/**
 * The interface Doctor dao.
 */
public interface DoctorDao extends Dao<Doctor, Long> {
    /**
     * Number written recipes list.
     *
     * @return the list
     */
    List<WrittenRecipes> numberWrittenRecipes();
}
