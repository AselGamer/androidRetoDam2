package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetallePedidoTransaccion implements Serializable {
    @SerializedName("idarticulo")
    private int idarticulo;

    public DetallePedidoTransaccion(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }
}
