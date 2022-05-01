module com.codeconverter.fscodeconverter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.codeconverter.fscodeconverter to javafx.fxml;
    exports com.codeconverter.fscodeconverter;
    exports com.codeconverter.fscodeconverter.controller;
    opens com.codeconverter.fscodeconverter.controller to javafx.fxml;
}