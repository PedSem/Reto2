module com.example.torneotabla {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires waffle.jna;
    requires org.checkerframework.checker.qual;
    requires java.desktop;


    opens com.example.torneotabla to javafx.fxml;
    exports com.example.torneotabla;
}