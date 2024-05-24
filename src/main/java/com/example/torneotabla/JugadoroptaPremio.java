package com.example.torneotabla;

import java.sql.SQLException;
import java.util.Objects;

public class JugadoroptaPremio {
    private String NomTorneo;
    private String Tipo;
    private int puesto;
    private int rangoinicial;

    public JugadoroptaPremio(String nomTorneo, String tipo, int puesto, int rangoinicial) {
        NomTorneo = nomTorneo;
        Tipo = tipo;
        this.puesto = puesto;
        this.rangoinicial = rangoinicial;
    }
    public JugadoroptaPremio(){
        this.NomTorneo = "";
        this.Tipo = "";
        this.puesto = 0;
        this.rangoinicial = 0;
    }
    public String getNomTorneo() {
        return NomTorneo;
    }
    public void setNomTorneo(String nomTorneo) {
        NomTorneo = nomTorneo;

    }
    public String getTipo() {
        return Tipo;
    }
    public void setTipo(String tipo) {
        Tipo = tipo;
    }
    public int getPuesto() {
        return puesto;
    }
    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }
    public int getRangoinicial() {
        return rangoinicial;
    }
    public void setRangoinicial(int rangoinicial) {
        this.rangoinicial = rangoinicial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JugadoroptaPremio that = (JugadoroptaPremio) o;
        return puesto == that.puesto && rangoinicial == that.rangoinicial && Objects.equals(NomTorneo, that.NomTorneo) && Objects.equals(Tipo, that.Tipo);
    }

    @Override
    public int hashCode() {
        final int primo=31;
        int result = 1;

        result=primo*result+(NomTorneo==null?0:NomTorneo.hashCode());
       result=primo*result+(Tipo==null?0:Tipo.hashCode());
        result=primo*result+puesto;
       result=primo*result+rangoinicial;
        return result;
    }
}
