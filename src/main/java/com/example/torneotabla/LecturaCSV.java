package com.example.torneotabla;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class LecturaCSV {
    public static void main(String[] args){
    }
    public static void introducirJugadores(String torneo){
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("/home/ALU1J/Documentos/data.csv")); //Esto se cambia por el seleccionador
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
                    int rFinal = cont;
                    DatosJugador.TablaJugador(rInicial,fideID,nombre,elo,pais,cv,hotel,rFinal,torneo);
                    cont++;
                }else cont++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Se acabaron los jugadores");
        } catch (SQLException e) {
            System.out.println("Ya están los usuarios en la base de datos");
        }
    }
}
