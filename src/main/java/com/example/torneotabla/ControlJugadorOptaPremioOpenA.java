package com.example.torneotabla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControlJugadorOptaPremioOpenA implements Initializable {
    //Mirar
    @FXML
    private TableView<PremiosOptarJugador> tablaRanking;

    @FXML
    private TableColumn<PremiosOptarJugador,Integer> RankingInicial;

    @FXML
    private TableColumn<PremiosOptarJugador,Integer> nombre;

    @FXML
    private TableColumn<PremiosOptarJugador,String> tipo;

    @FXML
    private TableColumn<PremiosOptarJugador,String>  Torneo;

    @FXML
    ObservableList<PremiosOptarJugador> obs;

    private Jugador jugador;

    //private ObservableList<PremiosOptarJugador> jugadoresPremiosOptan;


    public void imprimir (ActionEvent event) throws IOException {

        try {
            writeToTextFile("JugadorOptaPremio.txt", getJugadorOptaPremio());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PremiosOptarJugador> jugadoresPremiosOptan = getJugadorOptaPremio();
        this.tablaRanking.setItems(jugadoresPremiosOptan);
        this.RankingInicial.setCellValueFactory(new PropertyValueFactory<>("RangoInicial"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.Torneo.setCellValueFactory(new PropertyValueFactory<>("Torneo"));

        cargar();
    }

    public ObservableList<PremiosOptarJugador> getJugadorOptaPremio(){
        ObservableList<PremiosOptarJugador> obs = FXCollections.observableArrayList();
        Connection cnx;
        try {
            cnx = Conection.getConection();
            Statement stm = cnx.createStatement();

            ResultSet rsAlojados = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo FROM jugador j JOIN Premio p ON j.NomTorneo = p.NomTorneo WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.Hotel = true AND p.Tipo = 'Alojados') GROUP BY p.Tipo, j.Nombre ORDER BY j.RangoInicial");

            while (rsAlojados.next()) {

                int rinicial = rsAlojados.getInt("RangoInicial");
                String nom = rsAlojados.getString("Nombre");
                String tipo = rsAlojados.getString("tipo");
                String nomtorneo = rsAlojados.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }

            ResultSet rsSUB2400 = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo FROM jugador j JOIN premio p ON j.NomTorneo = p.NomTorneo WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.ELO < 2400 AND p.Tipo = 'SUB 2400') GROUP BY p.Tipo, j.Nombre ORDER BY j.RangoInicial");

            while (rsSUB2400.next()) {

                int rinicial = rsSUB2400.getInt("RangoInicial");
                String nom = rsSUB2400.getString("Nombre");
                String tipo = rsSUB2400.getString("tipo");
                String nomtorneo = rsSUB2400.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }

            ResultSet rsSUB2200 = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo FROM jugador j JOIN premio p ON j.NomTorneo = p.NomTorneo WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.ELO < 2200 AND p.Tipo = 'SUB 2200') GROUP BY p.Tipo, j.Nombre ORDER BY j.RangoInicial;");

            while (rsSUB2200.next()) {

                int rinicial = rsSUB2200.getInt("RangoInicial");
                String nom = rsSUB2200.getString("Nombre");
                String tipo = rsSUB2200.getString("Tipo");
                String nomtorneo = rsSUB2200.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }

            ResultSet rsCV = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo FROM jugador j JOIN premio p ON j.NomTorneo = p.NomTorneo WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.CV = true AND p.Tipo = 'Com.Valenciana') GROUP BY p.Tipo, j.Nombre ORDER BY j.RangoInicial;");

            while (rsCV.next()) {

                int rinicial = rsCV.getInt("RangoInicial");
                String nom = rsCV.getString("Nombre");
                String tipo = rsCV.getString("Tipo");
                String nomtorneo = rsCV.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return obs;
    }

    private static void writeToTextFile(String filename, ObservableList<PremiosOptarJugador> premiosopta) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (PremiosOptarJugador premioopt : premiosopta) {
            writer.write(premioopt.getRangoInicial() + "," + premioopt.getNombre() + "," + premioopt.getTipo() + "," + premioopt.getTorneo() + "\n");
        }
        writer.close();
    }

    public void cargar(){
        ObservableList<PremiosOptarJugador> obs = getJugadorOptaPremio();
        this.tablaRanking.setItems(obs);
        this.RankingInicial.setCellValueFactory(new PropertyValueFactory<>("RangoInicial"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.Torneo.setCellValueFactory(new PropertyValueFactory<>("Torneo"));

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
        Stage stage = (Stage) this.tablaRanking.getScene().getWindow();
        stage.close();
    }

}
