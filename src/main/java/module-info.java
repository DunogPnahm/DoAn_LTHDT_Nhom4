module java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;

    opens Controller;
    opens Model;
    exports Controller;
    exports Model;

}
