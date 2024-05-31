module com.example.torneotabla {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires waffle.jna;
    requires org.checkerframework.checker.qual;
    requires java.desktop;
    requires jdk.compiler;


    opens com.example.torneotabla to javafx.fxml;
    exports com.example.torneotabla;
}