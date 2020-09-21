package com.haulmont.view;

import com.haulmont.view.doctor.DoctorView;
import com.haulmont.view.patient.PatientView;
import com.haulmont.view.recipe.RecipeView;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The type Main ui.
 */
@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout layout = new VerticalLayout();
        layout.setWidthFull();

        TabSheet tabSheet = new TabSheet();

        //Recipe tab
        RecipeView recipeView = new RecipeView();
        Layout tabRecipe = recipeView.getLayout();
        tabRecipe.setCaption("Recipe");
        tabSheet.addTab(tabRecipe).setIcon(VaadinIcons.LIST);

        //Doctor tab
        DoctorView doctorView = new DoctorView();
        Layout tabDoctor = doctorView.getLayout();
        tabDoctor.setCaption("Doctor");
        tabSheet.addTab(tabDoctor).setIcon(VaadinIcons.DOCTOR);

        //Patient tab
        PatientView patientView = new PatientView();
        Layout tabPatient = patientView.getLayout();
        tabPatient.setCaption("Patient");
        tabSheet.addTab(tabPatient).setIcon(VaadinIcons.FAMILY);

        layout.addComponent(tabSheet);

        setContent(layout);
    }


}