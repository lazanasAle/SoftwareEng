package com.example.instatripapp;

import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

public class SearchPartnerScreen extends SearchScreen{

    private Package pkgMember;
    private DataSourceManager manager;
    public SearchPartnerScreen(StringWrapper content, Package pkgMember, DataSourceManager manager) {
        super("Αναζήτηση Συνεργατών", 1000, 750, content);
        this.pkgMember=pkgMember;
        this.manager=manager;
        renderLabel("Αναζήτηση Συνεργατών");
        renderGrid(500);
    }


    @Override
    protected void performSearch(String query, StringWrapper content) throws IOException {
        resultsList.getItems().clear();
        resultsList.setVisible(true);
        if (!query.isBlank()) {
            SpellChecker spChecker=new SpellChecker("el-GR");
            List<RuleMatch> misconceptions = spChecker.check_spelling(query);
            if(!misconceptions.isEmpty()) {
                resultsList.getItems().addAll(spChecker.suggest_examples(misconceptions));

            }
            else{
                stage.close();
                ScreenConnector.afterPartnerSearchPerform(pkgMember, manager, content);
            }
        } else {
            resultsList.getItems().add("Παρακαλώ εισάγετε κάποιο όρο αναζήτησης.");
        }
    }

    @Override
    protected void setupSearchUI(StringWrapper content){
        super.setupSearchUI(content);

        resultsList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                content.content = newSelection;
                stage.close();
                ScreenConnector.afterPartnerSearchPerform(pkgMember, manager, content);
            }
        });

    }
}
