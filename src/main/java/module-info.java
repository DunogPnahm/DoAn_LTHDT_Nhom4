module com.mycompany.do2g4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;

    opens Controller to javafx.fxml;
    exports Controller;
}
