package com.haulmont.view.recipe;

import com.haulmont.controller.DataController;
import com.haulmont.model.Doctor;
import com.haulmont.model.Patient;
import com.haulmont.model.Priority;
import com.haulmont.model.Recipe;
import com.haulmont.view.components.MessageBox;
import com.haulmont.view.components.TextAreaWithMinBinder;
import com.haulmont.view.components.TextFieldPositiveInteger;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Recipe add or edit dialog.
 */
public class RecipeAddOrEditDialog extends Window {
    /**
     * The Description text area.
     */
    TextAreaWithMinBinder descriptionTextArea;
    /**
     * The Select doctor.
     */
    NativeSelect<Doctor> selectDoctor;
    /**
     * The Select patient.
     */
    NativeSelect<Patient> selectPatient;
    /**
     * The Creation date field.
     */
    DateField creationDateField;
    /**
     * The Validity in days field.
     */
    TextFieldPositiveInteger validityInDaysField;
    /**
     * The Select priority.
     */
    NativeSelect<Priority> selectPriority;
    /**
     * The Doctor error.
     */
    boolean doctorError;
    /**
     * The Patient error.
     */
    boolean patientError;
    /**
     * The Creation date error.
     */
    boolean creationDateError;

    /**
     * Instantiates a new Recipe add or edit dialog.
     *
     * @param recipe     the recipe
     * @param recipeGrid the recipe grid
     */
    public RecipeAddOrEditDialog(Recipe recipe, Grid<Recipe> recipeGrid) {

        super((Recipe.NULL_RECIPE.equals(recipe)) ? "Add recipe" : "Edit recipe");
        setWidth("500px");

        Layout mainLayout = new VerticalLayout();
        FormLayout formLayout = new FormLayout();
        Layout buttonsLayout = new HorizontalLayout();
        Label errorLabel = new Label("");
        Label infoLabel = new Label();

        descriptionTextArea = new TextAreaWithMinBinder(Recipe.NULL_RECIPE.getDescription(),
                recipe.getDescription(), 10, "10 characters minimum!", errorLabel);

        selectDoctor = new NativeSelect<>("Doctor");
        List<Doctor> selectDoctorList = DataController.doctorData.findAll();
        selectDoctor.setItems(selectDoctorList);
        selectDoctor.setEmptySelectionAllowed(false);
        selectDoctor.setItemCaptionGenerator(item -> item.getSurnameNP() + ", " + item.getSpecialization());
        for (Doctor instance : selectDoctorList) {
            if (instance.getId() == recipe.getDoctor().getId()) {
                selectDoctor.setSelectedItem(instance);
                break;
            }
        }
        doctorError = true;
        selectDoctor.addValueChangeListener(event -> doctorError = false);

        selectPatient = new NativeSelect<>("Patient");
        selectPatient.setEmptySelectionAllowed(false);
        List<Patient> selectPatientList = DataController.patientData.findAll();
        selectPatient.setItems(selectPatientList);
        selectPatient.setItemCaptionGenerator(item -> item.getSurnameNP() + ", " + item.getPhone());
        for (Patient instance : selectPatientList) {
            if (instance.getId() == recipe.getPatient().getId()) {
                selectPatient.setSelectedItem(instance);
                break;
            }
        }
        patientError = true;
        selectPatient.addValueChangeListener(event -> patientError = false);

        creationDateField = new DateField("CreationDate");
        creationDateField.setPlaceholder("Creation date");
        creationDateField.setDateFormat("dd-MM-yyyy");
        creationDateField.setValue(recipe.getCreationDate());
        creationDateField.setRangeStart(recipe.getCreationDate());
        creationDateField.setRangeEnd(LocalDate.now().plusDays(365));
        creationDateError = true;
        creationDateField.addValueChangeListener(event -> creationDateError = false);

        validityInDaysField = new TextFieldPositiveInteger("Validity in days",
                Integer.toString(recipe.getValidityInDays()), errorLabel);

        selectPriority = new NativeSelect<>("Priority");
        selectPriority.setEmptySelectionAllowed(false);
        selectPriority.setItems(Priority.NORMAL, Priority.CITO, Priority.STATIM);
        selectPriority.setSelectedItem(recipe.getPriority());


        formLayout.addComponents(descriptionTextArea.getLayout(),
                selectDoctor, selectPatient, creationDateField,
                validityInDaysField.getLayout(), selectPriority, errorLabel, infoLabel);

        Button okButton = new Button("Ok", VaadinIcons.CHECK);
        okButton.addClickListener(clickEvent -> {
            okClick(recipe);
            recipeGrid.setItems(DataController.recipeData.findAll());
        });

        Button cancelButton = new Button("Cancel", VaadinIcons.EXIT);
        cancelButton.addClickListener(clickEvent -> close());

        buttonsLayout.addComponents(okButton, cancelButton);

        mainLayout.addComponents(formLayout, errorLabel, buttonsLayout);

        setContent(mainLayout);

        center();
    }

    private void okClick(Recipe recipe) {
        if ((descriptionTextArea.getValue().isEmpty()) || (descriptionTextArea.isError()) ||
                doctorError || patientError || creationDateError ||
                (validityInDaysField.getValue().isEmpty()) || (validityInDaysField.isError())) {
            UI.getCurrent().addWindow(new MessageBox("Error",
                    "The field cannot be empty or contains an error",
                    "Correct it!"));
        } else {
            Recipe currentRecipe = new Recipe(recipe.getId(),
                    selectPatient.getValue(), selectDoctor.getValue(),
                    descriptionTextArea.getValue(), creationDateField.getValue(),
                    Integer.parseInt(validityInDaysField.getValue()), selectPriority.getValue());
            if (recipe.getId() == -1) DataController.recipeData.create(currentRecipe);
            else DataController.recipeData.update(currentRecipe);
            close();
        }
    }
}
