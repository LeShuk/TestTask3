package com.haulmont.view.doctor;

import com.haulmont.controller.DataController;
import com.haulmont.model.Doctor;
import com.haulmont.view.components.MessageBox;
import com.haulmont.view.components.TextFieldWithMinBinder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;


/**
 * The type Doctor add or edit dialog.
 */
public class DoctorAddOrEditDialog extends Window {
    private TextFieldWithMinBinder<Doctor> nameTextField;
    private TextFieldWithMinBinder<Doctor> patronymicTextField;
    private TextFieldWithMinBinder<Doctor> surnameTextField;
    private TextFieldWithMinBinder<Doctor> specialisationTextField;

    /**
     * Instantiates a new Doctor add or edit dialog.
     *
     * @param doctor     the doctor
     * @param doctorGrid the doctor grid
     */
    public DoctorAddOrEditDialog(Doctor doctor, Grid<Doctor> doctorGrid) {

        super((Doctor.NULL_DOCTOR.equals(doctor)) ? "Add doctor" : "Edit doctor");
        setWidth("300px");

        Layout mainLayout = new VerticalLayout();
        FormLayout formLayout = new FormLayout();
        Layout buttonsLayout = new HorizontalLayout();
        Label errorLabel = new Label("");

        nameTextField = new TextFieldWithMinBinder<>(Doctor.NULL_DOCTOR.getName(),
                (Doctor.NULL_DOCTOR.equals(doctor)) ? "" : doctor.getName(), 3,
                "3 characters minimum!", errorLabel,
                Doctor::getName, Doctor::setName);

        patronymicTextField = new TextFieldWithMinBinder<>(Doctor.NULL_DOCTOR.getPatronymic(),
                (Doctor.NULL_DOCTOR.equals(doctor)) ? "" : doctor.getPatronymic(), 3,
                "3 characters minimum!", errorLabel,
                Doctor::getPatronymic, Doctor::setPatronymic);

        surnameTextField = new TextFieldWithMinBinder<>(Doctor.NULL_DOCTOR.getSurname(),
                (Doctor.NULL_DOCTOR.equals(doctor)) ? "" : doctor.getSurname(), 3,
                "3 characters minimum!", errorLabel,
                Doctor::getSurname, Doctor::setSurname);

        specialisationTextField = new TextFieldWithMinBinder<>(Doctor.NULL_DOCTOR.getSpecialization(),
                (Doctor.NULL_DOCTOR.equals(doctor)) ? "" : doctor.getSpecialization(), 3,
                "3 characters minimum!", errorLabel,
                Doctor::getSpecialization, Doctor::setSpecialization);

        formLayout.addComponents(surnameTextField.getLayout(),
                nameTextField.getLayout(),
                patronymicTextField.getLayout(),
                specialisationTextField.getLayout());

        Button okButton = new Button("Ok", VaadinIcons.CHECK);
        okButton.addClickListener(clickEvent -> {
            okClick(doctor);
            doctorGrid.setItems(DataController.doctorData.findAll());
        });

        Button cancelButton = new Button("Cancel", VaadinIcons.EXIT);
        cancelButton.addClickListener(clickEvent -> close());

        buttonsLayout.addComponents(okButton, cancelButton);

        mainLayout.addComponents(formLayout, errorLabel, buttonsLayout);

        setContent(mainLayout);

        center();
    }

    private void okClick(Doctor doctor) {
        if (nameTextField.getValue().isEmpty() || nameTextField.isError() ||
                patronymicTextField.getValue().isEmpty() || patronymicTextField.isError() ||
                surnameTextField.getValue().isEmpty() || surnameTextField.isError() ||
                specialisationTextField.getValue().isEmpty() || specialisationTextField.isError()) {
            UI.getCurrent().addWindow(new MessageBox("Error",
                    "The field cannot be empty or contains an error",
                    "Correct it!"));
        } else {
            Doctor currentDoctor = new Doctor(doctor.getId(), nameTextField.getValue(),
                    patronymicTextField.getValue(), surnameTextField.getValue(),
                    specialisationTextField.getValue());
            if (doctor.getId() == -1) DataController.doctorData.create(currentDoctor);
            else DataController.doctorData.update(currentDoctor);
            close();
        }
    }
}
