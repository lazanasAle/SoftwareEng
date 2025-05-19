package com.example.instatripapp;

import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class SearchContent {
    private String[] Keywords;
    private SpellChecker spell;
    private DataSourceManager manager;



    public SearchContent(String[] keywords) throws IOException {
        this.Keywords=keywords;
        this.spell = new SpellChecker("el");
        for(int i=0;i<Keywords.length;i++){
            List<RuleMatch> errors = spell.check_spelling(Keywords[i]);
            if(!errors.isEmpty()) {
                List<String> suggestions = spell.suggest_examples(errors);

                ScreenRedirect.launchSuggestionScreen(suggestions);
            }
            else {
                DataSourceManager manager=new DataSourceManager();
                manager.connect();

                String query="SELECT PackageID,email, TourAgency.name, startDate, endDate, description, maxParticipants\n" +
                        "FROM TourAgency INNER JOIN Package ON TourAgency.AgencyID = Package.AgencyID\n" +
                        "WHERE description LIKE ? and status='Σε εξέλιξη';";
                PreparedStatement stmt = null;

                Connection db_con = manager.getDb_con();
                try {

                    stmt = manager.getDb_con().prepareStatement(query);

                    List<Map<String, Object>> partres = null;



                    String keypp="%" + Keywords[i] + "%";
                    System.out.println("Querying with: " + keypp);
                    partres = manager.fetch(stmt, new String[]{keypp});


                    ScreenRedirect.make_result_screen(partres);

                }catch(Exception e){
                    System.out.println(e);
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