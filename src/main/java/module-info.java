module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires xstream;

    opens controllers to javafx.fxml, xstream;
    opens models to javafx.fxml, xstream;

    exports Main;
    exports models;
    exports controllers;
    exports utils;

}