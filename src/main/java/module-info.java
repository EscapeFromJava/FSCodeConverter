module com.codeconverter.fscodeconverter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    exports com.codeconverter.fscodeconverter;
    exports com.codeconverter.fscodeconverter.controller;
    opens com.codeconverter.fscodeconverter.controller to javafx.fxml;
}