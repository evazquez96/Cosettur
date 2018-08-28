package com.cos.israelc.cosettur.helpers;
import java.util.List;

public class Registro{

    private List<Dream> dreams;
    private double inactivo;
    private double activo ;

    public Registro(){
        /**
         * Constructo por defecto
         */
    }
    @Override
    public String toString(){
        return "Inactivo: "+inactivo+"\n"+
                "Activo:"+activo;
    }

    public List<Dream> getDreams() {
        return dreams;
    }

    public void setDreams(List<Dream> dreams) {
        this.dreams = dreams;
    }

    public double getInactivo() {
        return inactivo;
    }

    public void setInactivo(double inactivo) {
        this.inactivo = inactivo;
    }

    public double getActivo() {
        return activo;
    }

    public void setActivo(double activo) {
        this.activo = activo;
    }
}
