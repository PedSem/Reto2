package com.example.torneotabla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControlJugadorOptaPremioOpenA implements Initializable {
    //Mirar
    @FXML
    private TableView<JugadoroptaPremio> tablaJugadores;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtFIDEID;
    @FXML
    private TextField txtPais;
    @FXML
    private CheckBox cbCV;
    @FXML
    private CheckBox cbHotel;

    private Jugador jugador;

    private ObservableList<Jugador> jugadores;


    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnSalir;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    //Mirar
    public ObservableList<JugadoroptaPremio> obtenerDatosDeTabla() {
        return tablaJugadores.getItems();
    }

    @FXML
    private void salir(ActionEvent event) {
        this.jugador = null;
        // Cerrar la ventana
        Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
        stage.close();
    }

}
