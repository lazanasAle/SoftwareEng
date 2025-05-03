package com.example.instatravelgui;
import java.util.List;
public class FilterScreen extends ListScreen {
  public FilterScreen() {
      super("Φίλτρα", 600, 300);
      renderLabel("Επιλέξτε τα φίλτρα που θέλετε να εφαρμόσετε:");
      renderFilterOptions();
  }
  private void renderFilterOptions() {

        // Dummy data for demonstration
        List<String> filterOptions = List.of("Φίλτρο 1", "Φίλτρο 2", "Φίλτρο 3");

        renderList(filterOptions);
  }
}
