package com.haulmont.view.doctor;

import com.haulmont.controller.DataController;
import com.haulmont.model.Doctor;
import com.haulmont.view.reports.NumberWrittenRecipesReport;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

/**
 * The type Doctor view.
 */
public class DoctorView {
    private Layout layout = new VerticalLayout();
    private Grid<Doctor> doctorGrid;
    private Button addNewButton;
    private Button editButton;
    private Button deleteButton;
    private Button statisticButton;
    private Doctor currentDoctor = Doctor.NULL_DOCTOR;

    /**
     * Instantiates a new Doctor view.
     */
    public DoctorView() {
        doctorGrid();
        actionButtons();
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addComponents(addNewButton, editButton, deleteButton, statisticButton);
        layout.addComponents(doctorGrid, buttonLayout);

        doctorGrid.addItemClickListener(itemClick -> {
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
            currentDoctor = itemClick.getItem();
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

    private void doctorGrid() {
        doctorGrid = new Grid<>();

        doctorGrid.setItems(DataController.doctorData.findAll());
        doctorGrid.addColumn(Doctor::getSurname).setCaption("Surname");
        doctorGrid.addColumn(Doctor::getName).setCaption("Name");
        doctorGrid.addColumn(Doctor::getPatronymic).setCaption("Patronymic");
        doctorGrid.addColumn(Doctor::getSpecialization).setCaption("Specialization");

        doctorGrid.setWidthFull();
    }

    private void actionButtons() {
        //Add new doctor Button
        addNewButton = new Button(VaadinIcons.PLUS);
        addNewButton.addClickListener(clickEvent -> {
            DoctorAddOrEditDialog doctorDialog = new DoctorAddOrEditDialog(Doctor.NULL_DOCTOR, doctorGrid);
            UI.getCurrent().addWindow(doctorDialog);
        });

        //Edit doctor Button
        editButton = new Button(VaadinIcons.PENCIL);
        editButton.setEnabled(false);
        editButton.addClickListener(clickEvent -> {
            DoctorAddOrEditDialog doctorDialog = new DoctorAddOrEditDialog(currentDoctor, doctorGrid);
            UI.getCurrent().addWindow(doctorDialog);
        });

        //Delete doctor button
        deleteButton = new Button(VaadinIcons.DEL_A);
        deleteButton.setEnabled(false);
        deleteButton.addClickListener(clickEvent -> {
            DoctorDeleteDialog doctorDeleteDialog = new DoctorDeleteDialog(currentDoctor, doctorGrid);
            UI.getCurrent().addWindow(doctorDeleteDialog);
        });

        //Statistic by all doctors button
        statisticButton = new Button("Statistics", VaadinIcons.LIST);
        statisticButton.addClickListener(clickEvent -> {
            NumberWrittenRecipesReport report = new NumberWrittenRecipesReport();
            UI.getCurrent().addWindow(report);
        });
    }
}
