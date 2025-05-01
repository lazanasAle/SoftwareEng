module com.example.instatrip_java_impl {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.instatrip_java_impl to javafx.fxml;
    exports com.example.instatrip_java_impl;
}