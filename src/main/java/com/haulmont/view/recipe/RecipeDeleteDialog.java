package com.haulmont.view.recipe;

import com.haulmont.controller.DataController;
import com.haulmont.model.Recipe;
import com.haulmont.view.components.MessageBox;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

/**
 * The type Recipe delete dialog.
 */
public class RecipeDeleteDialog extends Window {
    /**
     * Instantiates a new Recipe delete dialog.
     *
     * @param recipe     the recipe
     * @param recipeGrid the recipe grid
     */
    public RecipeDeleteDialog(Recipe recipe, Grid<Recipe> recipeGrid) {
        super("Delete Recipe");
        setWidthUndefined();

        Layout mainLayout = new VerticalLayout();
        Layout buttonsLayout = new HorizontalLayout();

        Label questionLabel = new Label("A you sure to delete this recipe?");
        Label recipeLabel = new Label(recipe.getShortDescription());


        Button okButton = new Button("Ok", VaadinIcons.CHECK);
        okButton.addClickListener(clickEvent -> {
            okClick(recipe);
            recipeGrid.setItems(DataController.recipeData.findAll());
            close();
        });

        Button cancelButton = new Button("Cancel", VaadinIcons.EXIT);
        cancelButton.addClickListener(clickEvent -> close());

        buttonsLayout.addComponents(okButton, cancelButton);

        mainLayout.addComponents(questionLabel, recipeLabel, buttonsLayout);
        setContent(mainLayout);

        center();
    }

    private void okClick(Recipe recipe) {
        if (!DataController.recipeData.deleteByID(recipe.getId()))
            UI.getCurrent().addWindow(new MessageBox("Error",
                    "Сan't delete: the reason is not known.", "Okay..."));
    }
}
