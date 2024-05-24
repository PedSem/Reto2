package com.example.torneotabla;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.checkerframework.checker.units.qual.N;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControlInsertarOpenA implements Initializable {

    @FXML
    private TextField txtRankingInicial;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtFIDEID;
    @FXML
    private TextField txtELO;
    @FXML
    private TextField txtPais;
    @FXML
    private CheckBox cbCV;
    @FXML
    private CheckBox cbHotel;

    private Jugador jugador;
    @FXML
    private ObservableList<Jugador> jugadores;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnSalir;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAtributtes(ObservableList<Jugador> personas) {
        this.jugadores = personas;
    }

    public void guardar(ActionEvent actionEvent) {
        try {
        int ranking = Integer.parseInt(this.txtRankingInicial.getText());
        String nombre = this.txtNombre.getText();
        String fideID = this.txtFIDEID.getText();
        int elo = Integer.parseInt(this.txtELO.getText());
        String pais = this.txtPais.getText();
        boolean cv = this.cbCV.isSelected();
        boolean hotel = this.cbHotel.isSelected();
        int rangofinal = 1;
        String nomtorneo = "OPEN A";

        Jugador j = new Jugador(ranking,fideID,nombre,elo,pais,cv,hotel,rangofinal,nomtorneo);



        if (!jugadores.contains(j)) {
            DatosJugador.TablaJugador(String.valueOf(ranking), fideID, nombre, elo, pais, cv, hotel, rangofinal, nomtorneo);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El jugador ya existe");
            alert.showAndWait();
        }
        }catch (SQLException | IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La clave principal esta repetida ( Ranking inicial )");
            alert.showAndWait();
        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Sigue el formato inicial");
            alert.showAndWait();
        }
            // Cerrar la ventana
            Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
            stage.close();
    }

    @FXML
    private void salir(ActionEvent event) {
        this.jugador = null;
        // Cerrar la ventana
        Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
        stage.close();
    }

}
