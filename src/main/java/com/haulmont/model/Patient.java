package com.haulmont.model;

/**
 * The type Patient.
 */
public class Patient extends Person {
    private String phone;
    /**
     * The constant NULL_PATIENT.
     */
    public static final Patient NULL_PATIENT =
            new Patient(-1, "Name", "Patronymic", "Surname", "Phone");

    /**
     * Instantiates a new Patient.
     *
     * @param id         the id
     * @param name       the name
     * @param patronymic the patronymic
     * @param surname    the surname
     * @param phone      the phone
     */
    public Patient(long id, String name, String patronymic, String surname, String phone) {
        super(id, name, patronymic, surname);
        this.phone = phone;
    }

    //Getters&Setters==================================================================

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
