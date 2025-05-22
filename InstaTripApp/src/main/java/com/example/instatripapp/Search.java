package com.example.instatripapp;

import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

class SearchContent {
    private String[] Keywords;
    private SpellChecker spell;
    private DataSourceManager manager;



    public SearchContent(String[] keywords,DataSourceManager manager) throws IOException {
        this.Keywords=keywords;
        this.spell = new SpellChecker("el-GR");
        this.manager=manager;
        for(int i=0;i<Keywords.length;i++) {
            if (!keywords[i].isEmpty()) {
                List<RuleMatch> errors = spell.check_spelling(Keywords[i]);
                if (!errors.isEmpty()) {
                    List<String> suggestions = spell.suggest_examples(errors);

                    ScreenRedirect.launchSuggestionScreen(suggestions, manager);
                } else {

                    manager.connect();

                    String query = "SELECT PackageID,email, TourAgency.name, startDate, endDate, description, maxParticipants\n" +
                            "FROM TourAgency INNER JOIN Package ON TourAgency.AgencyID = Package.AgencyID\n" +
                            "WHERE description LIKE ? and status='Σε εξέλιξη';";
                    PreparedStatement stmt = null;

                    Connection db_con = manager.getDb_con();
                    try {
                        if(db_con.isClosed())
                            manager.connect();
                        stmt = manager.getDb_con().prepareStatement(query);

                        List<Map<String, Object>> partres = null;

                        String keypp = "%" + Keywords[i] + "%";
                        partres = manager.fetch(stmt, new String[]{keypp});

                        if (!partres.isEmpty()) {
                            ScreenRedirect.make_result_screen(partres, manager);
                        } else {
                            ScreenRedirect.launchErrorMsg("Δεν υπάρχουν αντικείμενα με αυτή την περιγραφή");
                        }
                    } catch (SQLException exe) {
                        ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ: " + exe);
                    }


                }
            }
        }



    }

    public void keywords_commit(){


    }
    public void make_suggestion_search_content(){

    }

    public void changeSearchContent(){

    }

    public void search(String search){

    }

}