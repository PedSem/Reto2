package com.example.torneotabla;

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
        try{
            sc=new Scanner(System.in);
            int opcion;
            do{
                menu();
                opcion=Integer.parseInt(sc.nextLine());
                switch(opcion){
                    case 1:
                        AddJugador();
                        break;


                }


            }while(opcion!=0);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    private static Connection getConexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/torneo";
        String user="root";
        String password="root";
        return DriverManager.getConnection(url,user,password);
    }
    public static void menu(){
        System.out.println("Gestionar Jugadores");
        System.out.println("-----------------");
        System.out.println("1. Añadir Jugador");
    }
    private static void AddJugador() throws SQLException{
        PreparedStatement ps= cnx.prepareStatement("INSERT INTO Jugador(RangoInicial)");



    }

}
