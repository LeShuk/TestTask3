package com.haulmont.controller;

import com.haulmont.controller.dao.DoctorDao;
import com.haulmont.controller.dao.PatientDao;
import com.haulmont.controller.dao.RecipeDao;
import com.haulmont.controller.jdbc.DoctorDataJdbcImpl;
import com.haulmont.controller.jdbc.PatientDataJdbcImpl;
import com.haulmont.controller.jdbc.RecipeDataJDBCImpl;

/**
 * The type Data controller.
 */
public class DataController {
    /**
     * The constant doctorData.
     */
    public static DoctorDao doctorData = new DoctorDataJdbcImpl();
    /**
     * The constant patientData.
     */
    public static PatientDao patientData = new PatientDataJdbcImpl();
    /**
     * The constant recipeData.
     */
    public static RecipeDao recipeData = new RecipeDataJDBCImpl();
}
