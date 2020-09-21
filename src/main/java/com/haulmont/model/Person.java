package com.haulmont.model;

/**
 * The type Person.
 */
public class Person {
    private long id;
    private String name;
    private String patronymic;
    private String surname;

    /**
     * Instantiates a new Person.
     *
     * @param id         the id
     * @param name       the name
     * @param patronymic the patronymic
     * @param surname    the surname
     */
    public Person(long id, String name, String patronymic, String surname) {
        this.id = id;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets patronymic.
     *
     * @return the patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return name + " " + patronymic + " " + surname;
    }

    /**
     * Gets surname np.
     *
     * @return the surname np
     */
    public String getSurnameNP() {
        return surname + " " + name.charAt(0) + ". " + patronymic.charAt(0) + ".";
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets patronymic.
     *
     * @param patronymic the patronymic
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
