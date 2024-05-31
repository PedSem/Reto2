package com.example.torneotabla;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LecturaCSV {
    public static void main(String[] args){
    }
    public static void introducirJugadores(String torneo){
        try {
            FileChooser d = new FileChooser();
            File file = d.showOpenDialog(null);
            String line;
            BufferedReader br = new BufferedReader(new FileReader(file));
            int cont = 0;
            while ((line = br.readLine()) != null){
                String[] data = line.split(";");
                if (cont >= 5) {

                    String rInicial = data[0];
                    int elo = Integer.parseInt(data[4]);
                    String fideID = data[6];
                    String nombre = data[2];
                    String pais = data[3];
                    boolean cv = false;
                    boolean hotel = false;
                    try {
                        switch (data[9]) {
                            case "CVH" -> {
                                cv = true;
                                hotel = true;
                            }
                            case "H" -> hotel = true;
                            case "CV" -> cv = true;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        cv = false;
                        hotel = false;
                    }
                    int rFinal = 1;
                    DatosJugador.TablaJugador(rInicial,fideID,nombre,elo,pais,cv,hotel,rFinal,torneo);
                    cont++;
                }else cont++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.setContentText("Se han terminado de importar los jugadores");
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println("Ya están los usuarios en la base de datos");
        } catch (NullPointerException e){
            System.out.println("Ningun archivo seleccionado");
        }
    }

    public static void introducirfinal(String torneo){
        try {
            Connection cnx;
            cnx = Conection.getConection();
            FileChooser d = new FileChooser();
            File file = d.showOpenDialog(null);
            String line;
            BufferedReader br = new BufferedReader(new FileReader(file));
            int cont = 0;
            while ((line = br.readLine()) != null){
                String[] data = line.split(";");
                if (cont >= 5) {
                    PreparedStatement ps = cnx.prepareStatement("update jugador set RangoFinal = ? where RangoInicial = ? and NomTorneo = ?");
                    int rfinal = Integer.parseInt(data[0]);
                    int rinicial = Integer.parseInt(data[1]);
                    ps.setInt(1, rfinal);
                    ps.setInt(2, rinicial);
                    ps.setString(3, torneo);
                    ps.executeUpdate();
                    ps.close();
                }else cont++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (SQLException E){
            //System.out.println("Ya estan los RFinales en la base de datos");
            E.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.setContentText("Se han terminado el Rango Final");
            alert.showAndWait();
        }catch (NullPointerException e){
            System.out.println("Ningun archivo seleccionado");
        }
    }
}
