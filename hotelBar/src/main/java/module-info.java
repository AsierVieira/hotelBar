module donamayor.hotelbar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens donamayor.hotelbar to javafx.fxml;
    exports donamayor.hotelbar;
    exports donamayor.hotelbar.controller;
    opens donamayor.hotelbar.controller to javafx.fxml;
}