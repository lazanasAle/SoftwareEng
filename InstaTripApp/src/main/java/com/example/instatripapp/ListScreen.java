package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.List;
// Strongly recommend to do this class into 2 classes
// One is ArrayScreen and the other is ListScreen
// Recommendation: is to put ListScreen to be a superclass to PackageDetailsScreen, PaymentScreen,FilterScreen,
public class ListScreen<T> extends Screen{
    public ListScreen(String title, int width, int height) {
        // screen methods
        super(title, width, height);
    }
    protected void renderArray(String[] columnNames, List<T> items, String[] propertyNames, String buttonName) {
        TableView<T> dataTable = new TableView<>();
        dataTable.setPrefWidth(widthOfScreen);
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn<T, String> column = new TableColumn<>(columnNames[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(propertyNames[i]));
            dataTable.getColumns().add(column);
        }

        // Add "Επιλογές" column
        TableColumn<T, Void> optionsColumn = new TableColumn<>("Επιλογές");
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
            }
        });

        dataTable.getColumns().add(optionsColumn);

        dataTable.getItems().addAll(items);
        dataTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.add(dataTable, 0, 1);
    }
    protected void renderList(List<String> items) {
        int gridPosition=1;
        for(String item : items) {
            Button button = new Button(item);
            grid.add(button, 0, gridPosition, 2, 1);
            gridPosition++;
            GridPane.setHalignment(button, javafx.geometry.HPos.CENTER); // Center the button in the grid cell
        }
    }
}
