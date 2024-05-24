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
    private TableView<Premios> tablaRanking;

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



    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnSalir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    /*
    public ControlJugadorOptaPremioOpenA(TableView<Premios> tablaJugadores) {
        this.tablaRanking = tablaJugadores;
    }




    public void initAtributtes(ObservableList<Premios> premios) {
    this.tablaRanking.setItems(premios);
    }
    */

    //Mirar

    /*
    public void initAtributtes(ObservableList<Jugador> premios) {
        this.tablaJugadores = premios;
    }
    */

    /*
    public ObservableList<Premios> obtenerDatosDeTabla() {
        return tablaJugadores.getItems();
    }
     */

    @FXML
    private void salir(ActionEvent event) {
        this.jugador = null;
        // Cerrar la ventana
        Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
        stage.close();
    }

}
