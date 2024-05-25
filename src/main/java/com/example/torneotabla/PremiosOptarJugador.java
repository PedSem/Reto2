package com.example.torneotabla;

import java.sql.*;

public class PremiosOptarJugador {


    public int rangoInicial;
    public String nombre;
    public String tipo;
    public String puesto;
    public String Torneo;

    public PremiosOptarJugador(String torneo, String tipo, int rangoInicial) {
        this.rangoInicial = rangoInicial;
        this.tipo = tipo;
        Torneo = torneo;
    }

    public PremiosOptarJugador(int rangoInicial, String nombre, String tipo, String Torneo) {
        this.rangoInicial = rangoInicial;
        this.nombre = nombre;
        this.tipo = tipo;
        this.Torneo = Torneo;
    }

    public PremiosOptarJugador(int rangoInicial, String nombre, String tipo, String puesto, String Torneo) {
        this.rangoInicial = rangoInicial;
        this.nombre = nombre;
        this.tipo = tipo;
        this.puesto = puesto;
        this.Torneo = Torneo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTorneo() {
        return Torneo;
    }

    public void setTorneo(String torneo) {
        Torneo = torneo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(int rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public static void main(String[] args) {
        /*
        int rangoInicial = 2;
        try {
            String premios = obtenerPremios(rangoInicial);
            System.out.println("Premios que opta el jugador con RangoInicial " + rangoInicial + ": " + premios);
        } catch (SQLException e) {
            e.printStackTrace();
        }

         */
    }


    public static String obtenerPremios(int rangoInicial) throws SQLException {
        Connection conn = null;
        CallableStatement stmt = null;
        String premios = null;

        try {
            // DriverManager.registerDriver(new org.mariadb.jdbc.Driver());

            conn = Conection.getConection();
            // Preparar la llamada al procedimiento almacenado
            String sql = "{? = CALL PremiosQueOpta(?)}";
            stmt = conn.prepareCall(sql);

            // Registrar el parámetro de salida
            stmt.registerOutParameter(1, Types.VARCHAR);

            // Establecer el parámetro de entrada
            stmt.setInt(2, rangoInicial);

            // Ejecutar la llamada al procedimiento
            stmt.execute();

            // Obtener el valor de retorno
            premios = stmt.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Finalmente cerrar los recursos
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return premios;
    }
}
