package com.example.torneotabla;

import java.sql.*;

public class PremiosOptarJugador {
    private static final String url = "jdbc:mysql://localhost:3306/torneo";
    private static final String user = "root";
    private static final String password = "root";

    private int rangoInicial;
    private String nombre;
    private String tipo;
    private String puesto;
    private String tipoTorneo;

    public PremiosOptarJugador(int rangoInicial, String nombre, String tipo, String tipoTorneo) {
        this.rangoInicial = rangoInicial;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tipoTorneo = tipoTorneo;
    }

    public PremiosOptarJugador(int rangoInicial, String nombre, String tipo, String puesto, String tipoTorneo) {
        this.rangoInicial = rangoInicial;
        this.nombre = nombre;
        this.tipo = tipo;
        this.puesto = puesto;
        this.tipoTorneo = tipoTorneo;
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

            conn = DriverManager.getConnection(url, user, password);
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
