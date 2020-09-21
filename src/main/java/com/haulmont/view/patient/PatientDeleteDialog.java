package com.haulmont.view.patient;


import com.haulmont.controller.DataController;
import com.haulmont.model.Patient;
import com.haulmont.view.components.MessageBox;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

/**
 * The type Patient delete dialog.
 */
public class PatientDeleteDialog extends Window {
    /**
     * Instantiates a new Patient delete dialog.
     *
     * @param patient     the patient
     * @param patientGrid the patient grid
     */
    public PatientDeleteDialog(Patient patient, Grid<Patient> patientGrid) {
        super("Delete Patient");
        setWidthUndefined();

        Layout mainLayout = new VerticalLayout();
        Layout buttonsLayout = new HorizontalLayout();

        Label questionLabel = new Label("A you sure to delete this patient?:");
        Label patientLabel = new Label(patient.getFullName());


        Button okButton = new Button("Ok", VaadinIcons.CHECK);
        okButton.addClickListener(clickEvent -> {
            okClick(patient);
            patientGrid.setItems(DataController.patientData.findAll());
            close();
        });

        Button cancelButton = new Button("Cancel", VaadinIcons.EXIT);
        cancelButton.addClickListener(clickEvent -> close());

        buttonsLayout.addComponents(okButton, cancelButton);

        mainLayout.addComponents(questionLabel, patientLabel, buttonsLayout);
        setContent(mainLayout);

        center();
    }

    private void okClick(Patient patient) {
        if (!DataController.patientData.deleteByID(patient.getId()))
            UI.getCurrent().addWindow(new MessageBox("Error",
                    "Сan't delete: the patient has recipes.", "Okay..."));
    }
}
