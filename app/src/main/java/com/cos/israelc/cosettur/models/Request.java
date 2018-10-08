package com.cos.israelc.cosettur;

public class Request {

    public String user;
    public String grado;
    public String semestre;
    public String ruta;
    public String localidad;
    public String modalidad;
    public String ciclo;
    public String tutor;
    public String tutorado;
    public String entrada;
    public String salida;
    public String pago;
    public String telefono;

    public Request() {
        this.user = "";
        this.grado = "";
        this.semestre = "";
        this.ruta = "";
        this.localidad = "";
        this.modalidad = "";
        this.ciclo = "";
        this.tutor = "";
        this.tutorado = "";
        this.entrada = "";
        this.salida = "";
        this.pago = "";
        this.telefono = "";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getTutorado() {
        return tutorado;
    }

    public void setTutorado(String tutorado) {
        this.tutorado = tutorado;
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

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
