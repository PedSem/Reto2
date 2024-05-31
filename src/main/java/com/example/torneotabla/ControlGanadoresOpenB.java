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
        premiosGanan();

        this.tablaRanking.setItems(getJugadorconpremiosB());
        this.RankingFinal.setCellValueFactory(new PropertyValueFactory<>("RangoFinal"));
        this.RankingInicial.setCellValueFactory(new PropertyValueFactory<>("RangoInicial"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.elo.setCellValueFactory(new PropertyValueFactory<>("ELO"));
        this.torneo.setCellValueFactory(new PropertyValueFactory<>("NomTorneo"));
        this.categoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        this.puesto.setCellValueFactory(new PropertyValueFactory<>("Puesto"));
        this.premio.setCellValueFactory(new PropertyValueFactory<>("Premio"));

        try {
            escribirGanadores("GanadoresOpenB");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObservableList<Jugador> getJugadorconpremiosB(){
        ObservableList<Jugador> obsP = FXCollections.observableArrayList();
        Connection cnx;
        try {
            cnx = Conection.getConection();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery("SELECT jugador.RangoInicial,Jugador.Nombre,Jugador.ELO,Jugador.RangoFinal,Jugador.NomTorneo,Premio.Tipo,Premio.Puesto,Premio.Cantidad FROM jugador join premio on jugador.RangoInicial=Premio.RangoInicial where jugador.NomTorneo = 'OPEN B' and Premio.NomTorneo = 'OPEN B' order by Premio.RangoInicial");
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

    private static void escribirGanadores(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write("Ganadores BENIDORM CHESS OPEN B\n");
        writer.write(".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-. \n");
        ObservableList<Jugador> obs = getJugadorconpremiosB();
        for (Jugador ob : obs) {
            writer.write(ob.getRangoFinal() + " " + ob.getRangoInicial() + " " + ob.getNombre() + " " + ob.getELO() + " " + ob.getNomTorneo() + " " + ob.getCategoria() + " " + ob.getPuesto() + " " + ob.getPuesto() + " " + ob.getPremio() + "\n");
            writer.write("-------------------------------------------------------------------------- \n");
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        this.jugador = null;
        // Cerrar la ventana
        Stage stage = (Stage) this.tablaRanking.getScene().getWindow();
        stage.close();
    }

    private void premiosGanan(){
        try{
            Connection cnx = Conection.getConection();
            int dinero;
            int puesto;
            String categoria;

            ObservableList<Jugador> jugadores = ControlOpenB.getJugador();
            String [] categorias;

            System.out.println(jugadores.size());
            for (int i = 1; i < jugadores.size(); i++){
                PreparedStatement ps = cnx.prepareStatement("select group_concat(Tipo) as Tipo from jugadoroptapremio where NomTorneo = 'OPEN B' and RangoInicial = ( select RangoInicial from jugador where RangoFinal = ? and NomTorneo = 'OPEN B')");
                ps.setInt(1,i);
                ps.execute();

                ResultSet rs = ps.executeQuery();
                rs.next();
                if (rs.getString("Tipo") != null) {
                    categorias = rs.getString("Tipo").split(",");
                    System.out.println(Arrays.toString(categorias));
                }else {
                    categorias = new String[]{""};
                }
                Arrays.sort(categorias, Collections.reverseOrder());
                ps.close();

                dinero = 0;
                puesto = 0;
                categoria = "";

                PreparedStatement psG = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN B' and RangoInicial is null) and Tipo = 'General' order by puesto asc limit 1");
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
                        case "SUB 1800":
                            PreparedStatement psS18 = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN B' and RangoInicial is null) and Tipo = 'SUB 1800' order by puesto asc limit 1");
                            psS18.execute();
                            ResultSet rsS18 = psS18.executeQuery();
                            try {
                                rsS18.next();
                                if (rsS18.getString(1) != null) {
                                    if (rsS18.getInt(3) > dinero) {
                                        categoria = rsS18.getString(1);
                                        puesto = rsS18.getInt(2);
                                        dinero = rsS18.getInt(3);
                                    }
                                }
                            }catch (SQLDataException ignored){}
                            psS18.close();
                            break;
                        case "SUB 1600":
                            PreparedStatement psS16 = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN B' and RangoInicial is null) and Tipo = 'SUB 1600' order by puesto asc limit 1");
                            psS16.execute();
                            ResultSet rsS16 = psS16.executeQuery();
                            try {
                                rsS16.next();
                                if (rsS16.getString(1) != null) {
                                    if (rsS16.getInt(3) > dinero) {
                                        categoria = rsS16.getString(1);
                                        puesto = rsS16.getInt(2);
                                        dinero = rsS16.getInt(3);
                                    }
                                }
                            }catch (SQLDataException ignored){}
                            psS16.close();
                            break;
                        case "SUB 1400":
                            PreparedStatement psS14 = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN B' and RangoInicial is null) and Tipo = 'SUB 1400' order by puesto asc limit 1");
                            psS14.execute();
                            ResultSet rsS14 = psS14.executeQuery();
                            try {
                                rsS14.next();
                                if (rsS14.getString(1) != null) {
                                    if (rsS14.getInt(3) > dinero) {
                                        categoria = rsS14.getString(1);
                                        puesto = rsS14.getInt(2);
                                        dinero = rsS14.getInt(3);
                                    }
                                }
                            }catch (SQLDataException ignored){}
                            psS14.close();
                            break;
                        case "Com.Valenciana":
                            PreparedStatement psCV = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN B' and RangoInicial is null) and Tipo = 'Com.Valenciana' order by puesto asc limit 1");
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
                            PreparedStatement psA = cnx.prepareStatement("select Tipo,Puesto,Cantidad from Premio where (NomTorneo = 'OPEN B' and RangoInicial is null) and Tipo = 'Alojados' order by puesto asc limit 1");
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
                PreparedStatement psP = cnx.prepareStatement("update Premio set RangoInicial = ( select RangoInicial from jugador where RangoFinal = ? and NomTorneo = 'OPEN B') where (Tipo = ? and puesto = ?) and NomTorneo = 'OPEN B'");
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
