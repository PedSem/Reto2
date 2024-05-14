package com.example.torneotabla;


import java.sql.*;
import java.util.Scanner;

public class DatosPremios {
    static Scanner sc;
    static Connection cnx;
    static {
        try {
            cnx = getConexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {



    }
    private static Connection getConexion() throws SQLException{
        String url="jdbc:mysql://localhost:3306/torneo";
        String user="root";
        String password="root";
        return DriverManager.getConnection(url,user,password);
    }
    private static void MostrarPremio() throws SQLException{
        try{
            Statement st= cnx.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM Premio");
            while(rs.next()){
                String nomtorneo=rs.getString("nomtorneo");
                String Tipo=rs.getString("tipo");
                int Puesto=rs.getInt("puesto");
                int Cantidad=rs.getInt("cantidad");
                int rangoinicial=rs.getInt("rangoinicial");
                System.out.printf("\t%s \t%d %s(%s)\n" + nomtorneo,Tipo,Puesto,Cantidad,rangoinicial);
            }
            st.close();
            rs.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

}

