package com.haulmont.view.reports;

import com.haulmont.controller.DataController;
import com.haulmont.model.reports.WrittenRecipes;
import com.vaadin.ui.*;


/**
 * The type Number written recipes report.
 */
public class NumberWrittenRecipesReport extends Window {
    /**
     * Instantiates a new Number written recipes report.
     */
    public NumberWrittenRecipesReport() {
        super("Statistic");
        setWidthUndefined();

        Layout mainLayout = new VerticalLayout();
        mainLayout.setWidthUndefined();


        Grid<WrittenRecipes> reportGrid = new Grid<>();
        reportGrid.setItems(DataController.doctorData.numberWrittenRecipes());
        reportGrid.addColumn(WrittenRecipes::getCaption).setCaption("Doctor");
        reportGrid.addColumn(WrittenRecipes::getNumberRecipes).setCaption("Written recipes");
        mainLayout.addComponent(reportGrid);

        Button button = new Button("Okay...", clickEvent -> close());
        mainLayout.addComponent(button);

        button.setWidthFull();
        setContent(mainLayout);
        center();
    }
}
