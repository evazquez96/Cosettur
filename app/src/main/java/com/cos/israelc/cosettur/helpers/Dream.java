package com.cos.israelc.cosettur.helpers;

public class Dream {

    public String fecha_inicio ;
    public String fecha_fin ;
    public double inactivo ;

    public Dream(){
        /**
         * Constructor por defecto.
         */
    }
    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public double getInactivo() {
        return inactivo;
    }

    public void setInactivo(double inactivo) {
        this.inactivo = inactivo;
    }
}
