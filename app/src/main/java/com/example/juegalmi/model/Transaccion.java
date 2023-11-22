package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Transaccion implements Serializable {
    @SerializedName("idtransaccion")
    private int idtransaccion;
    @SerializedName("latitud")
    private String latitud;
    @SerializedName("longitud")
    private String longitud;
    @SerializedName("tipo_transaccion")
    private String tipo_transaccion;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("fecha_inicio")
    private String fecha_inicio;
    @SerializedName("fecha_fin")
    private String fecha_fin;
    @SerializedName("fecha_devolucion")
    private String fecha_devolucion;
    @SerializedName("precio")
    private float precio;
    @SerializedName("detalles")
    private DetalleTransaccion[] detalles;

    public Transaccion(String latitud, String longitud, String tipo_transaccion, DetalleTransaccion[] detalles) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo_transaccion = tipo_transaccion;
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

    public String getTipo_transaccion() {
        return tipo_transaccion;
    }

    public void setTipo_transaccion(String tipo_transaccion) {
        this.tipo_transaccion = tipo_transaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public DetalleTransaccion[] getDetalles() {
        return detalles;
    }

    public void setDetalles(DetalleTransaccion[] detalles) {
        this.detalles = detalles;
    }
}
