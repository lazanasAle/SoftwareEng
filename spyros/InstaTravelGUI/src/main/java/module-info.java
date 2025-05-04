module com.example.instatravelgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.compiler;


    opens com.example.instatravelgui to javafx.fxml;
    exports com.example.instatravelgui;
}