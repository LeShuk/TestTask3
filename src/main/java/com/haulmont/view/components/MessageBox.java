package com.haulmont.view.components;

import com.vaadin.ui.*;

/**
 * The type Message box.
 */
public class MessageBox extends Window {
    /**
     * Instantiates a new Message box.
     *
     * @param caption       the caption
     * @param message       the message
     * @param buttonCaption the button caption
     */
    public MessageBox(String caption, String message, String buttonCaption) {
        super(caption);
        setWidthUndefined();

        Layout mainLayout = new VerticalLayout();
        mainLayout.setWidthUndefined();
        mainLayout.addComponent(new Label(message));
        Button button = new Button(buttonCaption, clickEvent -> close());
        mainLayout.addComponent(button);

        button.setWidthFull();
        setContent(mainLayout);
        center();
    }
}
