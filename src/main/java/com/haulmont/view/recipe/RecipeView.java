package com.haulmont.view.recipe;

import com.haulmont.controller.DataController;
import com.haulmont.model.Patient;
import com.haulmont.model.Priority;
import com.haulmont.model.Recipe;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

/**
 * The type Recipe view.
 */
public class RecipeView {
    private Layout layout = new VerticalLayout();
    private Grid<Recipe> recipeGrid;
    private Button addNewButton;
    private Button editButton;
    private Button deleteButton;
    private Recipe currentRecipe = Recipe.NULL_RECIPE;
    /**
     * The Error label.
     */
    Label errorLabel = new Label();

    private TextField descriptionFilter;
    private NativeSelect<Patient> patientFilter;
    private NativeSelect<Priority> priorityFilter;
    private Button filterButton;

    /**
     * Instantiates a new Recipe view.
     */
    public RecipeView() {
        recipeGrid();
        actionButtons();
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addComponents(addNewButton, editButton, deleteButton);
        layout.addComponents(filterLayout(), recipeGrid, buttonLayout, errorLabel);

        recipeGrid.addItemClickListener(itemClick -> {
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
            currentRecipe = itemClick.getItem();
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

    private Grid<Recipe> recipeGrid() {
        recipeGrid = new Grid<>();
        recipeGrid.setItems(DataController.recipeData.findAll());
        recipeGrid.addColumn(Recipe::getDescription).setCaption("Description");
        recipeGrid.addColumn(recipe -> recipe.getDoctor().getSurnameNP()).setCaption("Doctor");
        recipeGrid.addColumn(recipe -> recipe.getPatient().getSurnameNP()).setCaption("Patient");
        recipeGrid.addColumn(Recipe::getCreationDate).setCaption("CreationDate");
        recipeGrid.addColumn(Recipe::getValidityInDays).setCaption("Validity (days)");
        recipeGrid.addColumn(recipe -> recipe.getPriority().getCaption()).setCaption("Priority");

        recipeGrid.setWidthFull();

        return recipeGrid;
    }

    private void actionButtons() {
        //Add new recipe Button
        addNewButton = new Button(VaadinIcons.PLUS);
        addNewButton.addClickListener(clickEvent -> {
            RecipeAddOrEditDialog recipeDialog = new RecipeAddOrEditDialog(Recipe.NULL_RECIPE, recipeGrid);
            UI.getCurrent().addWindow(recipeDialog);
        });

        //Edit recipe Button
        editButton = new Button(VaadinIcons.PENCIL);
        editButton.setEnabled(false);
        editButton.addClickListener(clickEvent -> {
            RecipeAddOrEditDialog recipeDialog = new RecipeAddOrEditDialog(currentRecipe, recipeGrid);
            UI.getCurrent().addWindow(recipeDialog);
        });

        //Delete recipe button
        deleteButton = new Button(VaadinIcons.DEL_A);
        deleteButton.setEnabled(false);
        deleteButton.addClickListener(clickEvent -> {
            RecipeDeleteDialog recipeDeleteDialog = new RecipeDeleteDialog(currentRecipe, recipeGrid);
            UI.getCurrent().addWindow(recipeDeleteDialog);
        });
    }

    private Layout filterLayout() {
        Layout filterLayout = new HorizontalLayout();

        descriptionFilter = new TextField();
        descriptionFilter.setPlaceholder("Filter by description...");

        patientFilter = new NativeSelect<>();
        patientFilter.setEmptySelectionAllowed(true);
        patientFilter.setItems(DataController.patientData.findAll());
        patientFilter.setItemCaptionGenerator(item -> item.getSurnameNP() + ", " + item.getPhone());


        priorityFilter = new NativeSelect<>();
        priorityFilter.setEmptySelectionAllowed(true);
        priorityFilter.setItems(Priority.NORMAL, Priority.CITO, Priority.STATIM);

        filterButton = new Button("Filter it!");
        filterButton.addClickListener(clickEvent -> updateGrid());


        filterLayout.addComponents(descriptionFilter, patientFilter, priorityFilter, filterButton);
        return filterLayout;
    }

    private void updateGrid() {
        String description = descriptionFilter.getValue();
        Patient patient = patientFilter.getValue();
        Priority priority = priorityFilter.getValue();
//        errorLabel.setCaption("d: " + description + " p: " + patient.getSurnameNP() + " pr: " + priority);

        recipeGrid.setItems(DataController.recipeData.findAll(description, patient, priority));
    }
}
