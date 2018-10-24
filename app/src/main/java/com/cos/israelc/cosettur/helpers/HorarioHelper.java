package com.cos.israelc.cosettur.helpers;

import java.io.NotSerializableException;
import java.io.Serializable;

public class HorarioHelper extends NotSerializableException implements Serializable {
    private String entrada;
    private String salida;

    public HorarioHelper(){
        this("","");
    }
    public HorarioHelper(String entrada,String salida){
        this.entrada=entrada;
        this.salida=salida;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

}