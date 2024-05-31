package com.example.torneotabla;

public class Jugador {
    private int RangoInicial;
    private String FIDEID;
    private String Nombre;
    private int ELO;
    private String Pais;
    private boolean CV;
    private boolean Hotel;
    private int RangoFinal;
    private String NomTorneo;
    private int premio;
    private int puesto;
    private String categoria;

    public Jugador(int rangoInicial, String FIDEID,String Nombre, int ELO, String pais, boolean CV, boolean hotel, int RangoFinal, String nomTorneo) {
        this.RangoInicial = rangoInicial;
        this.FIDEID = FIDEID;
        this.Nombre = Nombre;
        this.ELO = ELO;
        this.Pais = pais;
        this.CV = CV;
        this.Hotel = hotel;
        this.RangoFinal = RangoFinal;
        this.NomTorneo = nomTorneo;
    }


    public Jugador(String nombre, String fIDEID, String pais, Boolean cv, Boolean hotel) {
    }

    public Jugador(int rangoInicial, int rangoFinal, String nombre, int ELO, String nomTorneo, String categoria, int puesto, int premio) {
        RangoInicial = rangoInicial;
        RangoFinal = rangoFinal;
        Nombre = nombre;
        this.ELO = ELO;
        NomTorneo = nomTorneo;
        this.categoria = categoria;
        this.puesto = puesto;
        this.premio = premio;
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
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
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
    public int getRangoFinal() {
        return RangoFinal;

    }
    public void setRangoFinal(int RangoFinal) {
        this.RangoFinal = RangoFinal;
    }
    public String getNomTorneo() {
        return NomTorneo;
    }

    public int getPremio() {
        return premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jugador jugador = (Jugador) o;
        return RangoInicial == jugador.RangoInicial && ELO == jugador.ELO && CV == jugador.CV && Hotel == jugador.Hotel && RangoFinal == jugador.RangoFinal && FIDEID.equals(jugador.FIDEID) && Pais.equals(jugador.Pais) && NomTorneo.equals(jugador.NomTorneo);
    }

    @Override
    public int hashCode() {
        final int primo=31;
        int result = 1;
        result = primo * result + RangoInicial;
        result=primo*result+((FIDEID==null)?0:FIDEID.hashCode());
        result=primo*result + ELO;
        result=primo*result + ((Pais==null)?0:Pais.hashCode());
        result=primo*result + RangoFinal;
        result=primo*result + ((NomTorneo==null)?0:NomTorneo.hashCode());
        return result;
    }
}
