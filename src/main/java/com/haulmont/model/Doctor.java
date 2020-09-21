package com.haulmont.model;

/**
 * The type Doctor.
 */
public class Doctor extends Person {
    private String specialization;
    /**
     * The constant NULL_DOCTOR.
     */
    public static final Doctor NULL_DOCTOR =
            new Doctor(-1, "Name", "Patronymic", "Surname", "Specialisation");

    /**
     * Instantiates a new Doctor.
     *
     * @param id             the id
     * @param name           the name
     * @param patronymic     the patronymic
     * @param surname        the surname
     * @param specialization the specialization
     */
    public Doctor(long id, String name, String patronymic, String surname, String specialization) {
        super(id, name, patronymic, surname);
        this.specialization = specialization;
    }

    //Getters&Setters==================================================================

    /**
     * Gets specialization.
     *
     * @return the specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets specialization.
     *
     * @param specialization the specialization
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
