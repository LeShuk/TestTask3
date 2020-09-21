package com.haulmont.view.doctor;


import com.haulmont.controller.DataController;
import com.haulmont.model.Doctor;
import com.haulmont.view.components.MessageBox;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

/**
 * The type Doctor delete dialog.
 */
public class DoctorDeleteDialog extends Window {
    /**
     * Instantiates a new Doctor delete dialog.
     *
     * @param doctor     the doctor
     * @param doctorGrid the doctor grid
     */
    public DoctorDeleteDialog(Doctor doctor, Grid<Doctor> doctorGrid) {
        super("Delete Doctor");
        setWidthUndefined();

        Layout mainLayout = new VerticalLayout();
        Layout buttonsLayout = new HorizontalLayout();

        Label questionLabel = new Label("A you sure to delete this doctor?:");
        Label doctorLabel = new Label(doctor.getFullName());


        Button okButton = new Button("Ok", VaadinIcons.CHECK);
        okButton.addClickListener(clickEvent -> {
            okClick(doctor);
            doctorGrid.setItems(DataController.doctorData.findAll());
            close();
        });

        Button cancelButton = new Button("Cancel", VaadinIcons.EXIT);
        cancelButton.addClickListener(clickEvent -> close());

        buttonsLayout.addComponents(okButton, cancelButton);

        mainLayout.addComponents(questionLabel, doctorLabel, buttonsLayout);
        setContent(mainLayout);

        center();
    }

    private void okClick(Doctor doctor) {
        if (!DataController.doctorData.deleteByID(doctor.getId()))
            UI.getCurrent().addWindow(new MessageBox("Error",
                    "Сan't delete: the doctor has recipes.", "Okay..."));
    }
}
