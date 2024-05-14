package com.example.torneotabla;

public class Jugador {
    private int RangoInicial;
    private String FIDEID;
    private int ELO;
    private String Pais;
    private boolean CV;
    private boolean Hotel;
    private int rangofinal;
    private String NomTorneo;

    public Jugador(int rangoInicial, String FIDEID, int ELO, String pais, boolean CV, boolean hotel, int rangofinal, String nomTorneo) {
        this.RangoInicial = rangoInicial;
        this.FIDEID = FIDEID;
        this.ELO = ELO;
        this.Pais = pais;
        this.CV = CV;
        this.Hotel = hotel;
        this.rangofinal = rangofinal;
        this.NomTorneo = nomTorneo;
    }
    public int getRangoInicial() {
        return RangoInicial;
    }
    public void setRangoInicial(int rangoInicial) {
        RangoInicial = rangoInicial;
    }
    public String getFIDEID() {
        return FIDEID;
    }
    public void setFIDEID(String FIDEID) {
        this.FIDEID = FIDEID;
    }
    public int getELO() {
        return ELO;
    }
    public void setELO(int ELO) {
        this.ELO = ELO;
    }
    public String getPais() {
        return Pais;
    }
    public void setPais(String pais) {
        Pais = pais;

    }
    public boolean isCV() {
        return CV;
    }
    public void setCV(boolean CV) {
        this.CV = CV;
    }
    public boolean isHotel() {
        return Hotel;
    }
    public void setHotel(boolean Hotel) {
        this.Hotel = Hotel;
    }
    public int getRangofinal() {
        return rangofinal;

    }
    public void setRangofinal(int rangofinal) {
        this.rangofinal = rangofinal;
    }
    public String getNomTorneo() {
        return NomTorneo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jugador jugador = (Jugador) o;
        return RangoInicial == jugador.RangoInicial && ELO == jugador.ELO && CV == jugador.CV && Hotel == jugador.Hotel && rangofinal == jugador.rangofinal && FIDEID.equals(jugador.FIDEID) && Pais.equals(jugador.Pais) && NomTorneo.equals(jugador.NomTorneo);
    }

    @Override
    public int hashCode() {
        final int primo=31;
        int result = 1;
        result = primo * result + RangoInicial;
        result=primo*result+((FIDEID==null)?0:FIDEID.hashCode());
        result=primo*result + ELO;
        result=primo*result + ((Pais==null)?0:Pais.hashCode());
        result=primo*result + rangofinal;
        result=primo*result + ((NomTorneo==null)?0:NomTorneo.hashCode());
        return result;
    }
}
