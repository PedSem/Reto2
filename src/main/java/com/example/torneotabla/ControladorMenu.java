package org.example.reto2benidormchess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorMenu implements Initializable {

    @FXML
    private Button btnOpenA;

    @FXML
    private Button btnOpenB;

    @FXML
    private Button listaAlojados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Preparamos las llamadas a la accion
    @FXML
    public void abrirOpenA(javafx.event.ActionEvent actionEvent) {

        try{
            //Cargamos la vista del Open A
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OpenAView.fxml"));

            // Cargo el padre
            Parent root = loader.load();

            // Obtengo el controlador
            ControlOpenA controlador = loader.getController();

            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.setTitle("Benidorm Chess Open A 2023");
            stage.show();

            // Indico que debe hacer al cerrar
            stage.setOnCloseRequest(e -> controlador.closeWindows());

            // Cierro la ventana donde estoy
            Stage myStage = (Stage) this.btnOpenA.getScene().getWindow();
            myStage.close();
        }
        catch (IOException e){
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void abrirOpenB(ActionEvent actionEvent) {

        try{
            //Cargamos la vista del Open A
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OpenBView.fxml"));

            // Cargo el padre
            Parent root = loader.load();

            // Obtengo el controlador
            ControlOpenA controlador = loader.getController();

            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.setTitle("Benidorm Chess Open B 2023");
            stage.show();

            // Indico que debe hacer al cerrar
            stage.setOnCloseRequest(e -> controlador.closeWindows());

            // Cierro la ventana donde estoy
            Stage myStage = (Stage) this.btnOpenB.getScene().getWindow();
            myStage.close();
        }
        catch (Exception e){
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    public void mostrarAlojados(ActionEvent actionEvent) {



    }

}
