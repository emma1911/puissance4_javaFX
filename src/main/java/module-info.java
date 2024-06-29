module com.example.puissance4_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.puissance4_javafx to javafx.fxml;
    exports com.example.puissance4_javafx;
}