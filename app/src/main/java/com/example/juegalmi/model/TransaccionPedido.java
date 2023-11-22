package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TransaccionPedido implements Serializable {
    @SerializedName("latitud")
    private String latitud;
    @SerializedName("longitud")
    private String longitud;
    @SerializedName("tipo_transaccion")
    private String tipo_transaccion;
    @SerializedName("detalles")
    private DetallePedidoTransaccion[] detalles;

    public TransaccionPedido(String latitud, String longitud, String tipo_transaccion, DetallePedidoTransaccion[] detalles) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo_transaccion = tipo_transaccion;
        this.detalles = detalles;
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

    public DetallePedidoTransaccion[] getDetalles() {
        return detalles;
    }

    public void setDetalles(DetallePedidoTransaccion[] detalles) {
        this.detalles = detalles;
    }
}
