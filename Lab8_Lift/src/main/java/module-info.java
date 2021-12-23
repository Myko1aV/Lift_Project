module com.lift.lab8_lift {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    opens com.lift.lab8_lift to javafx.fxml;
    exports com.lift.lab8_lift;
}