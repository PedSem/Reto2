package com.example.torneotabla;

import java.sql.*;

public class PremiosOptarJugador {


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
