package com.example.torneotabla;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlOpenA implements Initializable {

    @FXML
    private Button btnImportarDatos;

    @FXML
    private Button btnInsertarJugador;

    @FXML
    private Button btnModificarJugador;

    @FXML
    private Button btnEliminarJugador;

    @FXML
    private Button btnJugadoresPremiosOptan;

    @FXML
    private Button btnImportarGanadores;

    @FXML
    private Button btnListaGanadores;

    @FXML
    private TableView<Jugador> tablaRanking;

    @FXML
    private TableColumn RankingFinal;

    @FXML
    private TableColumn RankingInicial;

    @FXML
    private TableColumn fideid;

    @FXML
    private TableColumn nombre;

    @FXML
    private TableColumn elo;

    @FXML
    private TableColumn pais;

    @FXML
    private TableColumn cv;

    @FXML
    private TableColumn hotel;

    @FXML
    private TableColumn torneo;

    @FXML
    private ObservableList<Jugador> listaJugadores;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    private void insertarJugador(javafx.event.ActionEvent actionEvent) {

        try {

            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("btnInsertarJugadorA.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Asigno el controlador
            ControlInsertarOpenA controlador = loader.getController();

            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Insertar Nuevo Jugador");
            stage.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }


    @FXML
    void modificarJugador(ActionEvent event) {

        /** La idea aquí es primero seleccionar un jugador en la TableView y después ya darle a modificar para hacer los cambios**/

        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("btnModificarJugadorA.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Asigno el controlador
            ControlModificarOpenA controlador = loader.getController();

            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Modificar Jugador");
            stage.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }




   public void closeWindows() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));

            Parent root = loader.load();

            // Obtengo el controlador
            //ControladorMenu controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            //stage.setTitle("Benidorm Chess 2023");
            stage.show();

            // Indico que debe hacer al cerrar
            //stage.setOnCloseRequest(e -> controlador.closeWindows());

            Stage myStage = (Stage) this.btnImportarDatos.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



}
