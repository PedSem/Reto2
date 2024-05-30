package com.example.torneotabla;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlGanadoresOpenB implements Initializable {

    @FXML
    private TableView<Jugador> tablaRanking;

    @FXML
    private TableColumn<Jugador,Integer> RankingFinal;

    @FXML
    private TableColumn<Jugador,Integer> RankingInicial;

    @FXML
    private TableColumn<Jugador,String>  nombre;

    @FXML
    private TableColumn<Jugador,Integer> elo;

    @FXML
    private TableColumn<Jugador,String>  torneo;

    @FXML
    private TableColumn<Jugador,String>  categoria;

    @FXML
    private TableColumn<Jugador,String>  puesto;

    @FXML
    private TableColumn<Jugador,String>  premio;

    private ObservableList<Jugador> jugadores;

    private Jugador jugador;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void salir(ActionEvent event) {
        this.jugador = null;
        // Cerrar la ventana
        Stage stage = (Stage) this.tablaRanking.getScene().getWindow();
        stage.close();
    }

}
