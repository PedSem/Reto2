module com.example.torneotabla {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires waffle.jna;

    opens com.example.torneotabla to javafx.fxml;
    exports com.example.torneotabla;
}