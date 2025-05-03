module com.example.instatripapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.instatripapp to javafx.fxml;
    exports com.example.instatripapp;
}