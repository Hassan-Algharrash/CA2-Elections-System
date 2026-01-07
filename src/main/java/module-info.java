module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires xstream;

    opens controllers to javafx.fxml;
    exports main;
    exports models;
    exports controllers;
    exports utils;
    opens models to javafx.fxml, xstream;
}