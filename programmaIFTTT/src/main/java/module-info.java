module com.example.programmaifttt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.programmaifttt to javafx.fxml;
    exports com.example.programmaifttt;
}