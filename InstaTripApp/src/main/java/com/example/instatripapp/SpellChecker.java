package com.example.instatripapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.Languages;
import org.languagetool.rules.RuleMatch;

interface SpellCheckerIf {
    public List<RuleMatch> check_spelling(String expression) throws IOException;
    public List<String> suggest_examples(List<RuleMatch> error_rules);
}

public class SpellChecker extends JLanguageTool implements SpellCheckerIf{
    public SpellChecker(String scode){
        super(Languages.getLanguageForShortCode(scode));
    }

    public void removeGrammarRules(){
        for(var rule : this.getAllRules()){
            if(!rule.isDictionaryBasedSpellingRule())
                this.disableRule(rule.getId());
        }
    }

    @Override
    public List<RuleMatch> check_spelling(String expression) throws IOException {
        removeGrammarRules();
        return this.check(expression);
    }

    @Override
    public List<String> suggest_examples(List<RuleMatch> error_rules) {
        List<String> examples = new ArrayList<>();
        if(error_rules.isEmpty()){
            return examples;
        }
        String original = error_rules.getFirst().getSentence().getText();
        generateCombinations(original, error_rules, 0, 0, examples);
        return examples;

    }
    private void generateCombinations(String currentText, List<RuleMatch> rules, int ruleIndex, int offset, List<String> results) {
        if (ruleIndex >= rules.size()) {
            results.add(currentText);
            return;
        }

        RuleMatch rule = rules.get(ruleIndex);

        int from = rule.getFromPos() + offset;
        int to = rule.getToPos() + offset;

        String originalWord = currentText.substring(from, to);

        for (String suggestion : rule.getSuggestedReplacements()) {
            StringBuilder sb = new StringBuilder(currentText);
            sb.replace(from, to, suggestion);
            int newOffset = offset + (suggestion.length() - originalWord.length());
            generateCombinations(sb.toString(), rules, ruleIndex + 1, newOffset, results);
        }
    }
}



class checkingTest{
    public static void main(String []args) throws IOException {
        SpellChecker englishChecker = new SpellChecker("en-US");
        SpellChecker greekChecker = new SpellChecker("el-GR");

        List<RuleMatch> englishErrors = englishChecker.check_spelling("this is a textt with erors so many erors that makee me sickkk");
        List<RuleMatch> greekErrors = greekChecker.check_spelling("Μονήμερη εκδομή στην καλαμπάκα");

        List<String> englishSuggestions = englishChecker.suggest_examples(englishErrors);
        List<String> greekSuggestions = greekChecker.suggest_examples(greekErrors);

        for(String engSuggestion : englishSuggestions){
            System.out.println(engSuggestion);
        }
        for(String greekSuggestion : greekSuggestions){
            System.out.println(greekSuggestion);
        }
    }
}