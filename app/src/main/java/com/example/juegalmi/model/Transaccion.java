package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Transaccion implements Serializable {
    @SerializedName("idtransaccion")
    int idtransaccion;
    @SerializedName("latitud")
    String latitud;
    @SerializedName("longitud")
    String longitud;
    @SerializedName("fecha")
    String fecha;
    @SerializedName("detalles")
    DetalleTransaccion[] detalles;

    public Transaccion(int idtransaccion, String latitud, String longitud, String fecha, DetalleTransaccion[] detalles) {
        this.idtransaccion = idtransaccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
        this.detalles = detalles;
    }

    public int getIdtransaccion() {
        return idtransaccion;
    }

    public void setIdtransaccion(int idtransaccion) {
        this.idtransaccion = idtransaccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public DetalleTransaccion[] getDetalles() {
        return detalles;
    }

    public void setDetalles(DetalleTransaccion[] detalles) {
        this.detalles = detalles;
    }
}
