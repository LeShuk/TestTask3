package com.haulmont.model.reports;

/**
 * The type Written recipes.
 */
public class WrittenRecipes {
    private Long doctorId;
    private String caption;
    private Integer numberRecipes;

    /**
     * Instantiates a new Written recipes.
     *
     * @param doctorId      the doctor id
     * @param caption       the caption
     * @param numberRecipes the number recipes
     */
    public WrittenRecipes(Long doctorId, String caption, Integer numberRecipes) {
        this.doctorId = doctorId;
        this.caption = caption;
        this.numberRecipes = numberRecipes;
    }

    /**
     * Gets doctor id.
     *
     * @return the doctor id
     */
    public Long getDoctorId() {
        return doctorId;
    }

    /**
     * Gets caption.
     *
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Gets number recipes.
     *
     * @return the number recipes
     */
    public Integer getNumberRecipes() {
        return numberRecipes;
    }

    /**
     * Sets number recipes.
     *
     * @param numberRecipes the number recipes
     */
    public void setNumberRecipes(Integer numberRecipes) {
        this.numberRecipes = numberRecipes;
    }
}
