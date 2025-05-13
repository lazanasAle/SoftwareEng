package com.example.instatripapp;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UtilityHelper {
    public static void populateTableView( TableView<?> dataTable, int widthOfScreen, String[] columnNames, String[] propertyNames){
        dataTable.setPrefWidth(widthOfScreen);
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn<?, String> column = new TableColumn<>(columnNames[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(propertyNames[i]));
            dataTable.getColumns().add((TableColumn) column);
        }
    }
}
