package com.example.instatripapp;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import static com.example.instatripapp.MainScreen.manager;

// Strongly recommend to do this class into 2 classes
// One is ArrayScreen and the other is ListScreen
// Recommendation: is to put ListScreen to be a superclass to PackageDetailsScreen, PaymentScreen,FilterScreen,
public class ListScreen<T extends Searchable> extends Screen{
    public ListScreen(String title, int width, int height) {
        // screen methods
        super(title, width, height);
    }
    protected void renderArray(String[] columnNames, List<T> items, String[] propertyNames, String buttonName, PopupWindow<T> popup) {
        TableView<T> dataTable = new TableView<>();
        populateTableView(dataTable, widthOfScreen, columnNames, propertyNames);

        // Add "Επιλογές" column
        TableColumn<T, Void> optionsColumn = new TableColumn<>("Επιλογές");
        setOptionsColumn(optionsColumn, buttonName, dataTable, items, popup);

    }

    protected void renderArray(String[] columnNames, List<T> items, String[] propertyNames,String buttonName) {
        TableView<T> dataTable = new TableView<>();
        populateTableView(dataTable, widthOfScreen, columnNames, propertyNames);

        // Add "Επιλογές" column
        TableColumn<T, Void> optionsColumn = new TableColumn<>("Επιλογές");
        setOptionsColumn(optionsColumn, buttonName, dataTable, items, null);
    }

    protected void renderList(List<String> items) {
        int gridPosition=1;
        for(String item : items) {
            Button button = new Button(item);
            button.setMaxWidth(Double.MAX_VALUE);
            grid.add(button, 0, gridPosition, 2, 1);
            gridPosition++;
            GridPane.setHalignment(button, javafx.geometry.HPos.CENTER); // Center the button in the grid cell
        }
    }
    protected void renderListMain(List<String> items) {
        int gridPosition=1;
        for(String item : items) {
            Button button = new Button(item);
            button.setMaxWidth(Double.MAX_VALUE);

            button.setOnAction(e->{MainScreen.GetFunction(item);});

            grid.add(button, 0, gridPosition, 2, 1);
            gridPosition++;
            GridPane.setHalignment(button, javafx.geometry.HPos.CENTER); // Center the button in the grid cell
        }
    }
    private void setOptionsColumn(TableColumn<T, Void> optionsColumn, String buttonName, TableView<T> dataTable, List<T> items, PopupWindow<T> options) {

        optionsColumn.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button(buttonName);

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) { // if the row is empty
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
                if (options != null) {
                    button.setOnAction(e -> {
                        T element = dataTable.getItems().get(getIndex());
                        options.createPopup(element, button, element.getKey());
                    });
                } else if (buttonName.equals("Καλαθι")) {
                    button.setOnAction((e -> {
                        Package element= (Package) dataTable.getItems().get(getIndex());
                        ReservationBucket bucket=new ReservationBucket(element,manager);
                    }));
                } else if (buttonName.equals(" ")) {
                    button.setVisible(false);

                } else if (buttonName.equals("Αίτημα") || buttonName.equals("Πληρωμη")) {
                    String text = (buttonName.equals("Αίτημα"))? "Αποστολή" : "Submit";
                    List<Package> element = new ArrayList<>();
                    button.setOnAction(e->{
                        Button sumbit=new Button(text);
                        element.add((Package) dataTable.getItems().get(getIndex()));
                        PackageDetailsScreen pack=new PackageDetailsScreen(element.get(0), sumbit, manager);
                    });

                }
                else if(buttonName.equals("Τροποποιηση")){
                    button.setOnAction(e->{
                        Request element = (Request) dataTable.getItems().get(getIndex());
                        ScreenConnector.check_coop_status(element,manager);
                    });
                }
                else if(buttonName.equals("Ακυρωση")){

                    button.setOnAction(e->{
                        Request  element = (Request) dataTable.getItems().get(getIndex());
                        ScreenRedirect.show_cancelation_form(element,manager);
                    });
                }
                else if(buttonName.equals("Αποδοχή")){
                    button.setOnAction(e->{
                        Request req = (Request) dataTable.getItems().get(getIndex());
                        ScreenConnector.approveRequest(req, manager);
                    });
                }
            }
        });

        dataTable.getColumns().add(optionsColumn);

        dataTable.getItems().addAll(items);
        dataTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(dataTable, 0, 1);
    }


    private void populateTableView( TableView<T> dataTable, int widthOfScreen, String[] columnNames, String[] propertyNames){
        dataTable.setPrefWidth(widthOfScreen);
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn<T, String> column = new TableColumn<>(columnNames[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(propertyNames[i]));
            dataTable.getColumns().add(column);
        }
    }

}
