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



    private Jugador jugador;

    //private ObservableList<PremiosOptarJugador> jugadoresPremiosOptan;


    public void imprimir (ActionEvent event) throws IOException {
        try {
            escribirOptaOpenA("JugadorOptaPremioA.txt");
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

            ResultSet rsSUB2400 = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo FROM jugador j JOIN Premio p ON j.NomTorneo = p.NomTorneo WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.ELO < 2400 AND p.Tipo = 'SUB 2400') GROUP BY p.Tipo, j.Nombre ORDER BY j.RangoInicial");

            while (rsSUB2400.next()) {

                int rinicial = rsSUB2400.getInt("RangoInicial");
                String nom = rsSUB2400.getString("Nombre");
                String tipo = rsSUB2400.getString("tipo");
                String nomtorneo = rsSUB2400.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }

            ResultSet rsSUB2200 = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo FROM jugador j JOIN Premio p ON j.NomTorneo = p.NomTorneo WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.ELO < 2200 AND p.Tipo = 'SUB 2200') GROUP BY p.Tipo, j.Nombre ORDER BY j.RangoInicial;");

            while (rsSUB2200.next()) {

                int rinicial = rsSUB2200.getInt("RangoInicial");
                String nom = rsSUB2200.getString("Nombre");
                String tipo = rsSUB2200.getString("Tipo");
                String nomtorneo = rsSUB2200.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }

            ResultSet rsCV = stm.executeQuery("SELECT j.RangoInicial, j.Nombre, p.Tipo, p.NomTorneo FROM jugador j JOIN Premio p ON j.NomTorneo = p.NomTorneo WHERE (p.NomTorneo = 'OPEN A' AND j.NomTorneo = 'OPEN A') AND (j.CV = true AND p.Tipo = 'Com.Valenciana') GROUP BY p.Tipo, j.Nombre ORDER BY j.RangoInicial;");

            while (rsCV.next()) {
                int rinicial = rsCV.getInt("RangoInicial");
                String nom = rsCV.getString("Nombre");
                String tipo = rsCV.getString("Tipo");
                String nomtorneo = rsCV.getString("NomTorneo");
                PremiosOptarJugador premiosOpta = new PremiosOptarJugador(rinicial, nom, tipo, nomtorneo);
                obs.add(premiosOpta);
            }



            //stm.executeQuery("SET FOREIGN_KEY_CHECKS = 0");
            for (PremiosOptarJugador premiosoptar: obs){
                PreparedStatement ps = cnx.prepareStatement("INSERT INTO jugadoroptapremio VALUES (?, ?, ?)");
                String nomTorneo = premiosoptar.getTorneo();
                String tipo = premiosoptar.getTipo();
                int rangoInicial = premiosoptar.getRangoInicial();

                //PremiosOptarJugador premOpt = new PremiosOptarJugador(nomTorneo, tipo, rangoInicial);

                ps.setString(1, nomTorneo);
                ps.setString(2, tipo);
                ps.setInt(3, rangoInicial);
                ps.executeUpdate();
                ps.close();
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }


        return obs;
    }

    private static void escribirOptaOpenA(String filename) throws IOException {
        //select jugador.nombre,jugadoroptapremio.RangoInicial,group_concat(distinct Tipo) as Tipo , jugadoroptapremio.NomTorneo from jugadoroptapremio join jugador on jugador.RangoInicial = jugadoroptapremio.RangoInicial group by jugadoroptapremio.RangoInicial;
        try {
            Connection cnx = Conection.getConection();
            Statement stm = cnx.createStatement();
            FileWriter writer = new FileWriter(filename);
            ResultSet wJOPA = stm.executeQuery("select jugador.nombre,jugadoroptapremio.RangoInicial,group_concat(distinct Tipo) as Tipo , jugadoroptapremio.NomTorneo from jugadoroptapremio join jugador on jugador.RangoInicial = jugadoroptapremio.RangoInicial WHERE jugadoroptapremio.NomTorneo = 'OPEN A' group by jugadoroptapremio.RangoInicial");

            writer.write("BENIDORM CHESS OPEN A\n");
            writer.write(".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-. \n");
            while (wJOPA.next()) {
                writer.write(wJOPA.getString(1) + " ");
                writer.write(wJOPA.getString(2) + " ");
                writer.write(wJOPA.getString(3) + " ");
                writer.write(wJOPA.getString(4));
                writer.write("\n --------------------------------------------------------------- \n");
            }
            writer.close();

        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public void cargar(){
        ObservableList<PremiosOptarJugador> obs = getJugadorOptaPremio();
        this.tablaRanking.setItems(obs);
        this.RankingInicial.setCellValueFactory(new PropertyValueFactory<>("RangoInicial"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.Torneo.setCellValueFactory(new PropertyValueFactory<>("Torneo"));

    }

    @FXML
    private void salir(ActionEvent event) {
        this.jugador = null;
        // Cerrar la ventana
        Stage stage = (Stage) this.tablaRanking.getScene().getWindow();
        stage.close();
    }

}
