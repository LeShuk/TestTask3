package com.haulmont.view.components;

import com.haulmont.model.Person;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Setter;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;

/**
 * The type Text field with min binder.
 *
 * @param <T> the type parameter
 */
public class TextFieldWithMinBinder<T extends Person> {
    private Layout layout;
    private Label errorLabel;
    private TextField textField;
    private String errorMessage;
    private Binder<T> binder;
    private boolean error;


    /**
     * Instantiates a new Text field with min binder.
     *
     * @param placeHolder       the place holder
     * @param value             the value
     * @param minChars          the min chars
     * @param errorMessage      the error message
     * @param errorMessageLabel the error message label
     * @param getter            the getter
     * @param setter            the setter
     */
    public TextFieldWithMinBinder(String placeHolder, String value, int minChars,
                                  String errorMessage, Label errorMessageLabel,
                                  ValueProvider<T, String> getter, Setter<T, String> setter) {
        layout = new HorizontalLayout();
        layout.setWidthUndefined();

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

        this.errorMessage = errorMessage;

        binder = new Binder<>();
        binder.forField(textField)
                .withValidator(min -> min.length() >= minChars, errorMessage)
                .withValidationStatusHandler(status -> {
                    error = status.isError();
                    errorLabel.setVisible(error);
                    errorMessageLabel.setCaption(status.getMessage().orElse(""));
                    errorMessageLabel.setVisible(error);
                })
                .bind(getter, setter);

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
