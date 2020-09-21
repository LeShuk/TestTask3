package com.haulmont.model;

import java.time.LocalDate;

/**
 * The type Recipe.
 */
public class Recipe {
    private long id;
    private Patient patient;
    private Doctor doctor;
    private String description;
    private LocalDate creationDate;
    private int validityInDays;
    private Priority priority;
    /**
     * The constant NULL_RECIPE.
     */
    public static final Recipe NULL_RECIPE = new Recipe(-1, Patient.NULL_PATIENT, Doctor.NULL_DOCTOR,
            "Description", LocalDate.now(), 1, Priority.NORMAL);

    /**
     * Instantiates a new Recipe.
     *
     * @param id             the id
     * @param patient        the patient
     * @param doctor         the doctor
     * @param description    the description
     * @param creationDate   the creation date
     * @param validityInDays the validity in days
     * @param priority       the priority
     */
    public Recipe(long id, Patient patient, Doctor doctor,
                  String description, LocalDate creationDate, int validityInDays,
                  Priority priority) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.description = description;
        this.creationDate = creationDate;
        this.validityInDays = validityInDays;
        this.priority = priority;
    }


    //Getters==================================================================

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets patient.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Gets doctor.
     *
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Gets validity in days.
     *
     * @return the validity in days
     */
    public int getValidityInDays() {
        return validityInDays;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Gets short description.
     *
     * @return the short description
     */
    public String getShortDescription() {
        return "recipe from " + creationDate + "to " + patient.getSurnameNP() +
                " by " + doctor.getSurnameNP();
    }
//Setters==================================================================

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets patient.
     *
     * @param patient the patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Sets doctor.
     *
     * @param doctor the doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Sets validity in days.
     *
     * @param validityInDays the validity in days
     */
    public void setValidityInDays(int validityInDays) {
        this.validityInDays = validityInDays;
    }

    /**
     * Sets priority.
     *
     * @param priority the priority
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
