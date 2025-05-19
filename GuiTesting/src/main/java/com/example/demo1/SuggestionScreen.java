package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

public class SuggestionScreen extends FormScreen{
    private final String[] labels = new String[]{"Προορισμός 1", "Προορισμός 2", "Προορισμός 3"};
    public SuggestionScreen() {
        super("Προτεινόμενος Προορισμός");
        renderWelcomeLabel("Μήπως Εννοείτε;");
        renderDropdown(labels);
        renderFormSubmissionButton();
        renderHorizontalBar();
    }

}
