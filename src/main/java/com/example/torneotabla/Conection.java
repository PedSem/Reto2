package com.example.torneotabla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

    private String url = "jdbc:mariadb://localhost:3306/torneo";
    private String nombre;
    private String password;

    //Constructor

    public Conection(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    //Métodos

    public Connection getConection() throws SQLException {
        return DriverManager.getConnection(this.url, this.nombre, this.password);
    }


    //Getters and Setters

    /*public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
}
