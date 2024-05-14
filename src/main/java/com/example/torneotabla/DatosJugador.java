package com.example.torneotabla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DatosJugador {
    static Connection cnx;
    static Scanner sc;
    static{
        try{
            cnx=getConexion();
        }catch(SQLException e){
            e.printStackTrace();
        }




    }

    public static void main(String[] args) {


    }

    private static Connection getConexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/torneo";
        String user="root";
        String password="root";
        return DriverManager.getConnection(url,user,password);
    }

    private static void TablaJugador(String Rangoinicial,String FIDEID,String nombre,int ELO,String Pais,boolean CV,boolean Hotel,int rangofinal,String nomtorneo) throws SQLException, IOException {
        PreparedStatement ps = cnx.prepareStatement("INSERT INTO Jugador(RangoInicial,FIDEID,Nombre,ELO,Pais,CV,Hotel,rangofinal,NomTorneo) VALUES(?,?,?,?,?,?,?,?,?)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ps.setString(1, Rangoinicial);
        ps.setString(2, FIDEID);
        ps.setString(3, nombre);
        ps.setInt(4, ELO);
        ps.setString(5, Pais);
        ps.setBoolean(6, CV);
        ps.setBoolean(7, Hotel);
        ps.setInt(8, rangofinal);
        ps.setString(9, nomtorneo);
        ps.executeUpdate();
        br.close();








    }

}
