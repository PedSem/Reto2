package com.example.torneotabla;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControlModificarOpenA implements Initializable {

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

    private ObservableList<Jugador> jugadores;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAtributtes(ObservableList<Jugador> jugadores, Jugador jugador) {
        this.jugadores = jugadores;
        this.jugador = jugador;
        // cargo los datos de la persona
        this.txtNombre.setText(this.jugador.getNombre());
        this.txtFIDEID.setText(this.jugador.getFIDEID());
        this.txtPais.setText(this.jugador.getPais());
        this.cbCV.setSelected(this.jugador.isCV());
        this.cbHotel.setSelected(this.jugador.isHotel());
    }

    public Jugador getJugador() {
    return jugador;
    };



    @FXML
    private void guardar(ActionEvent event) throws SQLException {

        // Cojo los datos
        String nombre = this.txtNombre.getText();
        String fIDEID = this.txtFIDEID.getText();
        String pais = this.txtPais.getText();
        boolean cv = this.cbCV.isSelected();
        boolean hotel = this.cbHotel.isSelected();

        Connection cnx;
        cnx = Conection.getConection();

        // Creo la persona
        Jugador j = new Jugador(nombre,fIDEID,pais,cv,hotel);

        // Compruebo si la persona existe
        if (!jugadores.contains(j)) {

            // Modificar
            if (this.jugador != null) {
                PreparedStatement ps = cnx.prepareStatement("update jugador set nombre = ?, FIDEID = ?, Pais = ?, CV = ?, Hotel = ? where FIDEID = ? and   = 'OPEN A'");
                ps.setString(1, nombre);
                ps.setString(2, fIDEID);
                ps.setString(3, pais);
                ps.setBoolean(4, cv);
                ps.setBoolean(5, hotel);
                ps.setString(6, this.jugador.getFIDEID());
                ps.executeUpdate();
                ps.close();

                // Modifico el objeto
                this.jugador.setNombre(nombre);
                this.jugador.setFIDEID(fIDEID);
                this.jugador.setPais(pais);
                this.jugador.setCV(cv);
                this.jugador.setHotel(hotel);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Se ha modificado correctamente");
                alert.showAndWait();

            }

            // Cerrar la ventana
            Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La persona ya existe");
            alert.showAndWait();
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        this.jugador = null;
        // Cerrar la ventana
        Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
        stage.close();
    }


}
