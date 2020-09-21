package com.haulmont.model;

/**
 * The enum Priority.
 */
public enum Priority {
    /**
     * Normal priority.
     */
    NORMAL("NORMAL"),
    /**
     * Cito priority.
     */
    CITO("CITO"),
    /**
     * Statim priority.
     */
    STATIM("STATIM");

    private String caption;

    Priority(String caption) {
        this.caption = caption;
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
     * Gets priority by id.
     *
     * @param id the id
     * @return the priority by id
     */
    public static Priority getPriorityByID(int id) {
        switch (id) {
            case 0:
                return NORMAL;
            case 1:
                return CITO;
            case 2:
                return STATIM;
        }
        return NORMAL;
    }
}
