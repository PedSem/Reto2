package com.example.torneotabla;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LecturaCSV {
    public static void main(String[] args){
        try {
        String line;
        BufferedReader br = new BufferedReader(new FileReader("/home/ALU1J/Documentos/data.csv"));
        int cont = 0;
        while ((line = br.readLine()) != null){
            String[] data = line.split(";");
            if (cont >= 5) {
                int rInicial = Integer.parseInt(data[0]);
            int fideID = Integer.parseInt(data[6]);
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

                int rFinal = 0;
            String nomTorneo = "xd";

                System.out.println(rInicial + "," + fideID + "," + nombre + "," + pais + "," + cv + "," + hotel + "," + rFinal + "," + nomTorneo);
            cont++;
            }else cont++;
        }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Se acabaron los jugadores");
        }
    }
}
