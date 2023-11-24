module com.example.programmaifttt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.json;

    opens com.example.programmaifttt to javafx.fxml;
    exports com.example.programmaifttt;
    exports com.example.programmaifttt.Triggers;
    opens com.example.programmaifttt.Triggers to javafx.fxml;
    exports com.example.programmaifttt.Actions;
    opens com.example.programmaifttt.Actions to javafx.fxml;
    exports com.example.programmaifttt.BackEnd;
    opens com.example.programmaifttt.BackEnd to javafx.fxml;
}