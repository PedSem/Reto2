module com.example.torneotabla {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.torneotabla to javafx.fxml;
    exports com.example.torneotabla;
}