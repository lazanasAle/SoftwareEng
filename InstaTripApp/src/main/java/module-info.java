module com.example.instatripapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.instatripapp to javafx.fxml;
    exports com.example.instatripapp;
}