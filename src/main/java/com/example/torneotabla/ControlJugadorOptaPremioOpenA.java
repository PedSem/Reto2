package com.example.torneotabla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControlJugadorOptaPremioOpenA implements Initializable {
    //Mirar
    @FXML
    private static TableView<PremiosOptarJugador> tablaRanking;

    @FXML
    private TableColumn<Premios,Integer> RankingInicial;

    @FXML
    private TableColumn<Premios,Integer> nombre;

    @FXML
    private TableColumn<Premios,String> categoria;

    @FXML
    private TableColumn<Premios,String>  torneo;

    private Jugador jugador;

    //private ObservableList<PremiosOptarJugador> jugadoresPremiosOptan;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PremiosOptarJugador> jugadoresPremiosOptan = getJugadorOptaPremio();
        this.tablaRanking.setItems(jugadoresPremiosOptan);
        this.RankingInicial.setCellValueFactory(new PropertyValueFactory<>("RangoInicial"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        this.torneo.setCellValueFactory(new PropertyValueFactory<>("torneo"));

        cargar();
    }

    public ObservableList<PremiosOptarJugador> getJugadorOptaPremio(){
        ObservableList<PremiosOptarJugador> obs = FXCollections.observableArrayList();
        Connection cnx;
        try {
            cnx = Conection.getConection();
            Statement stm = cnx.createStatement();

            ResultSet rsAlojados = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo\n" +
                    "FROM jugador j JOIN premio p ON j.NomTorneo = p.NomTorneo \n" +
                    "WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.Hotel = true AND p.Tipo = \"Alojados\")\n" +
                    "GROUP BY p.Tipo, j.Nombre\n" +
                    "ORDER BY j.RangoInicial;");

            while (rsAlojados.next()) {

                int rinicial = rsAlojados.getInt("RangoInicial");
                String nom = rsAlojados.getString("Nombre");
                String tipo = rsAlojados.getString("Tipo");
                String nomtorneo = rsAlojados.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }

            /*ResultSet rsSUB2400 = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo\n" +
                    "FROM jugador j JOIN premio p ON j.NomTorneo = p.NomTorneo \n" +
                    "WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.ELO < 2400 AND p.Tipo = \"SUB 2400\")\n" +
                    "GROUP BY p.Tipo, j.Nombre\n" +
                    "ORDER BY j.RangoInicial;");

            while (rsSUB2400.next()) {

                int rinicial = rsAlojados.getInt("RangoInicial");
                String nom = rsAlojados.getString("Nombre");
                String tipo = rsAlojados.getString("Tipo");
                String nomtorneo = rsAlojados.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }

            ResultSet rsSUB2200 = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo\n" +
                    "FROM jugador j JOIN premio p ON j.NomTorneo = p.NomTorneo \n" +
                    "WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.ELO < 2200 AND p.Tipo = \"SUB 2200\")\n" +
                    "GROUP BY p.Tipo, j.Nombre\n" +
                    "ORDER BY j.RangoInicial;");

            while (rsSUB2200.next()) {

                int rinicial = rsAlojados.getInt("RangoInicial");
                String nom = rsAlojados.getString("Nombre");
                String tipo = rsAlojados.getString("Tipo");
                String nomtorneo = rsAlojados.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }

            /*ResultSet rsCV = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo\n" +
                    "FROM jugador j JOIN premio p ON j.NomTorneo = p.NomTorneo \n" +
                    "WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.CV = true AND p.Tipo = \"Com.Valenciana\")\n" +
                    "GROUP BY p.Tipo, j.Nombre\n" +
                    "ORDER BY j.RangoInicial;");

            while (rsCV.next()) {

                int rinicial = rsAlojados.getInt("RangoInicial");
                String nom = rsAlojados.getString("Nombre");
                String tipo = rsAlojados.getString("Tipo");
                String nomtorneo = rsAlojados.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obs;
    }

    public void cargar(){
        ObservableList<PremiosOptarJugador> obs = getJugadorOptaPremio();
        this.tablaRanking.setItems(obs);
        this.RankingInicial.setCellValueFactory(new PropertyValueFactory<>("RangoInicial"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        this.torneo.setCellValueFactory(new PropertyValueFactory<>("torneo"));

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
