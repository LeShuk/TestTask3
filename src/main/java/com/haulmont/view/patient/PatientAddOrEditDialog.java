package com.haulmont.view.patient;

import com.haulmont.controller.DataController;
import com.haulmont.model.Patient;
import com.haulmont.view.components.MessageBox;
import com.haulmont.view.components.TextFieldWithMinBinder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

/**
 * The type Patient add or edit dialog.
 */
public class PatientAddOrEditDialog extends Window {
    private TextFieldWithMinBinder<Patient> nameTextField;
    private TextFieldWithMinBinder<Patient> patronymicTextField;
    private TextFieldWithMinBinder<Patient> surnameTextField;
    private TextFieldWithMinBinder<Patient> phoneTextField;

    /**
     * Instantiates a new Patient add or edit dialog.
     *
     * @param patient     the patient
     * @param patientGrid the patient grid
     */
    public PatientAddOrEditDialog(Patient patient, Grid<Patient> patientGrid) {

        super((Patient.NULL_PATIENT.equals(patient)) ? "Add patient" : "Edit patient");
        setWidth("300px");

        Layout mainLayout = new VerticalLayout();
        FormLayout formLayout = new FormLayout();
        Layout buttonsLayout = new HorizontalLayout();
        Label errorLabel = new Label("");

        nameTextField = new TextFieldWithMinBinder<>(Patient.NULL_PATIENT.getName(),
                (Patient.NULL_PATIENT.equals(patient)) ? "" : patient.getName(), 3,
                "3 characters minimum!", errorLabel,
                Patient::getName, Patient::setName);

        patronymicTextField = new TextFieldWithMinBinder<>(Patient.NULL_PATIENT.getPatronymic(),
                (Patient.NULL_PATIENT.equals(patient)) ? "" : patient.getPatronymic(), 3,
                "3 characters minimum!", errorLabel,
                Patient::getPatronymic, Patient::setPatronymic);

        surnameTextField = new TextFieldWithMinBinder<>(Patient.NULL_PATIENT.getSurname(),
                (Patient.NULL_PATIENT.equals(patient)) ? "" : patient.getSurname(), 3,
                "3 characters minimum!", errorLabel,
                Patient::getSurname, Patient::setSurname);

        phoneTextField = new TextFieldWithMinBinder<>(Patient.NULL_PATIENT.getPhone(),
                (Patient.NULL_PATIENT.equals(patient)) ? "" : patient.getPhone(), 3,
                "3 characters minimum!", errorLabel,
                Patient::getPhone, Patient::setPhone);

        formLayout.addComponents(surnameTextField.getLayout(),
                nameTextField.getLayout(),
                patronymicTextField.getLayout(),
                phoneTextField.getLayout());

        Button okButton = new Button("Ok", VaadinIcons.CHECK);
        okButton.addClickListener(clickEvent -> {
            okClick(patient);
            patientGrid.setItems(DataController.patientData.findAll());
            close();
        });

        Button cancelButton = new Button("Cancel", VaadinIcons.EXIT);
        cancelButton.addClickListener(clickEvent -> close());

        buttonsLayout.addComponents(okButton, cancelButton);

        mainLayout.addComponents(formLayout, errorLabel, buttonsLayout);

        setContent(mainLayout);

        center();
    }

    private void okClick(Patient patient) {
        if (nameTextField.getValue().isEmpty() || nameTextField.isError() ||
                patronymicTextField.getValue().isEmpty() || patronymicTextField.isError() ||
                surnameTextField.getValue().isEmpty() || surnameTextField.isError() ||
                phoneTextField.getValue().isEmpty() || phoneTextField.isError()) {
            UI.getCurrent().addWindow(new MessageBox("Error",
                    "The field cannot be empty or contains an error",
                    "Correct it!"));
        } else {
            Patient currentPatient = new Patient(patient.getId(), nameTextField.getValue(),
                    patronymicTextField.getValue(), surnameTextField.getValue(),
                    phoneTextField.getValue());
            if (patient.getId() == -1) DataController.patientData.create(currentPatient);
            else DataController.patientData.update(currentPatient);
        }
    }
}
