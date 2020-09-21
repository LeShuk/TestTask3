package com.haulmont.view.patient;

import com.haulmont.controller.DataController;
import com.haulmont.model.Patient;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

/**
 * The type Patient view.
 */
public class PatientView {
    private Layout layout = new VerticalLayout();
    private Grid<Patient> patientGrid;
    private Button addNewButton;
    private Button editButton;
    private Button deleteButton;
    private Patient currentPatient = Patient.NULL_PATIENT;

    /**
     * Instantiates a new Patient view.
     */
    public PatientView() {
        patientGrid();
        actionButtons();
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addComponents(addNewButton, editButton, deleteButton);
        layout.addComponents(patientGrid, buttonLayout);

        patientGrid.addItemClickListener(itemClick -> {
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
            currentPatient = itemClick.getItem();
        });

    }

    /**
     * Gets layout.
     *
     * @return the layout
     */
    public Layout getLayout() {
        return layout;
    }

    private void patientGrid() {
        patientGrid = new Grid<>();

        patientGrid.setItems(DataController.patientData.findAll());
        patientGrid.addColumn(Patient::getSurname).setCaption("Surname");
        patientGrid.addColumn(Patient::getName).setCaption("Name");
        patientGrid.addColumn(Patient::getPatronymic).setCaption("Patronymic");
        patientGrid.addColumn(Patient::getPhone).setCaption("Phone");

        patientGrid.setWidthFull();
    }

    private void actionButtons() {
        //Add new patient Button
        addNewButton = new Button(VaadinIcons.PLUS);
        addNewButton.addClickListener(clickEvent -> {
            PatientAddOrEditDialog patientDialog = new PatientAddOrEditDialog(Patient.NULL_PATIENT, patientGrid);
            UI.getCurrent().addWindow(patientDialog);
        });

        //Edit patient Button
        editButton = new Button(VaadinIcons.PENCIL);
        editButton.setEnabled(false);
        editButton.addClickListener(clickEvent -> {
            PatientAddOrEditDialog patientDialog = new PatientAddOrEditDialog(currentPatient, patientGrid);
            UI.getCurrent().addWindow(patientDialog);
        });

        //Delete patient button
        deleteButton = new Button(VaadinIcons.DEL_A);
        deleteButton.setEnabled(false);
        deleteButton.addClickListener(clickEvent -> {
            PatientDeleteDialog patientDeleteDialog = new PatientDeleteDialog(currentPatient, patientGrid);
            UI.getCurrent().addWindow(patientDeleteDialog);
        });
    }
}
