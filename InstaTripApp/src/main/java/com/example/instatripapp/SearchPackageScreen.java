package com.example.instatripapp;


import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SearchPackageScreen extends SearchScreen {

    private TourAgency organizerMember;
    private DataSourceManager manager;
    public SearchPackageScreen(SearchContent content, TourAgency organizerMember, DataSourceManager manager) {
        super("Αναζήτηση Πακέτων", 1000, 750, content);
        this.organizerMember=organizerMember;
        this.manager=manager;
        renderLabel("Αναζήτηση Πακέτων");
        renderGrid(500);
    }


    @Override
    protected void performSearch(String query, SearchContent content) throws IOException {
        resultsList.getItems().clear();
        resultsList.setVisible(true); // Show the field when a search is performed

        // dummy αποτελεσματα 
        if (!query.isBlank()) {
            SpellChecker spChecker=new SpellChecker("el-GR");
            List<RuleMatch> misconceptions = spChecker.check_spelling(query);
            if(!misconceptions.isEmpty()) {
                resultsList.getItems().addAll(spChecker.suggest_examples(misconceptions));
                resultsList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        content.content = newSelection;
                        stage.close();
                        ScreenConnector.afterSearchPerform(organizerMember, manager, content);
                    }
                });
            }
            else{
                stage.close();
                ScreenConnector.afterSearchPerform(organizerMember, manager, content);
            }
        } else {
            resultsList.getItems().add("Παρακαλώ εισάγετε κάποιο όρο αναζήτησης.");
        }
    }
}

