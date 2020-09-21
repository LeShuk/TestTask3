package com.haulmont.view.components;

import com.haulmont.model.Recipe;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;

/**
 * The type Text area with min binder.
 */
public class TextAreaWithMinBinder {
    private Layout layout;
    private Label errorLabel;
    private TextArea textArea;
    private String errorMessage;
    private Binder<Recipe> binder;
    private boolean error;

    /**
     * Instantiates a new Text area with min binder.
     *
     * @param placeHolder       the place holder
     * @param value             the value
     * @param minChars          the min chars
     * @param errorMessage      the error message
     * @param errorMessageLabel the error message label
     */
    public TextAreaWithMinBinder(String placeHolder, String value, int minChars,
                                 String errorMessage, Label errorMessageLabel) {
        layout = new HorizontalLayout();
        layout.setWidthUndefined();

        errorMessageLabel.setVisible(false);
        errorMessageLabel.setCaption(errorMessage);

        textArea = new TextArea();
        textArea.setWidth("75%");
        textArea.setWordWrap(true);
        textArea.setPlaceholder(placeHolder);
        textArea.setValue(value);

        errorLabel = new Label();
        errorLabel.setIcon(VaadinIcons.EXCLAMATION);
        errorLabel.setWidth("25%");
        errorLabel.setVisible(false);

        this.errorMessage = errorMessage;

        binder = new Binder<>();
        binder.forField(textArea)
                .withValidator(min -> min.length() >= minChars, errorMessage)
                .withValidationStatusHandler(status -> {
                    error = status.isError();
                    errorLabel.setVisible(error);
                    errorMessageLabel.setCaption(status.getMessage().orElse(""));
                    errorMessageLabel.setVisible(error);
                })
                .bind(Recipe::getDescription, Recipe::setDescription);

        layout.addComponents(textArea, errorLabel);
    }

    /**
     * Gets layout.
     *
     * @return the layout
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return textArea.getValue();
    }

    /**
     * Is error boolean.
     *
     * @return the boolean
     */
    public boolean isError() {
        return error;
    }
}
