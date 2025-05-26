package com.example.instatripapp;


import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

public class SearchPackageScreen extends SearchScreen {

    private TourAgency  organizerMember;
    private DataSourceManager manager;
    private Customer client;

    public SearchPackageScreen(StringWrapper content, TourAgency organizerMember, DataSourceManager manager) {
        super("Αναζήτηση Πακέτων", 1000, 750, content);
        this.organizerMember=organizerMember;
        this.manager=manager;
        renderLabel("Αναζήτηση Πακέτων");
        renderGrid(500);
    }

    public SearchPackageScreen(StringWrapper content, Customer client, DataSourceManager manager) {
        super("Αναζήτηση Πακέτων", 1000, 750, content);
        this.client=client;
        this.manager=manager;
        renderLabel("Αναζήτηση Πακέτων");
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
                if(this.organizerMember!=null){
                    ScreenConnector.afterPackageSearchPerform(organizerMember, manager, content);
                }
                else if(this.client!=null){
                    ScreenConnector.afterCommitPerform(client,manager,content);
                }
                else{
                    ScreenRedirect.launchErrorMsg("");
                }
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
                if(this.organizerMember!=null){
                    ScreenConnector.afterPackageSearchPerform(organizerMember, manager, content);
                }
                else if(this.client!=null){
                    ScreenConnector.afterCommitPerform(client,manager,content);
                }

            }
        });

    }
}

