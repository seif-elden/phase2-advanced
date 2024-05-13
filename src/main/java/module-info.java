module com.example.yarab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.yarab2 to javafx.fxml;
    exports com.example.yarab2;
}