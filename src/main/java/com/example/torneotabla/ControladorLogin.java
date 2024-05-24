package com.example.torneotabla;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorLogin implements Initializable {

    @FXML
    private Button btnAcceder;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Preparamos las llamadas a la accion
    @FXML
    public void loguearse(ActionEvent actionEvent) {

        try{
            String passwordUsuario = txtPassword.getText();
            Conection conexion = new Conection(txtNombre.getText(), passwordUsuario);
            conexion.getConection();

            //Cargamos la vista del Open A
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));

            // Cargo el padre
            Parent root = loader.load();

            // Obtengo el controlador
            ControladorMenu controlador = loader.getController();

            // Creo la scene
            Scene scene = new Scene(root);

            //Cargo la hoja de estilos
            scene.getStylesheets().add(getClass().getResource("StylesMenu.css").toExternalForm());

            //Creo el Stage
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.setTitle("Benidorm Chess Open");
            stage.show();

            /* Indico que debe hacer al cerrar
            stage.setOnCloseRequest(e -> controlador.closeWindows());

            // Cierro la ventana donde estoy
            Stage myStage = (Stage) this.btnOpenA.getScene().getWindow();
            myStage.close();*/
        }
        catch (IOException e){
            Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
