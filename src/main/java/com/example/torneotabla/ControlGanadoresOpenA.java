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
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class ControlGanadoresOpenA implements Initializable {

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
    private TableColumn<Jugador,String>  Puesto;

    @FXML
    private TableColumn<Jugador,String>  Premio;

    private Jugador jugador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        premiosGanan();

        this.tablaRanking.setItems(getJugadorconpremiosA());
        this.RankingFinal.setCellValueFactory(new PropertyValueFactory<>("RangoFinal"));
        this.RankingInicial.setCellValueFactory(new PropertyValueFactory<>("RangoInicial"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.elo.setCellValueFactory(new PropertyValueFactory<>("ELO"));
        this.torneo.setCellValueFactory(new PropertyValueFactory<>("NomTorneo"));
        this.categoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        this.Puesto.setCellValueFactory(new PropertyValueFactory<>("Puesto"));
        this.Premio.setCellValueFactory(new PropertyValueFactory<>("Premio"));

        try {
            escribirGanadores("GanadoresOpenA");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObservableList<Jugador> getJugadorconpremiosA(){
        ObservableList<Jugador> obsP = FXCollections.observableArrayList();
        Connection cnx;
        try {
            cnx = Conection.getConection();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery("SELECT jugador.RangoInicial,Jugador.Nombre,Jugador.ELO,Jugador.RangoFinal,Jugador.NomTorneo,Premio.Tipo,Premio.Puesto,Premio.Cantidad FROM jugador join premio on jugador.RangoInicial=Premio.RangoInicial where jugador.NomTorneo = 'OPEN A' and Premio.NomTorneo = 'OPEN A' order by Premio.RangoInicial");
            while (rs.next()) {
                int rinicial = rs.getInt("RangoInicial");
                String nom = rs.getString("Nombre");
                int elo = rs.getInt("ELO");
                int rfinal = rs.getInt("RangoFinal");
                String nomtorneo = rs.getString("NomTorneo");
                String categoria = rs.getString("Tipo");
                int puesto = rs.getInt("Puesto");
                int premio = rs.getInt("Cantidad");

                Jugador j = new Jugador(rfinal,rinicial,nom,elo,nomtorneo,categoria,puesto,premio);
                obsP.add(j);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obsP;
    }


    @FXML
    private void salir(ActionEvent event) {
        this.jugador = null;
        // Cerrar la ventana
        Stage stage = (Stage) this.tablaRanking.getScene().getWindow();
        stage.close();
    }

    private static void escribirGanadores(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write("Ganadores BENIDORM CHESS OPEN A\n");
        writer.write(".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-. \n");
        ObservableList<Jugador> obs = getJugadorconpremiosA();
        for (Jugador ob : obs) {
            writer.write(ob.getRangoFinal() + " " + ob.getRangoInicial() + " " + ob.getNombre() + " " + ob.getELO() + " " + ob.getNomTorneo() + " " + ob.getCategoria() + " " + ob.getPuesto() + " " + ob.getPuesto() + " " + ob.getPremio() + "\n");
            writer.write("-------------------------------------------------------------------------- \n");
        }
    }

    public void premiosGanan(){
        try{
            Connection cnx = Conection.getConection();
            int dinero;
            int puesto;
            String categoria;

            ObservableList<Jugador> jugadores = ControlOpenA.getJugador();
            String [] categorias;

            for (int i = 1; i < jugadores.size(); i++){
                PreparedStatement ps = cnx.prepareStatement("select group_concat(Tipo) as Tipo from jugadoroptapremio where NomTorneo = 'OPEN A' and RangoInicial = ( select RangoInicial from jugador where RangoFinal = ? and NomTorneo = 'OPEN A')");
                ps.setInt(1,i);
                ps.execute();

                ResultSet rs = ps.executeQuery();
                rs.next();
                if (rs.getString("Tipo") != null) {
                    categorias = rs.getString("Tipo").split(",");
                }else {
                    categorias = new String[]{""};
                }
                Arrays.sort(categorias, Collections.reverseOrder());
                ps.close();

                dinero = 0;
                puesto = 0;
                categoria = "";

                PreparedStatement psG = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN A' and RangoInicial is null) and Tipo = 'General' order by puesto asc limit 1");
                psG.execute();
                ResultSet rsG = psG.executeQuery();
                try {
                rsG.next();
                if (rsG.getString(1) != null){
                categoria = rsG.getString(1);
                puesto = rsG.getInt(2);
                dinero = rsG.getInt(3);
                }
                }catch (SQLDataException ignored){}
                psG.close();

                for (String s : categorias) {
                    switch (s) {
                        case "SUB 2400":
                            PreparedStatement psS24 = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN A' and RangoInicial is null) and Tipo = 'SUB 2400' order by puesto asc limit 1");
                            psS24.execute();
                            ResultSet rsS24 = psS24.executeQuery();
                            try {
                                rsS24.next();
                                if (rsS24.getString(1) != null) {
                                    if (rsS24.getInt(3) > dinero) {
                                        categoria = rsS24.getString(1);
                                        puesto = rsS24.getInt(2);
                                        dinero = rsS24.getInt(3);
                                    }
                                }
                            }catch (SQLDataException ignored){}
                            psS24.close();
                            break;
                        case "SUB 2200":
                            PreparedStatement psS22 = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN A' and RangoInicial is null) and Tipo = 'SUB 2200' order by puesto asc limit 1");
                            psS22.execute();
                            ResultSet rsS22 = psS22.executeQuery();
                            try{
                            rsS22.next();
                            if (rsS22.getString(1) != null) {
                                if (rsS22.getInt(3) > dinero) {
                                    categoria = rsS22.getString(1);
                                    puesto = rsS22.getInt(2);
                                    dinero = rsS22.getInt(3);
                                }
                            }
                            }catch (SQLDataException ignored){}
                            psS22.close();
                            break;
                        case "Com.Valenciana":
                            PreparedStatement psCV = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN A' and RangoInicial is null) and Tipo = 'Com.Valenciana' order by puesto asc limit 1");
                            psCV.execute();
                            ResultSet rsCV = psCV.executeQuery();
                            try {
                                rsCV.next();
                                if (rsCV.getString(1) != null) {
                                    if (rsCV.getInt(3) > dinero) {
                                        categoria = rsCV.getString(1);
                                        puesto = rsCV.getInt(2);
                                        dinero = rsCV.getInt(3);
                                    }
                                }
                            }catch (SQLDataException ignored){}
                            psCV.close();
                            break;
                        case "Alojados":
                            PreparedStatement psA = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN A' and RangoInicial is null) and Tipo = 'Alojados' order by puesto asc limit 1");
                            psA.execute();
                            ResultSet rsA = psA.executeQuery();
                            try {
                                rsA.next();
                                if (rsA.getString(1) != null) {
                                    if (rsA.getInt(3) > dinero) {
                                        categoria = rsA.getString(1);
                                        puesto = rsA.getInt(2);
                                        dinero = rsA.getInt(3);
                                    }
                                }
                            }catch (SQLDataException ignored){}
                            psA.close();
                            break;
                    }
                }
                PreparedStatement psP = cnx.prepareStatement("update Premio set RangoInicial = ( select RangoInicial from jugador where RangoFinal = ? and NomTorneo = 'OPEN A') where (Tipo = ? and puesto = ?) and NomTorneo = 'OPEN A'");
                psP.setInt(1,i);
                psP.setString(2,categoria);
                psP.setInt(3,puesto);
                psP.execute();
                psP.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}