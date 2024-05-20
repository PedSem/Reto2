package com.example.torneotabla;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;


public class BenidormChessMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            //Cargo la vista
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BenidormChessMain.class.getResource("MenuView.fxml"));

            // Cargo la ventana
            Pane ventana = (Pane) loader.load();

            // Cargo el scene
            Scene scene = new Scene(ventana);

            //Cargo la hoja de estilos
            scene.getStylesheets().add(getClass().getResource("StylesMenu.css").toExternalForm());

            // Seteo la scene y la muestro
            primaryStage.setScene(scene);
            primaryStage.setTitle("Benidorm Chess 2023");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
