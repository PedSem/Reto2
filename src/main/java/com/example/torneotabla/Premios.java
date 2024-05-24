package com.example.torneotabla;

import java.util.Objects;

public class Premios {
    private String nomtorneo;
    private String Tipo;
    private int Puesto;
    private int Cantidad;
    private int rangoinicial;

    public Premios(String nomtorneo, String tipo, int puesto, int cantidad, int rangoinicial) {
        this.nomtorneo = nomtorneo;
        this.Tipo = tipo;
        this.Puesto = puesto;
        this.Cantidad = cantidad;
        this.rangoinicial = rangoinicial;
    }
    public Premios(){
        this.nomtorneo = "";
        this.Tipo = "";
        this.Puesto = 0;
        this.Cantidad = 0;
        this.rangoinicial = 0;

    }

    public Premios(String nomTorneo, String tipo, int puesto, int rangoinicial) {
    }

    public String getNomtorneo() {
        return nomtorneo;
    }
    public void setNomtorneo(String nomtorneo) {
        this.nomtorneo = nomtorneo;

    }
    public String getTipo() {
        return Tipo;
    }
    public void setTipo(String tipo) {
        Tipo = tipo;
    }
    public int getPuesto() {
        return Puesto;
    }
    public void setPuesto(int puesto) {
        Puesto = puesto;
    }
    public int getCantidad() {
        return Cantidad;
    }
    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }
    public int getRangoinicial() {
        return rangoinicial;
    }
    public void setRangoinicial(int rangoinicial) {
        this.rangoinicial = rangoinicial;
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Premios premios = (Premios) o;
        return Puesto == premios.Puesto && Cantidad == premios.Cantidad && rangoinicial == premios.rangoinicial && Objects.equals(nomtorneo, premios.nomtorneo) && Objects.equals(Tipo, premios.Tipo);
    }

 */
/*
    @Override
    public int hashCode() {

        int result = Objects.hashCode(nomtorneo);
        result = 31 * result + Objects.hashCode(Tipo);
        result = 31 * result + Puesto;
        result = 31 * result + Cantidad;
        result = 31 * result + rangoinicial;
        return result;
    }

 */
    @Override
    public boolean equals(Object o) {
        boolean result=true;
        boolean finished=false;
        if(this==o)
            result=true;
            if(this!=o){
                if(o==null){
                    result=false;
                }else if(getClass()!=o.getClass()){
                    result=false;

                }else{
                    Premios premios=(Premios)o;
                    if(nomtorneo==null){
                        if(premios.nomtorneo!=null){
                            result=false;
                            finished=true;
                        }
                    }else if(!nomtorneo.equals(premios.nomtorneo)){
                        result=false;
                        finished=true;
                    }
                    if(!finished){
                        if(Tipo==null){
                            if(premios.Tipo!=null){
                                result=false;
                                finished=true;

                            }
                        }else if(!Tipo.equals(premios.Tipo)){
                            result=false;
                            finished=true;
                        }
                    }
                }
            }
            return result;


    }
    @Override
    public int hashCode() {
        final int primo=31;
        int result = 1;
        result=primo*result+((nomtorneo==null)?0:nomtorneo.hashCode());
        result=primo*result+((Tipo==null)?0:Tipo.hashCode());
        result=primo*result+Puesto;
        result=primo*result+(Cantidad);
        result=primo*result+(rangoinicial);
        return result;
    }

}
