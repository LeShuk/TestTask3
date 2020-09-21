package com.haulmont.view.components;

import com.haulmont.model.Recipe;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;

/**
 * The type Text field positive integer.
 */
public class TextFieldPositiveInteger {
    private Layout layout;
    private Label errorLabel;
    private TextField textField;
    private String errorMessage;
    private Binder<Recipe> binder;
    private boolean error;


    /**
     * Instantiates a new Text field positive integer.
     *
     * @param placeHolder       the place holder
     * @param value             the value
     * @param errorMessageLabel the error message label
     */
    public TextFieldPositiveInteger(String placeHolder, String value, Label errorMessageLabel) {
        layout = new HorizontalLayout();
        layout.setWidthUndefined();

        this.errorMessage = "errorMessage";

        errorMessageLabel.setVisible(false);
        errorMessageLabel.setCaption(errorMessage);

        textField = new TextField();
        textField.setWidth("75%");
        textField.setPlaceholder(placeHolder);
        textField.setValue(value);

        errorLabel = new Label();
        errorLabel.setIcon(VaadinIcons.EXCLAMATION);
        errorLabel.setWidth("25%");
        errorLabel.setVisible(false);

        binder = new Binder<>();
        binder.forField(textField)
                .withValidator((Validator<String>) (stringValue, context) -> {
                    int intValue = 0;
                    try {
                        intValue = Integer.parseInt(stringValue);
                    } catch (Exception e) {
                        errorMessage = "Must be integer!";
                        return ValidationResult.error("Must be integer!");
                    }
                    if (intValue <= 0) {
                        errorMessage = "Must be more then 0";
                        return ValidationResult.error("Must be more then 0");
                    }
                    return ValidationResult.ok();
                })
                .withValidationStatusHandler(status -> {
                    error = status.isError();
                    errorLabel.setVisible(error);
                    errorMessageLabel.setCaption(errorMessage);
                    errorMessageLabel.setVisible(error);
                }).bind(Recipe::getDescription, Recipe::setDescription); //todo: дичь, непонятно как работающая

        layout.addComponents(textField, errorLabel);
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
        return textField.getValue();
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
